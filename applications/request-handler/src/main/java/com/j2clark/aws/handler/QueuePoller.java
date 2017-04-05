package com.j2clark.aws.handler;

import com.j2clark.aws.sqs.SQSQueue;
import com.j2clark.aws.sqs.SQSFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final RequestHandlerRegistry requestHandlerRegistry;
    private final SQSQueue sqs;
    private final int maxEvents;
    private final long maxProcessTime;
    private final ExecutorService pollExecutor;
    private final Semaphore workerManager;

    @Autowired
    public QueuePoller(@Value("${poller.maxThreads:10}") int maxThreads,
                       @Value("${poller.maxEvents:10}") int maxEvents, // max value for SQSQueue is 10
                       @Value("${poller.maxProcessTime:150000}") long maxProcessTime,
                       @Value("${resource.queuename:tmail-resource}") final String queueName,
                       final SQSFactory sqsFactory,
                       final RequestHandlerRegistry requestHandlerRegistry) {
        if (maxEvents > 10 || maxEvents < 1) {
            throw new IllegalArgumentException("SQSQueue maxEvents must be a value between 1 and 10, inclusive");
        }
        this.maxEvents = maxEvents;
        this.maxProcessTime = maxProcessTime;
        this.pollExecutor = Executors.newFixedThreadPool(maxThreads);
        this.workerManager = new Semaphore(maxThreads);

        // fail fast - sqsFactory will throw exception if queue not found, and forceCreate is false
        this.sqs = sqsFactory.of(queueName);
        this.requestHandlerRegistry = requestHandlerRegistry;

        // todo: find queueUrl to verify queue exists and we have access
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
                        PollWorker.of(workerManager, sqs, maxEvents, maxProcessTime,
                                      requestHandlerRegistry)
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
}
