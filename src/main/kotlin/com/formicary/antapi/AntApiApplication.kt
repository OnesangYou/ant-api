package com.formicary.antapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AntApiApplication

fun main(args: Array<String>) {
	runApplication<AntApiApplication>(*args)
}
