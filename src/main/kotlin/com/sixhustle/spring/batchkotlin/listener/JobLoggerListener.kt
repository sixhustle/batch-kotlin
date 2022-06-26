package com.sixhustle.spring.batchkotlin.listener

import mu.KotlinLogging
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener

const val START_MESSAGE = "%s is beginning execution"
const val END_MESSAGE = "%s has completed with the status %s"

private val log = KotlinLogging.logger {}

class JobLoggerListener: JobExecutionListener {
    override fun beforeJob(jobExecution: JobExecution) {
        log.info { String.format(START_MESSAGE, jobExecution.jobInstance.jobName) }
    }

    override fun afterJob(jobExecution: JobExecution) {
        log.info { String.format(END_MESSAGE, jobExecution.jobInstance.jobName, jobExecution.status) }
    }
}