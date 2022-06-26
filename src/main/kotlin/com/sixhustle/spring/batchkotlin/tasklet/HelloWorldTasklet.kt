package com.sixhustle.spring.batchkotlin.tasklet

import mu.KotlinLogging
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus

private val log = KotlinLogging.logger {}

class HelloWorldTasklet: Tasklet {
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        val parameters = chunkContext.stepContext
                .jobParameters
        val name = parameters["name"]
        val fileName = parameters["fileName"]
        log.info { String.format("Hello, %s!", name) }
        log.info { String.format("FileName, %s", fileName) }
        return RepeatStatus.FINISHED
    }
}