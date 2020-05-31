package com.hly.march2.service.impl;

import com.hly.march2.dao.SessionsMapper;
import com.hly.march2.entity.Sessions;
import com.hly.march2.entity.SessionsExample;
import com.hly.march2.service.ISessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Shiro Session Service For MySessionDAO
 */
@Service
@Transactional(readOnly = true)
public class SessionsServiceImpl implements ISessionsService {

    @Autowired
    private SessionsMapper sessionsMapper;

    @Override
    @Transactional(readOnly = false)
    public int insertSession(Sessions session) {
        int result = sessionsMapper.insert(session);
        return result;
    }

    @Override
    public Sessions findSessionById(String id) {
        Sessions session = sessionsMapper.selectByPrimaryKey(id);
        return session;
    }

    @Override
    @Transactional(readOnly = false)
    public int updateSessionById(Sessions session) {
        int result = sessionsMapper.updateByPrimaryKey(session);
        return result;
    }

    @Override
    @Transactional(readOnly = false)
    public int deleteSessionById(String id) {
        SessionsExample example = new SessionsExample();
        SessionsExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        int result = sessionsMapper.deleteByExample(example);
        return result;
    }
}
