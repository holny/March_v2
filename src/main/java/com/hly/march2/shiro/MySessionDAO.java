package com.hly.march2.shiro;

import com.hly.march2.entity.Sessions;
import com.hly.march2.service.ISessionsService;
import com.hly.march2.utils.DateUtils;
import com.hly.march2.utils.SessionSerializableUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;

/**
 * Shiro Session的持久化部分，一般是redis，这里用mysql代替。
 *
 * 问题，用户登录 doUpdate session序列化出错oos.writeObject(session);不登录update都没问题，create也没问题。
 * 网上了解怀疑是shiro给这个session加了头尾，所以序列化出错。
 * 网上类似问题redis的，他们可以给 JdkSerializationRedisSerializer 指定 key 的序列化方式，即指定 String 和 Hash 的序列化方式。
 * 这个问题在我给我定义的数据库entity Sessions implements Serializable 后解决，目前没问题出现。
 */
public class MySessionDAO extends EnterpriseCacheSessionDAO {
    private static final Logger log = LoggerFactory.getLogger(MySessionDAO.class);
    public static final String SESSION_UPDATE_FALG = "SESSION_UPDATE_FALG";
    public static final long SESSION_UPDATE_MIN_PERIOD = 10000;

    @Autowired
    private ISessionsService sessionsService;

    @Override
    protected Serializable doCreate(Session session) {
        SimpleSession simpleSession = (SimpleSession)session;
        Serializable sessionId = this.generateSessionId(simpleSession);
        assignSessionId(simpleSession,sessionId);
        Date nowTime = DateUtils.getCurrentDateTime();
        log.debug("doCreate , nowTime:{}",nowTime.getTime());
        simpleSession.setAttribute(SESSION_UPDATE_FALG, nowTime);
        log.debug("doCreate , set update time:{}",((Date)simpleSession.getAttribute(SESSION_UPDATE_FALG)).getTime());
        log.debug("doCreate session.getId:{}",simpleSession.getId().toString());

        int result = this.sessionsService.insertSession(new Sessions(sessionId.toString(), SessionSerializableUtils.simpleSessionSerialize(simpleSession),nowTime));
        if(result>=1){
            log.debug("doCreate success session.Serializable Id:{}",sessionId.toString());
        }
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        log.debug("doReadSession ,Serializable Id:{}",sessionId.toString());
        Sessions mySession = this.sessionsService.findSessionById(sessionId.toString());
        if(mySession==null){
            log.debug("doReadSession,get session is null from DB session.getId:{}",sessionId.toString());
            return null;
        }
        log.debug("doReadSession session.getId:{}",mySession.getId());
        SimpleSession simpleSession = SessionSerializableUtils.simpleSessionDeserialize(mySession.getSession());
//        session.setAttribute(SESSION_UPDATE_FALG,mySession.getUpdateTime().getTime());
        log.debug("doReadSession success session. update time:{}",((Date)simpleSession.getAttribute(SESSION_UPDATE_FALG)).getTime());
        return simpleSession;
    }

    @Override
    protected void doUpdate(Session session) {
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()){
            return;
        }
        SimpleSession simpleSession = (SimpleSession)session;
        Date nowTime = DateUtils.getCurrentDateTime();
        if(simpleSession.getAttribute(SESSION_UPDATE_FALG)!=null){
            Date lastUpdateTime = (Date)simpleSession.getAttribute(SESSION_UPDATE_FALG);
            log.debug("doUpdate time check session , last update time:{} , now time:{}",lastUpdateTime.getTime(),nowTime.getTime());
            if(nowTime.getTime()-lastUpdateTime.getTime()<=SESSION_UPDATE_MIN_PERIOD){
                log.debug("doUpdate time check session ,  no need update");
                return;
            }
            log.debug("doUpdate time check session , need update");
        }else{
            log.debug("doUpdate time check session , update attribute is NULL !");
        }
        log.debug("session.getId:{}",simpleSession.getId().toString());
        Serializable sessionId = this.generateSessionId(simpleSession);
        int result = this.sessionsService.updateSessionById(new Sessions(sessionId.toString(),SessionSerializableUtils.simpleSessionSerialize(simpleSession),nowTime));
        simpleSession.setAttribute(SESSION_UPDATE_FALG,nowTime);
        if(result>=1){
            log.debug("doUpdate success session.Serializable Id:{}",sessionId.toString());
        }
    }

    @Override
    protected void doDelete(Session session) {
        SimpleSession simpleSession = (SimpleSession)session;
        Serializable sessionId = this.generateSessionId(simpleSession);
        log.debug("session.getId:{}",simpleSession.getId().toString());
        int result = this.sessionsService.deleteSessionById(sessionId.toString());
        if(result>=1){
            log.debug("doDelete success session.Serializable Id:{}",sessionId.toString());
        }
    }
}
