package com.hly.march2.service.impl;

import com.hly.march2.controller.BlogController;
import com.hly.march2.dao.BlogSeriesMapper;
import com.hly.march2.entity.*;
import com.hly.march2.service.IBlogSeriesService;
import com.hly.march2.service.IBlogService;
import com.hly.march2.service.IDraftService;
import com.hly.march2.utils.DateUtils;
import com.hly.march2.utils.RedisUtils;
import com.hly.march2.vo.SeriesVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BlogSeriesServiceImpl implements IBlogSeriesService {
    private static final Logger log = LoggerFactory.getLogger(BlogSeriesServiceImpl.class);
    @Autowired
    @Lazy
    private BlogSeriesMapper blogSeriesMapper;

    @Autowired
    @Lazy
    private IBlogService blogService;

    @Autowired
    @Lazy
    private IDraftService draftService;

    @Override
    public Long insertSeriesByInitial(String seriesName, Long userId, Integer type, String intro) {
        Date now = DateUtils.getCurrentDateTime();
        BlogSeries blogSeries = new BlogSeries();
        blogSeries.setSeriesName(seriesName);
        blogSeries.setUserId(userId);
        blogSeries.setSeriesCreateTime(now);
        blogSeries.setSeriesUpdateTime(now);
        blogSeries.setSeriesNum(1);
        blogSeries.setSeriesStatus(BlogController.BlogStatusEnum.NORMAL_STATUS.getCode());
        blogSeries.setSeriesType(type);
        blogSeries.setSeriesIntro(intro);
        blogSeriesMapper.insertSelective(blogSeries);
        return blogSeries.getSeriesId();
    }

    @Override
    public List<BlogSeries> getSeriesBySeriesNameAndUserId(String seriesName, Long userId) {
        BlogSeriesExample example = new BlogSeriesExample();
        BlogSeriesExample.Criteria criteria = example.createCriteria();
        criteria.andSeriesNameEqualTo(seriesName);
        criteria.andUserIdEqualTo(userId);
        return blogSeriesMapper.selectByExample(example);
    }

    @Override
    public List<BlogSeries> getSeriesByUserId(Long userId) {
        BlogSeriesExample example = new BlogSeriesExample();
        BlogSeriesExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);

        return blogSeriesMapper.selectByExample(example);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSeriesWithCheck(Draft draft, BlogSeries blogSeries) {

        if (draft.getNewSeries() != null) {
            List<BlogSeries> blogSeriesList = getSeriesBySeriesNameAndUserId(draft.getNewSeries().trim(), draft.getUserId());
            if (blogSeriesList.size() > 0) {
                // 如果用户id名下已有同名series，拿过来替换
                draft.setNewSeries(null);
                blogSeries.setSeriesId(blogSeriesList.get(0).getSeriesId());
            } else {
                // 如果没有就要新建
                Date now = DateUtils.getCurrentDateTime();
                blogSeries.setSeriesName(draft.getNewSeries().trim());
                blogSeries.setUserId(draft.getUserId());
                blogSeries.setSeriesCreateTime(now);
                blogSeries.setSeriesUpdateTime(now);
                blogSeries.setSeriesNum(1);
                blogSeries.setSeriesStatus(BlogController.BlogStatusEnum.NORMAL_STATUS.getCode());
                blogSeries.setSeriesType(draft.getBlogType());
                blogSeries.setSeriesIntro(draft.getBlogIntro());
                blogSeriesMapper.insertSelective(blogSeries);
                draft.setNewSeries(null);
            }
        }
    }

    @Override
    public List<BlogSeries> getSeriesByUserIdAndStatus(Long userId, List<Integer> statusList) {
        BlogSeriesExample example = new BlogSeriesExample();
        BlogSeriesExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andSeriesStatusIn(statusList);
        return blogSeriesMapper.selectByExample(example);
    }

    @Override
    public List<BlogSeries> getSeriesByStatus(List<Integer> statusList) {
        BlogSeriesExample example = new BlogSeriesExample();
        BlogSeriesExample.Criteria criteria = example.createCriteria();
        criteria.andSeriesStatusIn(statusList);
        return blogSeriesMapper.selectByExample(example);
    }

    @Override
    public Long countTotalSeriesByStatus(List<Integer> statusList) {
        BlogSeriesExample example = new BlogSeriesExample();
        BlogSeriesExample.Criteria criteria = example.createCriteria();
        criteria.andSeriesStatusIn(statusList);
        return blogSeriesMapper.countByExample(example);
    }

    @Override
    public List<SeriesVO> getSeriesCountGroupByBlogStatus(List<Integer> statusList) {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andBlogStatusIn(statusList);
        criteria.andSeriesIdGreaterThan(new Long(0));
        example.setOrderByClause("view_sum desc LIMIT 10");
        return blogSeriesMapper.getSeriesCountByBlogExample(example);
    }

    @Override
    public SeriesVO getSeriesBlogByBlogStatus(List<Integer> statusList, Long seriesId) {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andBlogStatusIn(statusList);
        criteria.andSeriesIdEqualTo(seriesId);
        example.setOrderByClause("blog_views desc");
        return blogSeriesMapper.selectSeriesBlogByBlogExample(example);
    }

    @Override
    public List<BlogSeries> getSeriesBySeriesIdAndStatus(Long seriesId, List<Integer> statusList) {
        BlogSeriesExample example = new BlogSeriesExample();
        BlogSeriesExample.Criteria criteria = example.createCriteria();
        criteria.andSeriesStatusIn(statusList);
        criteria.andSeriesIdEqualTo(seriesId);
        return blogSeriesMapper.selectByExample(example);
    }

    @Override
    public List<BlogSeries> getSeriesByLikeSeriesName(String seriesName) {
        BlogSeriesExample example = new BlogSeriesExample();
        BlogSeriesExample.Criteria criteria = example.createCriteria();
        criteria.andSeriesNameLike("%" + seriesName.trim() + "%");
        return blogSeriesMapper.selectByExample(example);
    }

    @Override
    public BlogSeries getSeriesBySeriesId(Long seriesId) {
        return blogSeriesMapper.selectByPrimaryKey(seriesId);
    }

    @Override
    @Transactional(readOnly = false)
    public Msg updateSeriesWithCheck(BlogSeries blogSeries,Long userId) {
        // 这里进来默认这个要更新的series就属于此userId
        Date now = DateUtils.getCurrentDateTime();
        if(blogSeries.getNewSeriesId()!=null&&!blogSeries.getNewSeriesId().equals(blogSeries.getSeriesId())){
            BlogSeries targetSeries = getSeriesBySeriesId(blogSeries.getNewSeriesId());
            if(targetSeries!=null&&targetSeries.getUserId().equals(userId)) {
//                deleteSeriesBySeriesId(blogSeries.getSeriesId());
                combinationSeriesBySeriesId(blogSeries.getSeriesId(),targetSeries.getSeriesId());

                blogSeries.setSeriesId(targetSeries.getSeriesId());
                blogSeries.setNewSeriesName(null);
                blogSeries.setSeriesName(null);
                blogSeries.setSeriesShortName(null);
                blogSeries.setSeriesStatus(null);
                blogSeries.setNewSeriesId(null);
                blogSeries.setSeriesUpdateTime(now);
                blogSeriesMapper.updateByPrimaryKeySelective(blogSeries);
                return Msg.success().setMsg("合并成功!").add("newSeriesId",blogSeries.getSeriesId());
            }else{
                return Msg.fail().setMsg("不存在此目标Series");
            }
        }
        blogSeries.setSeriesName(null);
        if(blogSeries.getNewSeriesName()!=null){
            blogSeries.setSeriesName(blogSeries.getNewSeriesName());
        }
        blogSeries.setUserId(userId);
        blogSeries.setNewSeriesName(null);
        blogSeries.setNewSeriesId(null);
        blogSeries.setSeriesUpdateTime(now);
        blogSeriesMapper.updateByPrimaryKeySelective(blogSeries);
        return  Msg.success().setMsg("更新成功!").add("newSeriesId",blogSeries.getSeriesId());
    }

    @Override
    @Transactional(readOnly = false)
    public int deleteSeriesBySeriesId(Long seriesId) {
        Date now = DateUtils.getCurrentDateTime();
        Blog blog = new Blog();
        blog.setSeriesId(new Long(-1));
        blog.setBlogUpdateTime(now);
        blogService.updateSeriesIdBySeriesId(blog,seriesId);
        Draft draft = new Draft();
        draft.setSeriesId(new Long(-1));
        draft.setBlogUpdateTime(now);
        draftService.updateSeriesIdBySeriesId(draft,seriesId);
        return blogSeriesMapper.deleteByPrimaryKey(seriesId);
    }

    @Override
    @Transactional(readOnly = false)
    public int deleteSeriesBySeriesIdAndUserId(Long seriesId, Long userId) {
        BlogSeriesExample example = new BlogSeriesExample();
        BlogSeriesExample.Criteria criteria = example.createCriteria();
        criteria.andSeriesIdEqualTo(seriesId);
        criteria.andUserIdEqualTo(userId);

        Date now = DateUtils.getCurrentDateTime();
        Blog blog = new Blog();
        blog.setSeriesId(new Long(-1));
        blog.setBlogUpdateTime(now);
        blogService.updateSeriesIdByUserIdAndSeriesId(blog,userId,seriesId);
        Draft draft = new Draft();
        draft.setSeriesId(new Long(-1));
        draft.setBlogUpdateTime(now);
        draftService.updateSeriesIdByUserIdAndSeriesId(draft,userId,seriesId);
        return blogSeriesMapper.deleteByExample(example);
    }

    @Override
    public int combinationSeriesBySeriesId(Long oldSeriesId, Long newSeriesId) {
        Date now = DateUtils.getCurrentDateTime();
        Blog blog = new Blog();
        blog.setSeriesId(newSeriesId);
        blog.setBlogUpdateTime(now);
        blogService.updateSeriesIdBySeriesId(blog,oldSeriesId);
        Draft draft = new Draft();
        draft.setSeriesId(newSeriesId);
        draft.setBlogUpdateTime(now);
        draftService.updateSeriesIdBySeriesId(draft,oldSeriesId);
        return blogSeriesMapper.deleteByPrimaryKey(oldSeriesId);
    }
}
