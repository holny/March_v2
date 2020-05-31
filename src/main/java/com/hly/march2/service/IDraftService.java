package com.hly.march2.service;

import com.hly.march2.dto.BlogSearchDTO;
import com.hly.march2.entity.Draft;
import com.hly.march2.entity.Msg;
import com.hly.march2.vo.BlogUserVo;

import java.util.List;

public interface IDraftService {

    BlogUserVo getDraftUserByBlogIdWithBLOBs(Long blogId);

    List<BlogUserVo> getDraftUserByUserId(Long userId);

    List<Draft> getDraftByBlogIdAndUserIdWithBLOBs(Long blogId, Long userId);

    Draft getDraftByBlogIdWithBLOBs(Long blogId);

    List<Draft> getDraftByBlogI(Long blogId);

    List<Draft> getDraftByBlogIdAndUserId(Long blogId, Long userId);

    int updateByPrimaryKey(Draft blog);

    int insertDraft(Draft blog);

    int deleteByPrimaryKey(Long blogId);

    List<BlogUserVo> getDraftUserByBlogIdAndStatus(Long userId,Integer status);

    Msg updateDraftWithCheck(Draft draft);

    Msg insertDraftWithCheck(Draft draft);

    List<BlogUserVo> getDraftUserBySearch(BlogSearchDTO blogSearchDTO);

    List<Draft> getDraftByUserIdAndStatus(Long userId, List<Integer> statusList);

    Integer updateSeriesIdByUserIdAndSeriesId(Draft draft, Long userId, Long seriesId);

    Integer updateSeriesIdBySeriesId(Draft draft,Long seriesId);

    Msg updatePreviewByBlogId(Draft draft);

    Draft updateAndGetNewSelectiveDraftByBlogId(Draft draft);
}
