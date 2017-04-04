package com.j2clark.aws.handler;

import com.amazonaws.services.sqs.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.j2clark.aws.domain.Request;
import com.j2clark.aws.sqs.SQSQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Semaphore;

public class PollWorker implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Logger failed = LoggerFactory.getLogger("failed");


    private final Semaphore workerManager;
    private final SQSQueue sqs;
    private final int maxEvents;
    private final long maxProcessTime;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RequestHandlerRegistry requestHandlerRegistry;

    public static PollWorker of(final Semaphore workerManager,
                                final SQSQueue sqs,
                                final int maxEvents,
                                final long maxProcessTime,
                                final RequestHandlerRegistry requestHandlerRegistry) {
        return new PollWorker(workerManager, sqs, maxEvents, maxProcessTime, requestHandlerRegistry);
    }

    public PollWorker(final Semaphore workerManager,
                      final SQSQueue sqs,
                      final int maxEvents,
                      final long maxProcessTime,
                      final RequestHandlerRegistry requestHandlerRegistry) {
        this.workerManager = workerManager;
        this.sqs = sqs;
        this.maxEvents = maxEvents;
        this.maxProcessTime = maxProcessTime;
        this.requestHandlerRegistry = requestHandlerRegistry;
    }

    @Override
    public final void run() {
        try {
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
                .debug("PollID[" + pollId + "] SQSQueue[" + sqs.name() + "] begin poll for " + maxEvents);
        }

        Collection<Message> messages = sqs.retrieveMessages(maxEvents);
        if(!messages.isEmpty()) {


            int size = messages.size();
            logger.info("PollID[" + pollId + "] SQSQueue[" + sqs.name() + "] found " + size + " messages");

            Iterator<Message> it = messages.iterator();
            while (it.hasNext() && procEndTime > System.currentTimeMillis()) {

                msgCount++;

                Message message = it.next();
                String json = message.getBody();
                String messageId = message.getMessageId();

                logger.info("PollID[" + pollId + "] SQSQueue[" + sqs.name() + "] begin processing MessageId["+messageId+"]: msgCount[" + msgCount + "] of [" + size + "]");

                try {
                    long processEventStart = System.currentTimeMillis();

                    // we are commited to this message - delete from queue
                    sqs.deleteMessage(message);
                    logger.info("PollID[" + pollId + "] SQSQueue[" + sqs.name() + "] MessageId["+messageId+"] deleted from queue");

                    // attempt to deserialize
                    Request r = objectMapper.readValue(json, Request.class);
                    //EventMessage event = objectMapper.readValue(json, EventMessage.class);

                    logger.info("PollID[" + pollId + "] SQSQueue[" + sqs.name() + "] MessageId["+messageId+"] RequestId["+r.getTransactionId()+"] successfully deserialized");


                    // pass to handler
                    Optional<RequestHandler> eventHandler = requestHandlerRegistry.findEventHandler(r);
                    if (eventHandler.isPresent()) {
                        eventHandler.get().onEvent(messageId, r);
                    } else {
                        // todo: more detailed reason why it failed
                        failed.info("SQSQueue["+sqs.name()+"] MessageID["+messageId+"] PollID["+pollId+"] - No Handler Found: " + json);
                    }

                    logger.info("PollID[" + pollId + "] SQSQueue[" + sqs.name() + "] MessageId["+messageId+"] process completed in " + (System.currentTimeMillis() - processEventStart) + "ms");
                } catch (IOException e) {
                    // formatting issue - this message is dead
                    // todo: log to failed
                    failed.info("SQSQueue["+sqs.name()+"] MessageID["+messageId+"] PollID["+pollId+"]: " + json);
                }
            }

            long endTime = System.currentTimeMillis();
            logger.info("PollID["+pollId+"] SQSQueue["+sqs.name()+"] poll complete. Processed "+msgCount+" records in " + (endTime - startTime) + " ms");
        }
    }

}
