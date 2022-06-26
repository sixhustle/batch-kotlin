package com.sixhustle.spring.batchkotlin.job

import com.sixhustle.spring.batchkotlin.incrementer.DailyJobTimeStamper
import com.sixhustle.spring.batchkotlin.listener.JobLoggerListener
import com.sixhustle.spring.batchkotlin.tasklet.HelloWorldTasklet
import com.sixhustle.spring.batchkotlin.validator.ParameterValidator
import mu.KotlinLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersValidator
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.job.CompositeJobParametersValidator
import org.springframework.batch.core.job.DefaultJobParametersValidator
import org.springframework.batch.core.launch.support.RunIdIncrementer
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
                .validator(compositeValidator())
                .incrementer(DailyJobTimeStamper())
                .listener(JobLoggerListener())
                .build()
    }

    @Bean
    fun step1(): Step {
        return stepBuilderFactory["step1"]
                .tasklet(HelloWorldTasklet())
                .build()
    }

    @Bean
    fun compositeValidator(): CompositeJobParametersValidator {
        val defaultValidator = DefaultJobParametersValidator(
                arrayOf("fileName"),
                arrayOf("name", "run.id", "currentDate")
        )

        defaultValidator.afterPropertiesSet()

        val validator = CompositeJobParametersValidator()
        validator.setValidators(arrayListOf(ParameterValidator(), defaultValidator))

        return validator;
    }


}