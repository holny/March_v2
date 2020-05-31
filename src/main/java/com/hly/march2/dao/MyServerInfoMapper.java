package com.hly.march2.dao;

import com.hly.march2.entity.MyServerInfo;
import com.hly.march2.entity.MyServerInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

public interface MyServerInfoMapper {
    long countByExample(MyServerInfoExample example);

    int deleteByExample(MyServerInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MyServerInfo record);

    int insertSelective(MyServerInfo record);

    List<MyServerInfo> selectByExample(MyServerInfoExample example);

    MyServerInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MyServerInfo record, @Param("example") MyServerInfoExample example);

    int updateByExample(@Param("record") MyServerInfo record, @Param("example") MyServerInfoExample example);

    int updateByPrimaryKeySelective(MyServerInfo record);

    int updateByPrimaryKey(MyServerInfo record);

    int finalUpdateByPrimaryKeySelective(MyServerInfo record);

    int periodUpdateByPrimaryKeySelective(MyServerInfo record);
}