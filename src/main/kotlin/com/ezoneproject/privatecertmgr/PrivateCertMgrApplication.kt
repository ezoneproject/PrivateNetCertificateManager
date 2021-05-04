package com.ezoneproject.privatecertmgr

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PrivateCertMgrApplication

private val log = KotlinLogging.logger {}

fun main(args: Array<String>) {
    runApplication<PrivateCertMgrApplication>(*args)
}
