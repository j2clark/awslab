package com.j2clark.aws.handler;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Service
public class QueuePoller {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    //private final EventHandler eventHandler;
    private final EventHandlerRegistry eventHandlerRegistry;
    private final AmazonSQS sqs;
    private final String queueName;
    private final String queueUrl;
    private final int maxEvents;
    private final long maxProcessTime;

    //private final ObjectMapper objectMapper = new ObjectMapper();

    private final ExecutorService pollExecutor;
    private final Semaphore workerManager;

    public QueuePoller(@Value("${poller.maxThreads:10}") int maxThreads,
                       @Value("${poller.maxEvents:100}") int maxEvents,
                       @Value("${poller.maxProcessTime:150000}") long maxProcessTime,
                       @Value("${resource.queuename:tmail-resource}") final String queueName,
                       final AmazonSQS sqs,
                       final EventHandlerRegistry eventHandlerRegistry) {
        this.maxEvents = maxEvents;
        this.maxProcessTime = maxProcessTime;
        this.pollExecutor = Executors.newFixedThreadPool(maxThreads);
        this.workerManager = new Semaphore(maxThreads);

        // fail fast - if we do not find the queue, we should throw up
        logger.info("look up SQS QueueURL for queueName["+queueName+"]...");
        this.queueUrl = getQueueUrl(sqs, queueName);
        logger.info("SQS["+queueUrl+"] found for queueName["+queueName+"]");
        this.queueName = queueName;
        this.sqs = sqs;
        this.eventHandlerRegistry = eventHandlerRegistry;

        // todo: find queueUrl to verify queue exisists and we have access
    }

    @Scheduled(
        initialDelayString = "${poller.initialDelay:1000}",
        fixedDelayString = "${poller.fixedDelay:500}"
    )
    public void pollQueue() {
        //if (dynamicProperties.isResourceQueueEnabled()) {
            try {
                if (workerManager.tryAcquire(100, TimeUnit.MILLISECONDS)) {
                    pollExecutor.execute(
                        PollWorker.of(workerManager, sqs, queueName, queueUrl, maxEvents, maxProcessTime, eventHandlerRegistry)
                    );
                }
            } catch (InterruptedException e) {
                logger.info("Interrupt exception. Not aborting!", e);
            }
        //} else {
            // only warn if service has been explicitly disabled
        //    logger.warn("RESOURCE QUEUE PROCESSING IS DISABLED ON THIS SERVER");
       ///}
    }

    protected String getQueueUrl(final AmazonSQS sqs, String queueName){
        GetQueueUrlRequest getQueueUrlRequest = new GetQueueUrlRequest(queueName);
        return sqs.getQueueUrl(getQueueUrlRequest).getQueueUrl();
    }

}
