package com.hly.march2.schedule;

import com.hly.march2.entity.MyServerInfo;
import com.hly.march2.service.IMyServerInfoService;
import com.hly.march2.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.List;
import java.util.Vector;

@Component
public class ProjectSchedule {
    private static final Logger log = LoggerFactory.getLogger(ProjectSchedule.class);
    @Autowired
    private IMyServerInfoService serverInfoService;

    private static Vector<String> guestIpVector = new Vector<>();

    private static Long periodVisitorNum = new Long(0);

    private static Long totalVisitorNum =new Long(0);

    private static Date serverThisStartTime;

    private static Long serverTotalRunningTime = null;   // 这个总运行时间是上一次记录在上数据库的时间

    private static Long serverPeriodRunningTime = new Long(0);   // 这个总运行时间是这次开启服务器运行时间

    private static Long thisRecordId;       // 本次服务器运行数据

    /**
     * 记录网站的总运行时间和总访问数功能。
     *
     * @PostConstruct 在每次服务器启动时从数据库取得上一次运行保存的总运行时间和总访问人数，在本次运行期间内继续计数。
     *
     *  @Scheduled 通过定时任务，定时保存记录。防止数据丢失。
     */
    @PostConstruct
    private void initCount() {
        MyServerInfo newRecord = new MyServerInfo();
        List<MyServerInfo> serverInfoList = serverInfoService.getLatestRecord();
        serverThisStartTime = DateUtils.getCurrentDateTime();
        if (serverInfoList.size() > 0) {
            MyServerInfo myServerInfo = serverInfoList.get(0);
            log.debug("******** initCount Server *******myServerInfo=" + myServerInfo.toString());
            if (myServerInfo.getTotalRunningTime() != null) {
                serverTotalRunningTime = myServerInfo.getTotalRunningTime();
            }
            if (myServerInfo.getTotalVisitorNum() != null) {
                totalVisitorNum = myServerInfo.getTotalVisitorNum();
            }
            newRecord.setFirstStartTime(myServerInfo.getFirstStartTime());
            // 服务器初始时，要新建关于这次服务器启动的数据，初始数据用上一次的数据，后面再更新这条数据
            newRecord.setTotalRunningTime(myServerInfo.getTotalRunningTime());
            newRecord.setTotalVisitorNum(myServerInfo.getTotalVisitorNum());
        } else {
            totalVisitorNum = new Long(0);
            serverTotalRunningTime = new Long(0);
            newRecord.setFirstStartTime(serverThisStartTime);
            newRecord.setTotalRunningTime(serverTotalRunningTime);
            newRecord.setTotalVisitorNum(totalVisitorNum);
            log.debug("******** initCount Server ******* 无历史记录");
        }
        newRecord.setThisStartTime(serverThisStartTime);
        newRecord.setThisVisitorNum(periodVisitorNum);
        newRecord.setThisUpdateTime(DateUtils.getCurrentDateTime());
        serverInfoService.insertNewRecord(newRecord);
        thisRecordId = newRecord.getId();
        log.debug("******** initCount Server ******* thisRecord=" + newRecord.toString());
    }

    /**
     * @param
     * @param
     * @return true 说明是最近新sid
     */
    public static Long guestIpCheckAndPush(String guestIp) {
        if (guestIp != null && !guestIpVector.contains(guestIp)) {
            guestIpVector.add(guestIp);
            periodVisitorNum++;
            totalVisitorNum++;
            log.debug("not containsKey--guestIp=" + guestIp);
            return totalVisitorNum;
        } else {
            log.debug("containsKey--guestIp=" + guestIp);
            return totalVisitorNum;
        }

    }

    /**
     * @param
     * @param
     * @return true 说明是最近新sid
     */
    public static Long getServerRunningTime() {
        Date now = DateUtils.getCurrentDateTime();
        Long diffSec = (now.getTime() - serverThisStartTime.getTime()) / 1000;  // 获取服务器当前已经运行的秒数
        Long totalSec = diffSec+serverTotalRunningTime; // 加上以前的历史记录
        log.info("当前服务器已经运行(秒):"+totalSec);
        return totalSec;
    }

    /**
     * fixedDelay单位毫秒 1000=1秒
     */
    @Scheduled(fixedDelay = 1200000)
    private void saveLocal() {
        if(periodVisitorNum!=null&&serverPeriodRunningTime!=null) {
            Date now = DateUtils.getCurrentDateTime();
            MyServerInfo newRecord = new MyServerInfo();
            newRecord.setId(thisRecordId);
            log.debug("******** Scheduled project guest Ip save Local *******--guestIpVector" + guestIpVector.toString());
            newRecord.setThisVisitorNum(periodVisitorNum);
            newRecord.setTotalVisitorNum(totalVisitorNum);
            Long diffSec = (now.getTime() - serverThisStartTime.getTime()) / 1000;  // 获取服务器当前已经运行的秒数
            Long totalSec = diffSec+serverTotalRunningTime; // 加上以前的历史记录
            newRecord.setTotalRunningTime(totalSec);
            newRecord.setThisEndTime(now);
            newRecord.setThisUpdateTime(now);
            serverInfoService.periodUpdateRecord(newRecord);
            guestIpVector.clear();
            log.debug("******** Scheduled project guest Ip save Local *******--newRecord" + newRecord.toString());
        }
    }

    @PreDestroy
    private void saveCount() {
//        Date now = DateUtils.getCurrentDateTime();
//        MyServerInfo newRecord = new MyServerInfo();
//        newRecord.setId(thisRecordId);
//        Long diffSec = (now.getTime() - serverThisStartTime.getTime()) / 1000;  // 获取服务器当前已经运行的秒数
//        newRecord.setThisEndTime(now);
//        newRecord.setTotalRunningTime(diffSec);     // mapper那会累加之前的记录
//        newRecord.setThisUpdateTime(now);
//        newRecord.setThisVisitorNum(thisVisitorNum);
//        serverInfoService.finalUpdateRecord(newRecord);
        log.debug("******** PreDestroy Like save Count *******");
    }
}
