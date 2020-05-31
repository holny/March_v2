package com.hly.march2.service.impl;

import com.hly.march2.controller.BlogController;
import com.hly.march2.dao.BlogMapper;
import com.hly.march2.dto.BlogSearchDTO;
import com.hly.march2.entity.*;
import com.hly.march2.service.*;
import com.hly.march2.utils.DateUtils;
import com.hly.march2.utils.WrappedBeanCopier;
import com.hly.march2.vo.BlogStatisticsVO;
import com.hly.march2.vo.BlogUserVo;
import com.hly.march2.vo.HomeSummaryVO;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BlogServiceImpl implements IBlogService {
    private static final Logger log = LoggerFactory.getLogger(BlogServiceImpl.class);
    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private IBlogSeriesService blogSeriesService;

    @Autowired
    private IDraftService draftService;

    @Autowired
    private IUserService userService;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    /**
     * 获取所有博文Brief，最近更新优先
     * @return
     */
    @Override
    public List<BlogUserVo> getLatestBlogUserByStatusAndLimit(List<Integer> statusList,Integer limit) {
        Date before_month = DateUtils.minusMonths(1);
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andBlogStatusIn(statusList);
        String order = "blog_update_time desc";
        if(limit!=null&&limit>0){
            order += " limit "+limit;
        }
        criteria.andBlogUpdateTimeGreaterThanOrEqualTo(before_month);
        example.setOrderByClause(order);
        return blogMapper.selectBlogUserByExample(example);
    }

    @Override
    public List<BlogUserVo> getMostHotBlogUserByStatusAndLimit(List<Integer> statusList, Integer limit) {
        Date before_month = DateUtils.minusMonths(1);
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andBlogStatusIn(statusList);
        String order = "blog_views desc";
        if(limit!=null&&limit>0){
            order += " limit "+limit;
        }
        criteria.andBlogUpdateTimeGreaterThanOrEqualTo(before_month);
        example.setOrderByClause(order);
        return blogMapper.selectBlogUserByExample(example);
    }

    @Override
    public List<BlogUserVo> getBlogUserByBlogIdAndStatusWithBLOBs(Long blogId, List<Integer> statusList) {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andBlogIdEqualTo(blogId);
        criteria.andBlogStatusIn(statusList);
        return blogMapper.selectBlogUserByExampleWithBLOBs(example);
    }

    /**
     * 获取单个博文
     * @return
     */
    @Override
    public BlogUserVo getBlogUserByBlogIdWithBLOBs(Long blogId) {

        return blogMapper.selectBlogUserByPrimaryKeyWithBLOBs(blogId);
    }

    @Override
    public List<BlogUserVo> getBlogUserByUserId(Long userId) {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        example.setOrderByClause("b.blog_update_time desc");
        return blogMapper.selectBlogUserByExample(example);
    }

    /**
     * 根据博客Id和用户Id，确认符合条件的博客,只返回第一条。
     * 之所以没用count，我认为Count要搜索整个数据库，而加了limit，搜到第一条就返回
     * @param blogId
     * @param userId
     * @return
     */

    @Override
    public List<Blog> getBlogByBlogIdAndUserIdWithBLOBs(Long blogId, Long userId) {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andBlogIdEqualTo(blogId);
        criteria.andUserIdEqualTo(userId);
        example.setOrderByClause("blog_id limit 1");
        return blogMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public Blog getBlogByBlogIdWithBLOBs(Long blogId) {
        return blogMapper.selectByPrimaryKey(blogId);
    }

    @Override
    public List<Blog> getBlogByBlogId(Long blogId) {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andBlogIdEqualTo(blogId);
        return blogMapper.selectByExample(example);
    }

    @Override
    public List<Blog> getBlogByBlogIdAndUserId(Long blogId, Long userId) {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andBlogIdEqualTo(blogId);
        criteria.andUserIdEqualTo(userId);
        return blogMapper.selectByExample(example);
    }

    /**
     * 更新blog
     * @param blog
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKey(Blog blog){
        return blogMapper.updateByPrimaryKeySelective(blog);
    }

    /**
     * 获取SQL返回的自增id，别再service层获取，因为事务没完成是获取不到的，返回Null
     * @param blog
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public int insertBlog(Blog blog) {
        return  blogMapper.insertSelective(blog);
    }

    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(Long blogId) {
        return blogMapper.deleteByPrimaryKey(blogId);
    }

    @Override
    public BlogStatisticsVO getUserSUMByUserId(Long userId) {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return blogMapper.getBlogStatisticsByExample(example);
    }


    @Override
    @Transactional(readOnly = false)
    public Msg updateBlogWithCheck(Blog blog) {
        if(!BlogController.BlogStatusEnum.getBlogTableList().contains(blog.getBlogStatus())){
            return Msg.fail().setMsg("参数错误!");
        }
        Blog testBlog = null;
        if("draft".equals(blog.getSourceFrom())){
            if(blog.getOriginalBlogId()!=null&&blog.getOriginalBlogId()>0) {
                testBlog = blogMapper.selectByPrimaryKey(blog.getOriginalBlogId());
            }
        }else{
            if(blog.getBlogId()!=null&&blog.getBlogId()>0) {
                testBlog = blogMapper.selectByPrimaryKey(blog.getBlogId());
            }
        }
        if (testBlog != null) {
            Date now = DateUtils.getCurrentDateTime();
            blog.setBlogUpdateTime(now);
            blog.setBlogCreateTime(null);
            if (testBlog.getUserId().equals(blog.getUserId())) {
                // 发布表存在数据并且匹配, 说明blogId有效。就Update
                if("draft".equals(blog.getSourceFrom())){
                    Draft draft = WrappedBeanCopier.copyProperties(blog, Draft.class);
                    draft.setUserId(null);
                    Draft newDraft = draftService.updateAndGetNewSelectiveDraftByBlogId(draft);
                    if(newDraft!=null) {
                        blog = WrappedBeanCopier.copyProperties(newDraft, Blog.class);
                    }
                    draftService.deleteByPrimaryKey(blog.getBlogId());
                    blog.setBlogStatus(BlogController.BlogStatusEnum.PRIVATE_STATUS.getCode());
                    blog.setBlogId(testBlog.getBlogId());
                }
                blog.setBlogVersion(testBlog.getBlogVersion() + 1);
                blog.setUserId(testBlog.getUserId());
                blogMapper.updateByPrimaryKeySelective(blog);
                String updateTime = DateUtils.format(blog.getBlogUpdateTime(), DateUtils.DATE_FORMAT_DEFAULT);
                return Msg.success().setMsg("更新成功,更新时间:" + updateTime).add("updateTime", updateTime).add("sourceFrom", "blog");
            } else {
                return Msg.fail().setMsg("这篇Blog不属于你!");
            }
        } else {
            return insertBlogWithCheck(blog);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public Msg insertBlogWithCheck(Blog blog) {
        if(!BlogController.BlogStatusEnum.getBlogTableList().contains(blog.getBlogStatus())){
            return Msg.fail().setMsg("参数错误!");
        }
        // 保存到发布表中
        if("draft".equals(blog.getSourceFrom())){
            Draft draft = WrappedBeanCopier.copyProperties(blog, Draft.class);
            draft.setUserId(null);
            Draft newDraft = draftService.updateAndGetNewSelectiveDraftByBlogId(draft);
            if(newDraft!=null) {
                blog = WrappedBeanCopier.copyProperties(newDraft, Blog.class);
            }
            draftService.deleteByPrimaryKey(blog.getBlogId());
            blog.setBlogStatus(BlogController.BlogStatusEnum.PRIVATE_STATUS.getCode());
        }
        Date now = DateUtils.getCurrentDateTime();
        blog.setBlogUpdateTime(now);
        blog.setBlogCreateTime(now);
        blog.setBlogVersion((long) 0);
        blog.setBlogId(null);  // 因为后面还需要删除草稿表中的数据,所以blogblog的blogId不能为空
        blogMapper.insertSelective(blog);
        String updateTime = DateUtils.format(blog.getBlogUpdateTime(), DateUtils.DATE_FORMAT_DEFAULT);
        return Msg.success().setMsg("新建成功,新建时间:" + updateTime).add("updateTime", updateTime).add("sourceFrom", "blog");
    }

    @Override
    public List<BlogUserVo> getBlogUserBySearch(BlogSearchDTO blogSearchDTO) {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        List<Long> userIdList = new ArrayList<>();
        if(blogSearchDTO.getUserName()!=null&&!"".equals(blogSearchDTO.getUserName().trim())){
           List<User> userList = userService.getUserByLikeUserName(blogSearchDTO.getUserName().trim());
           if(userList.size()>0) {
               for (User u : userList) {
                   userIdList.add(u.getUserId());
               }
           }else{
               return new ArrayList<BlogUserVo>();
           }
        }
        if(blogSearchDTO.getBlogId()!=null){
            criteria.andBlogIdEqualTo(blogSearchDTO.getBlogId());
        }
        if(blogSearchDTO.getUserId()!=null){
            userIdList.add(blogSearchDTO.getUserId());
        }
        if(userIdList.size()>0){
            criteria.andUserIdIn(userIdList);
        }
        if(blogSearchDTO.getBlogTitle()!=null&&!"".equals(blogSearchDTO.getBlogTitle().trim())){
            criteria.andBlogTitleLike("%"+blogSearchDTO.getBlogTitle().trim()+"%");
        }
        List<Integer> statusList = new ArrayList<>();
        if(blogSearchDTO.getBlogStatus()!=null){
            statusList.add(blogSearchDTO.getBlogStatus());
        }
        if(blogSearchDTO.getBlogStatusList()!=null&&blogSearchDTO.getBlogStatusList().size()>0){
            statusList.addAll(blogSearchDTO.getBlogStatusList());
        }
        if(statusList.size()>0){
            criteria.andBlogStatusIn(statusList);
        }
        if(blogSearchDTO.getUpdateTimeStart()!=null&&blogSearchDTO.getUpdateTimeEnd()!=null){
            if(blogSearchDTO.getUpdateTimeStart().before(blogSearchDTO.getUpdateTimeEnd())) {
                criteria.andBlogUpdateTimeBetween(blogSearchDTO.getUpdateTimeStart(),blogSearchDTO.getUpdateTimeEnd());
            }else{
                return new ArrayList<BlogUserVo>();
            }
        }else if(blogSearchDTO.getUpdateTimeStart()!=null){
            criteria.andBlogUpdateTimeGreaterThanOrEqualTo(blogSearchDTO.getUpdateTimeStart());
        }else if(blogSearchDTO.getUpdateTimeEnd()!=null){
            criteria.andBlogUpdateTimeLessThanOrEqualTo(blogSearchDTO.getUpdateTimeEnd());
        }
        if(blogSearchDTO.getBlogType()!=null){
            criteria.andBlogTypeEqualTo(blogSearchDTO.getBlogType());
        }
        List<Long> seriesIdList= new ArrayList<>();
        if(blogSearchDTO.getSeriesName()!=null&&!"".equals(blogSearchDTO.getSeriesName().trim())){
            List<BlogSeries> blogSeriesList = blogSeriesService.getSeriesByLikeSeriesName(blogSearchDTO.getSeriesName());
            if(blogSeriesList.size()>0){
                for(BlogSeries b:blogSeriesList){
                    seriesIdList.add(b.getSeriesId());
                }
            }else{
                return new ArrayList<BlogUserVo>();
            }
        }
        if(blogSearchDTO.getSeriesId()!=null){
            seriesIdList.add(blogSearchDTO.getSeriesId());
        }
        if(seriesIdList.size()>0){
            criteria.andSeriesIdIn(seriesIdList);
        }

        if(blogSearchDTO.getListSort()!=null&&!"".equals(blogSearchDTO.getListSort().trim())){
           String order = null;
           if(BlogController.BlogSortEnum.VIEW_ASC.getDesc().equals(blogSearchDTO.getListSort())){
               order = "blog_views asc";
           }else if(BlogController.BlogSortEnum.VIEW_DESC.getDesc().equals(blogSearchDTO.getListSort())){
               order = "blog_views desc";
           }else if(BlogController.BlogSortEnum.COMMENT_ASC.getDesc().equals(blogSearchDTO.getListSort())){
               order = "blog_comment_count asc";
           }else if(BlogController.BlogSortEnum.COMMENT_DESC.getDesc().equals(blogSearchDTO.getListSort())){
               order = "blog_comment_count desc";
           }else if(BlogController.BlogSortEnum.LIKE_ASC.getDesc().equals(blogSearchDTO.getListSort())){
               order = "blog_like_count asc";
           }else if(BlogController.BlogSortEnum.LIKE_DESC.getDesc().equals(blogSearchDTO.getListSort())){
               order = "blog_like_count desc";
           }else if(BlogController.BlogSortEnum.UPDATE_TIME_ASC.getDesc().equals(blogSearchDTO.getListSort())){
               order = "blog_update_time asc";
           }else if(BlogController.BlogSortEnum.UPDATE_TIME_DESC.getDesc().equals(blogSearchDTO.getListSort())){
               order = "blog_update_time desc";
           }
           if(order!=null){
               example.setOrderByClause(order);
           }
        }else{
            example.setOrderByClause("blog_update_time desc");
        }

        return blogMapper.selectBlogUserByExample(example);
    }

    @Override
    public HomeSummaryVO getCountBlogByStatus(List<Integer> statusList) {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andBlogStatusIn(statusList);
        return blogMapper.countSumBlogByExample(example);
    }

    @Override
    public List<BlogUserVo>  getBlogSeriesTypeByUserIdAndSql(Long userId, List<Integer> statusList,String sql) {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andBlogStatusIn(statusList);
        example.setOrderByClause(sql);
        return blogMapper.selectBlogSeriesTypeByExample(example);
    }

    @Override
    @Transactional(readOnly = false)
    public Integer updateBatchBlogByBlogId(List<Blog> blogList) {
        long start=System.currentTimeMillis();
        blogMapper.updateBatchBlogByBlogId(blogList);
        long end = System.currentTimeMillis();
        log.debug("updateBatchBlogByBlogId---exec time:{}",(end-start));
        return 1;
    }

    @Override
    @Transactional(readOnly = false)
    public Long updateSqlBatchBlogByBlogId(List<Blog> blogList) {
        SqlSession sqlSession =sqlSessionFactory.openSession(ExecutorType.BATCH);
        BlogMapper blogMapper_BATCH = sqlSession.getMapper(BlogMapper.class);
        long start=System.currentTimeMillis();
        for(Blog b:blogList){
            blogMapper_BATCH.updateByPrimaryKeySelective(b);
        }
        sqlSession.flushStatements();
        sqlSession.commit();
        sqlSession.close();
        long end = System.currentTimeMillis();
        log.debug("updateSqlBatchBlogByBlogId---exec time:{}",(end-start));
        return end-start;
    }

    @Override
    @Transactional(readOnly = false)
    public Integer updateSeriesIdByUserIdAndSeriesId(Blog blog,Long userId, Long seriesId) {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andSeriesIdEqualTo(seriesId);
        criteria.andUserIdEqualTo(userId);
        return blogMapper.updateByExampleSelective(blog,example);
    }

    @Override
    @Transactional(readOnly = false)
    public Integer updateSeriesIdBySeriesId(Blog blog, Long seriesId) {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andSeriesIdEqualTo(seriesId);
        return blogMapper.updateByExampleSelective(blog,example);
    }


}
