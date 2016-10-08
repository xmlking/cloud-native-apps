package com.sumo.cloudnative.mybatis

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.cloud.client.discovery.EnableDiscoveryClient



@EnableDiscoveryClient
@SpringBootApplication
open class App: CommandLineRunner {
    companion object {
        private val log = LoggerFactory.getLogger(App::class.java)
    }

    override fun run(vararg args: String) {
    }
}

//@JvmStatic fun main(args: Array<String>) {
fun main(vararg args: String) {
    SpringApplicationBuilder(App::class.java)
            .registerShutdownHook(true).run(*args)
}
