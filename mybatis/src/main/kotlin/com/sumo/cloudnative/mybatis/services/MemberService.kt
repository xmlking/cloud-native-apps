package com.sumo.cloudnative.mybatis

import org.slf4j.LoggerFactory

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.sleuth.Span
import org.springframework.cloud.sleuth.Tracer

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
class MemberService @Autowired constructor(val mapper: MemberMapper,
                                           var tracer: Tracer) {
    fun findAll() : List<Member> {
        return mapper.findAll() ;
    }
    fun findById(id: Int) : Member {
        var span = tracer.createSpan("Lookup member");

        tracer.addTag("memberId", id.toString());

        span.logEvent("DB query started");

        var member = mapper.findById(id) ;

        span.logEvent("DB query ended");

        tracer.close(span);

        return member ;
    }
}

