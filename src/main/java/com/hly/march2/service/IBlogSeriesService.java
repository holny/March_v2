package com.hly.march2.service;

import com.hly.march2.entity.BlogSeries;
import com.hly.march2.entity.Draft;
import com.hly.march2.entity.Msg;
import com.hly.march2.vo.SeriesVO;

import java.util.List;

public interface IBlogSeriesService {
    Long insertSeriesByInitial(String seriesName,Long userId,Integer type,String intro);

    List<BlogSeries> getSeriesBySeriesNameAndUserId(String seriesName,Long userId);

    List<BlogSeries> getSeriesByUserId(Long userId);

    void insertSeriesWithCheck(Draft draft, BlogSeries blogSeries);

    List<BlogSeries> getSeriesByUserIdAndStatus(Long userId, List<Integer> statusList);

    List<BlogSeries> getSeriesByStatus(List<Integer> statusList);

    Long countTotalSeriesByStatus(List<Integer> statusList);

    List<SeriesVO> getSeriesCountGroupByBlogStatus(List<Integer> statusList);

    SeriesVO getSeriesBlogByBlogStatus(List<Integer> statusList,Long seriesId);

    List<BlogSeries> getSeriesBySeriesIdAndStatus(Long seriesId, List<Integer> statusList);

    List<BlogSeries> getSeriesByLikeSeriesName(String seriesName);

    BlogSeries getSeriesBySeriesId(Long seriesId);

    Msg updateSeriesWithCheck(BlogSeries blogSeries,Long userId);

    int deleteSeriesBySeriesId(Long seriesId);

    int deleteSeriesBySeriesIdAndUserId(Long seriesId,Long userId);

    int combinationSeriesBySeriesId(Long oldSeriesId,Long newSeriesId);
}
