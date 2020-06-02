package com.ezoneproject.certmgr

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CertmgrApplication

private val log = KotlinLogging.logger {}

fun main(args: Array<String>) {
    runApplication<CertmgrApplication>(*args)
}
