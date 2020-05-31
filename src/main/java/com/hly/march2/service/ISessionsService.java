package com.hly.march2.service;

import com.hly.march2.entity.Sessions;

public interface ISessionsService {

    int insertSession(Sessions session);
    Sessions findSessionById(String id);
    int updateSessionById(Sessions session);
    int deleteSessionById(String id);
}
