package com.hly.march2.service.impl;

import com.hly.march2.controller.BlogController;
import com.hly.march2.dao.BlogTypeMapper;
import com.hly.march2.entity.BlogType;
import com.hly.march2.entity.BlogTypeExample;
import com.hly.march2.entity.Draft;
import com.hly.march2.service.IBlogTypeService;
import com.hly.march2.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BlogTypeServiceImpl implements IBlogTypeService {

    @Autowired
    private BlogTypeMapper blogTypeMapper;

    @Override
    @Transactional(readOnly = false)
    public Integer insertTypeByInitial(String typeName, Long userId) {
        Date now = DateUtils.getCurrentDateTime();
        // 如果没有就要新建
        BlogType blogType = new BlogType();
        blogType.setTypeName(typeName);
        blogType.setTypeCreateTime(now);
        blogType.setUserId(userId);
        blogType.setTypeStatus(BlogController.BlogStatusEnum.NORMAL_STATUS.getCode());
        blogType.setTypeNum(1);
        blogTypeMapper.insertSelective(blogType);
        return blogType.getTypeId();
    }

    @Override
    public List<BlogType> getTypeByTypeNameAndUserId(String typeName, Long userId) {
        BlogTypeExample example = new BlogTypeExample();
        BlogTypeExample.Criteria criteria = example.createCriteria();
        criteria.andTypeNameEqualTo(typeName);
        criteria.andUserIdEqualTo(userId);
        return blogTypeMapper.selectByExample(example);
    }

    @Override
    public List<BlogType> getTypeByTypeName(String TypeName) {
        BlogTypeExample example = new BlogTypeExample();
        BlogTypeExample.Criteria criteria = example.createCriteria();
        criteria.andTypeNameEqualTo(TypeName);
        return blogTypeMapper.selectByExample(example);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertTypeWithCheck(Draft draft,BlogType blogType) {
        if (draft.getNewType() != null) {
            List<BlogType> blogTypeList = getTypeByTypeNameAndUserId(draft.getNewType().trim(), draft.getUserId());
            if (blogTypeList.size() > 0) {
                // 如果已有同名Type，拿过来替换
                draft.setNewType(null);
                blogType.setTypeId(blogTypeList.get(0).getTypeId());
            } else {
                Date now = DateUtils.getCurrentDateTime();
                // 如果没有就要新建
                blogType.setTypeName(draft.getNewType().trim());
                blogType.setTypeCreateTime(now);
                blogType.setUserId(draft.getUserId());
                blogType.setTypeStatus(BlogController.BlogStatusEnum.NORMAL_STATUS.getCode());
                blogType.setTypeNum(1);
                blogTypeMapper.insertSelective(blogType);
            }
        }

    }

    @Override
    public List<BlogType> getTypeByUserId(Long userId) {
        BlogTypeExample example = new BlogTypeExample();
        BlogTypeExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return blogTypeMapper.selectByExample(example);
    }

    @Override
    public List<BlogType> getTypeByUserIdAndStatus(Long userId, List<Integer> statusList) {
        BlogTypeExample example = new BlogTypeExample();
        BlogTypeExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andTypeStatusIn(statusList);
        return blogTypeMapper.selectByExample(example);
    }

    @Override
    public List<BlogType> getTypeByStatus(List<Integer> statusList) {
        BlogTypeExample example = new BlogTypeExample();
        BlogTypeExample.Criteria criteria = example.createCriteria();
        criteria.andTypeStatusIn(statusList);
        return blogTypeMapper.selectByExample(example);
    }

    @Override
    public List<BlogType> getAllType() {
        BlogTypeExample example = new BlogTypeExample();
        return blogTypeMapper.selectByExample(example);
    }

}
