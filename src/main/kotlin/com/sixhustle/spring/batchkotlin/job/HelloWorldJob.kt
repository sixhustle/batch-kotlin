package com.sixhustle.spring.batchkotlin.job

import com.sixhustle.spring.batchkotlin.tasklet.HelloWorldTasklet
import mu.KotlinLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersValidator
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.job.DefaultJobParametersValidator
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.scope.context.StepContext
import org.springframework.batch.core.step.tasklet.Tasklet
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
                .start(step1())
                .validator(validator())
                .build()
    }

    @Bean
    fun step1(): Step {
        return stepBuilderFactory["step1"]
                .tasklet(HelloWorldTasklet())
                .build()
    }

    @Bean
    fun validator(): JobParametersValidator {
        val validator = DefaultJobParametersValidator()
        validator.setRequiredKeys(arrayOf("fileName"))
        validator.setOptionalKeys(arrayOf("name"))
        return validator
    }
}