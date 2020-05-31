package com.hly.march2.service.impl;

import com.hly.march2.controller.BlogController;
import com.hly.march2.dao.DraftMapper;
import com.hly.march2.dto.BlogSearchDTO;
import com.hly.march2.entity.*;
import com.hly.march2.service.IBlogSeriesService;
import com.hly.march2.service.IBlogTypeService;
import com.hly.march2.service.IDraftService;
import com.hly.march2.service.IUserService;
import com.hly.march2.utils.DateUtils;
import com.hly.march2.vo.BlogUserVo;
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
public class DraftServiceImpl implements IDraftService {
    private static final Logger log = LoggerFactory.getLogger(DraftServiceImpl.class);
    @Autowired
    private DraftMapper draftMapper;

    @Autowired
    @Lazy
    private IBlogSeriesService blogSeriesService;

    @Autowired
    @Lazy
    private IBlogTypeService blogTypeService;

    @Autowired
    @Lazy
    private IUserService userService;

    /**
     * 获取单个博文
     *
     * @return
     */
    @Override
    public BlogUserVo getDraftUserByBlogIdWithBLOBs(Long blogId) {

        return draftMapper.selectDraftUserByPrimaryKeyWithBLOBs(blogId);
    }

    @Override
    public List<BlogUserVo> getDraftUserByUserId(Long userId) {
        DraftExample example = new DraftExample();
        DraftExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        example.setOrderByClause("b.blog_update_time desc");
        return draftMapper.selectDraftUserByExample(example);
    }

    /**
     * 根据博客Id和用户Id，确认符合条件的博客,只返回第一条。
     * 之所以没用count，我认为Count要搜索整个数据库，而加了limit，搜到第一条就返回
     *
     * @param blogId
     * @param userId
     * @return
     */
    @Override
    public List<Draft> getDraftByBlogIdAndUserIdWithBLOBs(Long blogId, Long userId) {
        DraftExample example = new DraftExample();
        DraftExample.Criteria criteria = example.createCriteria();
        criteria.andBlogIdEqualTo(blogId);
        criteria.andUserIdEqualTo(userId);
        example.setOrderByClause("blog_id limit 1");
        return draftMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public Draft getDraftByBlogIdWithBLOBs(Long blogId) {
        return draftMapper.selectByPrimaryKey(blogId);
    }

    @Override
    public List<Draft> getDraftByBlogI(Long blogId) {
        DraftExample example = new DraftExample();
        DraftExample.Criteria criteria = example.createCriteria();
        criteria.andBlogIdEqualTo(blogId);
        return draftMapper.selectByExample(example);
    }

    @Override
    public List<Draft> getDraftByBlogIdAndUserId(Long blogId, Long userId) {
        DraftExample example = new DraftExample();
        DraftExample.Criteria criteria = example.createCriteria();
        criteria.andBlogIdEqualTo(blogId);
        criteria.andUserIdEqualTo(userId);
        return draftMapper.selectByExample(example);
    }

    /**
     * 更新blog
     *
     * @param blog
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKey(Draft blog) {
        return draftMapper.updateByPrimaryKeySelective(blog);
    }

    /**
     * 获取SQL返回的自增id，别再service层获取，因为事务没完成是获取不到的，返回Null
     *
     * @param blog
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public int insertDraft(Draft blog) {
        return draftMapper.insertSelective(blog);
    }

    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(Long blogId) {
        return draftMapper.deleteByPrimaryKey(blogId);
    }

    @Override
    public List<BlogUserVo> getDraftUserByBlogIdAndStatus(Long blogId, Integer status) {
        DraftExample example = new DraftExample();
        DraftExample.Criteria criteria = example.createCriteria();
        criteria.andBlogIdEqualTo(blogId);
        criteria.andBlogStatusEqualTo(status);
        return draftMapper.selectDraftUserByExampleWithBLOBs(example);
    }

    @Override
    @Transactional(readOnly = false)
    public Msg insertDraftWithCheck(Draft draft) {
        if(!BlogController.BlogStatusEnum.getDraftTableList().contains(draft.getBlogStatus())){
            return Msg.fail().setMsg("参数错误!");
        }
        Date now = DateUtils.getCurrentDateTime();
        draft.setBlogUpdateTime(now);
        draft.setBlogCreateTime(now);
        if("blog".equals(draft.getSourceFrom())) {
            draft.setOriginalBlogId(draft.getBlogId());
        }
        // Series与Type
        if (draft.getNewType() != null&&!"".equals(draft.getNewType().trim()  )) {
            List<BlogType> blogTypeList = blogTypeService.getTypeByTypeNameAndUserId(draft.getNewType().trim(), draft.getUserId());
            if (blogTypeList.size() > 0) {
                // 如果已有同名Type，拿过来替换
                draft.setNewType(null);
                draft.setBlogType(blogTypeList.get(0).getTypeId());
            } else {
                // 如果没有就要新建
                BlogType blogType = new BlogType();
                blogTypeService.insertTypeWithCheck(draft, blogType);
                log.info("insert new type. Type Id:{}",blogType.getTypeId());
                draft.setNewType(null);
                draft.setBlogType(blogType.getTypeId());
            }
        }
        if (draft.getNewSeries() != null&&!"".equals(draft.getNewSeries().trim()  )) {
            List<BlogSeries> blogSeriesList = blogSeriesService.getSeriesBySeriesNameAndUserId(draft.getNewSeries().trim(), draft.getUserId());
            if (blogSeriesList.size() > 0) {
                // 如果用户id名下已有同名series，拿过来替换
                draft.setNewSeries(null);
                draft.setSeriesId(blogSeriesList.get(0).getSeriesId());
            } else {
                // 如果没有就要新建
                BlogSeries blogSeries = new BlogSeries();
                blogSeriesService.insertSeriesWithCheck(draft, blogSeries);
                log.info("insert new series. Series Id:{}",blogSeries.getSeriesId());
                draft.setNewSeries(null);
                draft.setSeriesId(blogSeries.getSeriesId());
            }
        }
        draft.setBlogId(null);
        draft.setBlogVersion((long) 0);
        draftMapper.insertSelective(draft);
        String updateTime = DateUtils.format(draft.getBlogUpdateTime(), DateUtils.DATE_FORMAT_DEFAULT);
        String msg = "";
        if(BlogController.BlogStatusEnum.PREVIEW_STATUS.getCode().equals(draft.getBlogStatus())) {
            msg=  "新建预览成功,新建时间:";
        }else{
            msg ="新建草稿成功,新建时间:";
        }
        return Msg.success().setMsg(msg + updateTime).add("updateTime", updateTime).add("sourceFrom", "draft");
    }

    @Override
    @Transactional(readOnly = false)
    public Msg updateDraftWithCheck(Draft draft) {
        if(!BlogController.BlogStatusEnum.getDraftTableList().contains(draft.getBlogStatus())){
            return Msg.fail().setMsg("参数错误!");
        }
        Draft testDraft = null;
        if(draft.getBlogId()!=null&&draft.getBlogId()>0) {
            testDraft = draftMapper.selectByPrimaryKey(draft.getBlogId());
        }
        if (testDraft != null) {
            Date now = DateUtils.getCurrentDateTime();
            draft.setBlogUpdateTime(now);
            draft.setBlogCreateTime(null);
            if (testDraft.getUserId().equals(draft.getUserId())) {
                // 草稿表存在数据并且匹配, 说明blogId有效。就Update
                // Series与Type
                if (draft.getNewType() != null&&!"".equals(draft.getNewType().trim()  )) {
                    List<BlogType> blogTypeList = blogTypeService.getTypeByTypeNameAndUserId(draft.getNewType().trim(), draft.getUserId());
                    if (blogTypeList.size() > 0) {
                        // 如果已有同名Type，拿过来替换
                        draft.setNewType(null);
                        draft.setBlogType(blogTypeList.get(0).getTypeId());
                    } else {
                        // 如果没有就要新建
                        BlogType blogType = new BlogType();
                        blogTypeService.insertTypeWithCheck(draft, blogType);
                        log.info("insert new type. Type Id:{}",blogType.getTypeId());
                        draft.setNewType(null);
                        draft.setBlogType(blogType.getTypeId());
                    }
                }
                if (draft.getNewSeries() != null&&!"".equals(draft.getNewSeries().trim()  )) {
                    List<BlogSeries> blogSeriesList = blogSeriesService.getSeriesBySeriesNameAndUserId(draft.getNewSeries().trim(), draft.getUserId());
                    if (blogSeriesList.size() > 0) {
                        // 如果用户id名下已有同名series，拿过来替换
                        draft.setNewSeries(null);
                        draft.setSeriesId(blogSeriesList.get(0).getSeriesId());
                    } else {
                        // 如果没有就要新建
                        BlogSeries blogSeries = new BlogSeries();
                        blogSeriesService.insertSeriesWithCheck(draft, blogSeries);
                        log.info("insert new series. Series Id:{}",blogSeries.getSeriesId());
                        draft.setNewSeries(null);
                        draft.setSeriesId(blogSeries.getSeriesId());
                    }
                }
//                draft.setBlogStatus(BlogController.BlogStatusEnum.DRAFT_STATUS.getCode());
                draft.setBlogVersion(testDraft.getBlogVersion() + 1);
                draft.setOriginalBlogId(testDraft.getOriginalBlogId());
                draft.setUserId(draft.getUserId());
                draftMapper.updateByPrimaryKeySelective(draft);
                String updateTime = DateUtils.format(draft.getBlogUpdateTime(), DateUtils.DATE_FORMAT_DEFAULT);
                return Msg.success().setMsg("更新草稿成功,更新时间:" + updateTime).add("updateTime", updateTime).add("sourceFrom", "draft");
            } else {
                return Msg.fail().setMsg("这篇Blog不属于你!");
            }
        } else {
            draft.setBlogId(null);
            return insertDraftWithCheck(draft);
        }

    }

    @Override
    public List<BlogUserVo> getDraftUserBySearch(BlogSearchDTO blogSearchDTO) {
        DraftExample example = new DraftExample();
        DraftExample.Criteria criteria = example.createCriteria();
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
        if(blogSearchDTO.getBlogStatus()!=null){
            criteria.andBlogStatusEqualTo(blogSearchDTO.getBlogStatus());
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
            if(BlogController.BlogSortEnum.UPDATE_TIME_ASC.getDesc().equals(blogSearchDTO.getListSort())){
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

        return draftMapper.selectDraftUserByExample(example);
    }

    @Override
    public List<Draft> getDraftByUserIdAndStatus(Long userId, List<Integer> statusList) {
        DraftExample example = new DraftExample();
        DraftExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andBlogStatusIn(statusList);
        return draftMapper.selectByExample(example);
    }

    @Override
    @Transactional(readOnly = false)
    public Integer updateSeriesIdByUserIdAndSeriesId(Draft draft, Long userId, Long seriesId) {
        DraftExample example = new DraftExample();
        DraftExample.Criteria criteria = example.createCriteria();
        criteria.andSeriesIdEqualTo(seriesId);
        criteria.andUserIdEqualTo(userId);
        return draftMapper.updateByExampleSelective(draft,example);
    }

    @Override
    @Transactional(readOnly = false)
    public Integer updateSeriesIdBySeriesId(Draft draft, Long seriesId) {
        DraftExample example = new DraftExample();
        DraftExample.Criteria criteria = example.createCriteria();
        criteria.andSeriesIdEqualTo(seriesId);
        return draftMapper.updateByExampleSelective(draft,example);
    }

    @Override
    @Transactional(readOnly = false)
    public Msg updatePreviewByBlogId(Draft draft) {
        Date now = DateUtils.getCurrentDateTime();
        draft.setBlogUpdateTime(now);
        draft.setBlogCreateTime(now);
        draft.setBlogVersion(new Long(0));
        draftMapper.updateByPrimaryKeyWithBLOBs(draft);
        String updateTime = DateUtils.format(draft.getBlogUpdateTime(), DateUtils.DATE_FORMAT_DEFAULT);
        return Msg.success().setMsg("新建预览成功,新建时间:" + updateTime).add("updateTime", updateTime).add("sourceFrom", "draft");
    }

    @Override
    public Draft updateAndGetNewSelectiveDraftByBlogId(Draft draft) {
        Long blogId = draft.getBlogId();
        if(blogId!=null) {
            draftMapper.updateByPrimaryKeySelective(draft);
            Draft newDraft = draftMapper.selectByPrimaryKey(blogId);
            return newDraft;
        }else {
            return null;
        }
    }
}
