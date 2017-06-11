package com.sumo.cloudnative.mybatis

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod


@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
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

@FeignClient("GetCaption")
interface CaptionClient {
    @RequestMapping(value = "/caption", method = arrayOf(RequestMethod.GET))
    fun caption(): String
}

@FeignClient("GetInterests")
interface IntClient {
    @RequestMapping(value = "/getInterest", method = arrayOf(RequestMethod.GET))
    fun int(): String
}

@FeignClient("GetAddress")
interface AddClient {
    @RequestMapping(value = "/add", method = arrayOf(RequestMethod.GET))
    fun add(): String
}