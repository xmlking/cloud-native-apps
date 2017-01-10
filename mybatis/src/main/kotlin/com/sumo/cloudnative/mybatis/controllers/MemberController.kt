package com.sumo.cloudnative.mybatis

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.discovery.DiscoveryClient


@RestController
class MemberController @Autowired constructor(var client: DiscoveryClient, val service: MemberService) {

    @RequestMapping("/")
    fun hello(): String {
        val localInstance = client.localServiceInstance
        return "MyBatis: " + localInstance.serviceId + ":" + localInstance.host + ":" + localInstance.port
    }

    //@RequestMapping("members",  method = arrayOf(RequestMethod.GET))
    @GetMapping("members")
    fun members(): List<Member>  {
        return service.findAll();
    }

    @GetMapping("members/{id}")
    fun member(@PathVariable id: Int): Member {
        print(id)
        return service.findById(id);
    }
}
