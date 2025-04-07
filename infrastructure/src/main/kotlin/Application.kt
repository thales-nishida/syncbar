package br.com.thalesnishida.syncbar.infrastructure

import br.com.thalesnishida.syncbar.infrastructure.configuration.WebServiceConfig
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.core.env.AbstractEnvironment

@SpringBootApplication
class Application

fun main(args: Array<String>) {
//    System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "development")
    System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "development")
    SpringApplication.run(WebServiceConfig::class.java, *args)
}
