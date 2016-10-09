package com.sumo.cloudnative.aggregator

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType

import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.bind.annotation.RequestMethod.GET

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
open class App: CommandLineRunner {
    companion object {
        private val log = LoggerFactory.getLogger(App::class.java)
    }

    override fun run(vararg args: String) {
    }

//    @Bean
//    open fun corsConfigurer(): WebMvcConfigurer = object : WebMvcConfigurerAdapter() {
//        override fun addCorsMappings(registry: CorsRegistry) {
//            //registry.addMapping("/aggregator").allowedOrigins("http://localhost:9000")
//            //registry.addMapping("/aggregator").allowedOrigins("*")
//            registry.addMapping("/**");
//        }
//    }
}

fun main(vararg args: String) {
    SpringApplicationBuilder(App::class.java)
            .registerShutdownHook(true).run(*args)
}


@Configuration
open class WebConfig : WebMvcConfigurerAdapter() {
    override fun addCorsMappings(registry: CorsRegistry) {
        //registry.addMapping("/aggregator").allowedOrigins("http://localhost:9000")
        //registry.addMapping("/aggregator").allowedOrigins("*")
        registry.addMapping("/**");
    }
}

data class Member(
        val id: Integer? = Integer(0),
        val name: String? = null,
        val phone: String? = null
)

@FeignClient("mybatis")
interface MybatisClient {
    @RequestMapping(value = "/", method = arrayOf(GET))
    fun hello(): String

    @RequestMapping(value = "/members", method = arrayOf(GET))
    fun members(): List<Member>
}


@FeignClient("producer")
interface ProducerClient {
    @RequestMapping(value = "/", method = arrayOf(GET))
    fun home(): String
}


@RestController
class AggregatorController @Autowired constructor(val mybatis: MybatisClient, val producer: ProducerClient) {

    @GetMapping("/")
    fun hello(): String {
        return mybatis.hello();
    }

    @GetMapping("members")
    fun members(): List<Member>  {
        return mybatis.members();
    }

    @GetMapping("home")
    fun home(): String  {
        return producer.home();
    }
}
