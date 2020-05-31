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
@EnableScheduling
public class LikeSchedule {
    private static final Logger log = LoggerFactory.getLogger(LikeSchedule.class);
    @Autowired
    private IBlogService blogService;

    private static Map<Long, Vector<String>> blogIdSessionIdMap = new ConcurrentHashMap<Long,Vector<String>>();

    private static Map<Long,Long> blogLikeNumMap = new ConcurrentHashMap<Long,Long>();

    @PostConstruct
    private void initCount(){

    }

    /**
     * 用户对Blog点赞处理。
     * 通常点赞处理方式有数据库保存点赞记录(每点赞一次都要保存，太麻烦)。application保存点赞信息(每个blog分别保存不现实，而且重启服务器就没了)。
     * 最好的方法应该是redis缓存保存，不过目前本项目还未用到redis缓存。但是我以下这个处理方式也是类似于缓存。
     *
     * 我是每次保存点击记录跟blogId。并且通过记录点赞用户的session(因为点赞用户可能是未登录的所以无Id。当然也可以保存ip地址)，防止短时间内多次点赞。
     * 然后通过@Scheduled定时任务，一起把记录保存到数据库中。
     *
     * 这样就实现了不用频繁访问数据库，并且相对于application不易失，也可以防止短时间内频繁点赞。
     * 对无登录的点赞也可以计数。
     *
     * @param sessionId
     * @param blogId
     * @return true 说明是最近新sid
     */
    public static Boolean blogLikeCheckAndPush(String sessionId,Long blogId){
        if(sessionId!=null&&blogId!=null) {
            if (!blogIdSessionIdMap.containsKey(blogId)) {
                Vector<String> sidList = new Vector<>();
                sidList.add(sessionId);
                blogIdSessionIdMap.put(blogId,sidList);
                log.debug("not containsKey--blogId={}",blogId);
                return true;
            }else{
                Vector<String> sidList = blogIdSessionIdMap.get(blogId);
                if (!sidList.contains(sessionId)){
                    sidList.add(sessionId);
                    blogIdSessionIdMap.put(blogId,sidList);
                    log.debug("containKey--blogId={}",blogId);
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
        log.debug("******** Scheduled Like save Local *******--blogIdSessionIdMap:{}",blogIdSessionIdMap.toString());
        List<Blog> blogList = new ArrayList<>();
        for(Long key:blogIdSessionIdMap.keySet()){
            Blog b = new Blog();
            b.setBlogId(key);
            b.setBlogLikeCount(new Long(blogIdSessionIdMap.get(key).size()));
            blogList.add(b);
        }
        if(blogList.size()>0) {
            blogService.updateBatchBlogByBlogId(blogList);
            blogIdSessionIdMap.clear();
        }else{
            log.debug("******** Scheduled Like save Local *******--blogList.为空");
        }
    }

    /**
     * Destory后调用，经测试此方法内访问不了数据库了。
     */
    @PreDestroy
    private void saveCount(){
        log.debug("******** PreDestroy Like save Local *******--blogIdSessionIdMap:{}",blogIdSessionIdMap.toString());
//        List<Blog> blogList = new ArrayList<>();
//        for(Long key:blogIdSessionIdMap.keySet()){
//            Blog b = new Blog();
//            b.setBlogId(key);
//            b.setBlogLikeCount(new Long(blogIdSessionIdMap.get(key).size()));
//            blogList.add(b);
//        }
//        if(blogList.size()>0) {
//            blogService.updateBatchBlogByBlogId(blogList);
//            blogIdSessionIdMap.clear();
//        }else{
//            System.out.println("******** PreDestroy Like save Count *******--blogList.为空");
//        }
    }

}
