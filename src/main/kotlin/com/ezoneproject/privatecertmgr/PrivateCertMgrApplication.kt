package com.ezoneproject.privatecertmgr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PrivateCertMgrApplication

fun main(args: Array<String>) {
    runApplication<PrivateCertMgrApplication>(*args)
}
