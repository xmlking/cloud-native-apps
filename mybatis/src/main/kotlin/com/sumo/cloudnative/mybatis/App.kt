package com.sumo.cloudnative.mybatis

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@EnableDiscoveryClient
@SpringBootApplication
open class App: CommandLineRunner {
    companion object {
        private val log = LoggerFactory.getLogger(App::class.java)
    }

    override fun run(vararg args: String) {
    }
}

fun main(vararg args: String) {
    SpringApplicationBuilder(App::class.java)
            .registerShutdownHook(true).run(*args)
}


data class Member(
        val id: Integer,
        val name: String,
        val phone: String
)

@Mapper
public interface MemberMapper {

    @Select("""
        SELECT ID, NAME, PHONE FROM MEMBER
	""")
    fun  findAll(): List<Member>

    @Select("""
        SELECT ID, NAME, PHONE FROM   MEMBER WHERE  ID = #{id}
	""")
    fun  findById(id: Int): Member
}

@Service
class MemberService @Autowired constructor(val mapper: MemberMapper) {
    fun findAll() : List<Member> {
        return mapper.findAll() ;
    }
    fun findById(id: Int) : Member {
        return mapper.findById(id) ;
    }
}

@RestController
class MemberController @Autowired constructor(var client: DiscoveryClient, val service: MemberService) {

    @RequestMapping("/")
    fun hello(): String {
        val localInstance = client.localServiceInstance
        return "MyBatis: " + localInstance.serviceId + ":" + localInstance.host + ":" + localInstance.port
    }

    @RequestMapping("members",  method = arrayOf(RequestMethod.GET))
    fun members(): List<Member>  {
        return service.findAll();
    }

    @RequestMapping("members/{id}", method = arrayOf(RequestMethod.GET))
    fun member(@PathVariable id: Int): Member {
        print(id)
        return service.findById(id);
    }
}
