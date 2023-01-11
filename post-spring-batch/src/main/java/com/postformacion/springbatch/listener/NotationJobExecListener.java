package com.postformacion.springbatch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.stereotype.Component;

@Component
public class NotationJobExecListener implements JobExecutionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotationJobExecListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {}

    @Override
    public void afterJob(JobExecution jobExecution) {
        LOGGER.info("afterJob:" + jobExecution.getStatus());
    }
}
