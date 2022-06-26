package com.sixhustle.spring.batchkotlin

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableBatchProcessing
@SpringBootApplication
class BatchKotlinApplication

fun main(args: Array<String>) {
	runApplication<BatchKotlinApplication>(*args)
}
