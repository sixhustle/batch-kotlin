package com.sixhustle.spring.batchkotlin.job

import mu.KotlinLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

private val log = KotlinLogging.logger {}

@Configuration
class HelloWorldJob(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory
) {

    @Bean
    fun job(): Job {
        return jobBuilderFactory["basicJob"]
                .start(step())
                .build()
    }

    @Bean
    fun step(): Step {
        return stepBuilderFactory["step1"]
                .tasklet { _: StepContribution, _: ChunkContext ->
                    log.info { "Hello, World !" }
                    RepeatStatus.FINISHED
                }
                .build()
    }
}