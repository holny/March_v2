package com.hly.march2.dao;

import com.hly.march2.entity.Draft;
import com.hly.march2.entity.DraftExample;
import com.hly.march2.vo.BlogUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

public interface DraftMapper {
    long countByExample(DraftExample example);

    int deleteByExample(DraftExample example);

    int deleteByPrimaryKey(Long blogId);

    int insert(Draft record);

    int insertSelective(Draft record);

    List<Draft> selectByExampleWithBLOBs(DraftExample example);

    List<Draft> selectByExample(DraftExample example);

    Draft selectByPrimaryKey(Long blogId);

    int updateByExampleSelective(@Param("record") Draft record, @Param("example") DraftExample example);

    int updateByExampleWithBLOBs(@Param("record") Draft record, @Param("example") DraftExample example);

    int updateByExample(@Param("record") Draft record, @Param("example") DraftExample example);

    int updateByPrimaryKeySelective(Draft record);

    int updateByPrimaryKeyWithBLOBs(Draft record);

    int updateByPrimaryKey(Draft record);

    // new
    List<BlogUserVo> selectDraftUserByExample(DraftExample example);

    List<BlogUserVo> selectDraftUserByExampleWithBLOBs(DraftExample example);

    BlogUserVo selectDraftUserByPrimaryKey(Long blogId);

    BlogUserVo selectDraftUserByPrimaryKeyWithBLOBs(Long blogId);

}