package com.hly.march2.service.impl;

import com.hly.march2.dao.MyServerInfoMapper;
import com.hly.march2.entity.MyServerInfo;
import com.hly.march2.entity.MyServerInfoExample;
import com.hly.march2.service.IMyServerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MyServerInfoServiceImpl implements IMyServerInfoService {
    @Autowired
    private MyServerInfoMapper myServerInfoMapper;

    @Override
    public List<MyServerInfo> getLatestRecord() {
        MyServerInfoExample example = new MyServerInfoExample();
//        MyServerInfoExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("this_update_time desc limit 1");
        return myServerInfoMapper.selectByExample(example);
    }

    @Override
    @Transactional(readOnly = false)
    public Integer insertNewRecord(MyServerInfo myServerInfo) {
        return myServerInfoMapper.insertSelective(myServerInfo);
    }

    @Override
    @Transactional(readOnly = false)
    public Integer updateRecord(MyServerInfo myServerInfo) {
        return myServerInfoMapper.updateByPrimaryKeySelective(myServerInfo);
    }

    @Override
    @Transactional(readOnly = false)
    public Integer finalUpdateRecord(MyServerInfo myServerInfo) {
        return myServerInfoMapper.finalUpdateByPrimaryKeySelective(myServerInfo);
    }

    @Override
    @Transactional(readOnly = false)
    public Integer periodUpdateRecord(MyServerInfo myServerInfo) {
        return myServerInfoMapper.periodUpdateByPrimaryKeySelective(myServerInfo);
    }
}
