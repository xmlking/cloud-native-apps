package com.sumo.cloudnative.mybatis

import org.slf4j.LoggerFactory

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

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

