package com.hly.march2.dao;

import com.hly.march2.entity.Sessions;
import com.hly.march2.entity.SessionsExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

public interface SessionsMapper {
    long countByExample(SessionsExample example);

    int deleteByExample(SessionsExample example);

    int deleteByPrimaryKey(String id);

    int insert(Sessions record);

    int insertSelective(Sessions record);

    List<Sessions> selectByExample(SessionsExample example);

    Sessions selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Sessions record, @Param("example") SessionsExample example);

    int updateByExample(@Param("record") Sessions record, @Param("example") SessionsExample example);

    int updateByPrimaryKeySelective(Sessions record);

    int updateByPrimaryKey(Sessions record);
}