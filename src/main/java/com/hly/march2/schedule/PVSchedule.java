package com.hly.march2.schedule;

import com.hly.march2.entity.Blog;
import com.hly.march2.service.IBlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

@Component
@EnableScheduling//可以在启动类上注解也可以在当前文件
public class PVSchedule {
    private static final Logger log = LoggerFactory.getLogger(PVSchedule.class);
    @Autowired
    private IBlogService blogService;

    private static Map<Long, Vector<String>> blogIdSessionIdMap = new ConcurrentHashMap<Long,Vector<String>>();

    private static Map<Long,Long> blogViewNumMap = new ConcurrentHashMap<Long,Long>();

    @PostConstruct
    private void initCount(){

    }

    /**
     * 实现Blog 浏览量计数功能。类似于点赞功能实现方式，所以不赘述了。
     *
     * 对无登录的访问也可以计数，并且防止短时间内多次访问重复计数。
     * @param sessionId
     * @param blogId
     * @return true 说明是最近新sid
     */
    public static Boolean blogViewCheckAndPush(String sessionId,Long blogId){
        log.info("blogViewCheckAndPush  judge blogId:{}, sessionId:{}",blogId,sessionId);
        if(sessionId!=null&&blogId!=null) {
            if (!blogIdSessionIdMap.containsKey(blogId)) {
                Vector<String> sidList = new Vector<>();
                sidList.add(sessionId);
                blogIdSessionIdMap.put(blogId,sidList);
                log.debug("not containsKey--blogId="+blogId);
                return true;
            }else{
                Vector<String> sidList = blogIdSessionIdMap.get(blogId);
                if (!sidList.contains(sessionId)){
                    sidList.add(sessionId);
                    blogIdSessionIdMap.put(blogId,sidList);
                    log.debug("containsKey--blogId="+blogId);
                    return  true;
                }else{
                    return false;
                }
            }
        }else{
            return false;
        }
    }

    /**
     * fixedDelay单位毫秒 1000=1秒
     */
    @Scheduled(fixedDelay=600000)
    private void saveLocal(){
        log.debug("******** Scheduled view save Local *******--blogIdSessionIdMap"+blogIdSessionIdMap.toString());
        List<Blog> blogList = new ArrayList<>();
        for(Long key:blogIdSessionIdMap.keySet()){
            Blog b = new Blog();
            b.setBlogId(key);
            b.setBlogViews(new Long(blogIdSessionIdMap.get(key).size()));
            blogList.add(b);
        }
        if(blogList.size()>0) {
            blogService.updateBatchBlogByBlogId(blogList);
            blogIdSessionIdMap.clear();
        }else{
            log.debug("******** Scheduled view save Local *******--blogList.为空");
        }
    }

    @PreDestroy
    private void saveCount(){
        log.debug("******** PreDestroy view save Count *******-blogIdSessionIdMap="+blogIdSessionIdMap.toString());
//        List<Blog> blogList = new ArrayList<>();
//        for(Long key:blogIdSessionIdMap.keySet()){
//            Blog b = new Blog();
//            b.setBlogId(key);
//            b.setBlogViews(new Long(blogIdSessionIdMap.get(key).size()));
//            blogList.add(b);
//        }
//        if(blogList.size()>0) {
//            blogService.updateBatchBlogByBlogId(blogList);
//            blogIdSessionIdMap.clear();
//        }else{
//            System.out.println("******** PreDestroy view save Count *******--blogList.为空");
//        }
    }


}
