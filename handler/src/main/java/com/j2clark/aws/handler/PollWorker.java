package com.j2clark.aws.handler;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteMessageResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.util.CollectionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.j2clark.aws.domain.EventMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Semaphore;

public class PollWorker implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Logger failed = LoggerFactory.getLogger("failed");


    private final Semaphore workerManager;
    private final AmazonSQS sqs;
    private final String queueUrl;
    private final String queueName;
    private final int maxEvents;
    private final long maxProcessTime;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final EventHandlerRegistry eventHandlerRegistry;

    public static PollWorker of(final Semaphore workerManager,
                                final AmazonSQS sqs,
                                final String queueName,
                                final String queueUrl,
                                final int maxEvents,
                                final long maxProcessTime,
                                final EventHandlerRegistry eventHandlerRegistry) {
        return new PollWorker(workerManager, sqs, queueName, queueUrl, maxEvents, maxProcessTime, eventHandlerRegistry);
    }

    public PollWorker(final Semaphore workerManager,
                      final AmazonSQS sqs,
                      final String queueName,
                      final String queueUrl,
                      final int maxEvents,
                      final long maxProcessTime,
                      final EventHandlerRegistry eventHandlerRegistry) {
        this.workerManager = workerManager;
        this.sqs = sqs;
        this.queueName = queueName;
        this.queueUrl = queueUrl;
        this.maxEvents = maxEvents;
        this.maxProcessTime = maxProcessTime;
        this.eventHandlerRegistry = eventHandlerRegistry;
    }

    @Override
    public final void run() {
        try {
            //safePollForRetry(name);

            pollForProcessing();
        } finally {
            workerManager.release();
        }
    }

    protected void pollForProcessing() {
        int msgCount = 0;
        UUID pollId = UUID.randomUUID();
        long startTime = System.currentTimeMillis();
        long procEndTime = startTime + maxProcessTime;

        if(logger.isDebugEnabled()) {
            logger
                .debug("PollID[" + pollId + "] SQS[" + queueName + "] begin poll for " + maxEvents);
        }

        ReceiveMessageResult result = sqs.receiveMessage(
            new ReceiveMessageRequest().withQueueUrl(queueUrl)
                .withMaxNumberOfMessages(maxEvents));


        if (!CollectionUtils.isNullOrEmpty(result.getMessages())) {


            int size = result.getMessages().size();
            logger.info("PollID[" + pollId + "] SQS[" + queueName + "] found " + size + " messages");

            Iterator<Message> messages = result.getMessages().iterator();
            while (messages.hasNext() && procEndTime > System.currentTimeMillis()) {

                msgCount++;

                Message message = messages.next();
                String json = message.getBody();
                String messageId = message.getMessageId();

                logger.info("PollID[" + pollId + "] SQS[" + queueName + "] begin processing MessageId["+messageId+"]: msgCount[" + msgCount + "] of [" + size + "]");

                try {
                    long processEventStart = System.currentTimeMillis();

                    // we are commited to this message - delete from queue
                    DeleteMessageResult
                        deleteResult =
                        sqs.deleteMessage(new DeleteMessageRequest().withQueueUrl(queueUrl)
                                              .withReceiptHandle(message.getReceiptHandle()));
                    logger.info("PollID[" + pollId + "] SQS[" + queueName + "] MessageId["+messageId+"] deleted from queue");

                    // attempt to deserialize
                    //Request r = objectMapper.readValue(json, Request.class);
                    EventMessage event = objectMapper.readValue(json, EventMessage.class);

                    logger.info("PollID[" + pollId + "] SQS[" + queueName + "] MessageId["+messageId+"] RequestId[XYZ] successfully deserialized to ...");


                    // pass to handler
                    Optional<EventHandler> eventHandler = eventHandlerRegistry.findEventHandler(
                        event);
                    if (eventHandler.isPresent()) {
                        eventHandler.get().onEvent(messageId, event);
                    } else {
                        // todo: more detailed reason why it failed
                        failed.info("SQS["+queueName+"] MessageID["+messageId+"] PollID["+pollId+"] - No Handler Found: " + json);
                    }

                    logger.info("PollID[" + pollId + "] SQS[" + queueName + "] MessageId["+messageId+"] process completed in " + (System.currentTimeMillis() - processEventStart) + "ms");
                } catch (IOException e) {
                    // formatting issue - this message is dead
                    // todo: log to failed
                    failed.info("SQS["+queueName+"] MessageID["+messageId+"] PollID["+pollId+"]: " + json);
                }
            }

            long endTime = System.currentTimeMillis();
            logger.info("PollID["+pollId+"] SQS["+queueName+"] poll complete. Processed "+msgCount+" records in " + (endTime - startTime) + " ms");

            //queueLogger.info("{} PROCESSING COMPLETE: msg[{} of max {}], endTime[{}], processTime[{}ms]", pollId, msgCount, maxRows, df.format(new Date(endTime)), (endTime - startTime));
        }
    }

}
