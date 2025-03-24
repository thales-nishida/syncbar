package br.com.thalesnishida.syncbar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SyncbarApplication

fun main(args: Array<String>) {
    runApplication<SyncbarApplication>(*args)
}
