package com.hly.march2.dao;

import com.hly.march2.entity.Blog;
import com.hly.march2.entity.BlogExample;
import com.hly.march2.vo.BlogStatisticsVO;
import com.hly.march2.vo.BlogUserVo;
import com.hly.march2.vo.HomeSummaryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

public interface BlogMapper {
    long countByExample(BlogExample example);

    int deleteByExample(BlogExample example);

    int deleteByPrimaryKey(Long blogId);

    int insert(Blog record);

    int insertSelective(Blog record);

    List<Blog> selectByExampleWithBLOBs(BlogExample example);

    List<Blog> selectByExample(BlogExample example);

    Blog selectByPrimaryKey(Long blogId);

    int updateByExampleSelective(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByExampleWithBLOBs(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByExample(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKeyWithBLOBs(Blog record);

    int updateByPrimaryKey(Blog record);

    // new
    List<BlogUserVo> selectBlogUserByExample(BlogExample example);

    List<BlogUserVo> selectBlogUserByExampleWithBLOBs(BlogExample example);

    BlogUserVo selectBlogUserByPrimaryKey(Long blogId);

    BlogUserVo selectBlogUserByPrimaryKeyWithBLOBs(Long blogId);

    BlogStatisticsVO getBlogStatisticsByExample(BlogExample example);

    HomeSummaryVO countSumBlogByExample(BlogExample example);

    List<BlogUserVo>  selectBlogSeriesTypeByExample(BlogExample example);

    Integer updateBatchBlogByBlogId(@Param("list") List<Blog> blogList);

}