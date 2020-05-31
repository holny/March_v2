package com.hly.march2.dao;

import com.hly.march2.entity.BlogExample;
import com.hly.march2.entity.BlogSeries;
import com.hly.march2.entity.BlogSeriesExample;
import com.hly.march2.vo.SeriesVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

public interface BlogSeriesMapper {
    long countByExample(BlogSeriesExample example);

    int deleteByExample(BlogSeriesExample example);

    int deleteByPrimaryKey(Long seriesId);

    int insert(BlogSeries record);

    int insertSelective(BlogSeries record);

    List<BlogSeries> selectByExample(BlogSeriesExample example);

    BlogSeries selectByPrimaryKey(Long seriesId);

    int updateByExampleSelective(@Param("record") BlogSeries record, @Param("example") BlogSeriesExample example);

    int updateByExample(@Param("record") BlogSeries record, @Param("example") BlogSeriesExample example);

    int updateByPrimaryKeySelective(BlogSeries record);

    int updateByPrimaryKey(BlogSeries record);

    List<SeriesVO> getSeriesCountByBlogExample(BlogExample example);

    SeriesVO selectSeriesBlogByBlogExample(BlogExample example);

}