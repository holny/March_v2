package com.hly.march2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BlogSearchDTO implements Serializable {

    @Range(min=0,max = Long.MAX_VALUE,message = "User Id超出范围")
    private Long userId;

    @NotNull(message = "页码不能为空")
    @Range(min=0,max = Integer.MAX_VALUE,message = "页码超出范围")
    private Integer pn;

    @Size(max=10, message="用户名长度最大10")
    @Pattern(regexp = "(^\\s?$)|(^([a-zA-Z0-9_\\.-]+){0,10}$)|(^([\\u4e00-\\u9fa5\\.]+){0,10}$)"
            ,message = "用户名必须是字母或者汉字组成(最大10位)")
    private String userName;

    @Size(max=50, message="标题长度必须在最大50")
    private String blogTitle;

    @Range(min=0,max = Long.MAX_VALUE,message = "Blog Id超出范围")
    private Long blogId;

    @Range(min=0,max =7,message = "Blog Status超出范围")
    private Integer blogStatus;

    private List<Integer> blogStatusList;

    @Past(message = "更新时间start不能早于现在")
    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date updateTimeStart;

    @Past(message = "更新时间end不能早于现在")
    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm")
    private Date updateTimeEnd;

    @Range(min=0,max = Integer.MAX_VALUE,message = "标题Id超出范围")
    private Integer blogType;

    @Range(min=0,max = Long.MAX_VALUE,message = "Series Id超出范围")
    private Long seriesId;

    @Size( max=20, message="Series name长度最大20")
    private String seriesName;

    private String sourceFrom;

    private String listSort;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Integer getBlogStatus() {
        return blogStatus;
    }

    public void setBlogStatus(Integer blogStatus) {
        this.blogStatus = blogStatus;
    }

    public Date getUpdateTimeStart() {
        return updateTimeStart;
    }

    public void setUpdateTimeStart(Date updateTimeStart) {
        this.updateTimeStart = updateTimeStart;
    }

    public Date getUpdateTimeEnd() {
        return updateTimeEnd;
    }

    public void setUpdateTimeEnd(Date updateTimeEnd) {
        this.updateTimeEnd = updateTimeEnd;
    }

    public Integer getBlogType() {
        return blogType;
    }

    public void setBlogType(Integer blogType) {
        this.blogType = blogType;
    }

    public Long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Long seriesId) {
        this.seriesId = seriesId;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    public Integer getPn() {
        return pn;
    }

    public void setPn(Integer pn) {
        this.pn = pn;
    }

    public String getListSort() {
        return listSort;
    }

    public void setListSort(String listSort) {
        this.listSort = listSort;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public List<Integer> getBlogStatusList() {
        return blogStatusList;
    }

    public void setBlogStatusList(List<Integer> blogStatusList) {
        this.blogStatusList = blogStatusList;
    }

    @Override
    public String toString() {
        return "BlogSearchDTO{" +
                "userId=" + userId +
                ", pn=" + pn +
                ", userName='" + userName + '\'' +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogId=" + blogId +
                ", blogStatus=" + blogStatus +
                ", blogStatusList=" + blogStatusList +
                ", updateTimeStart=" + updateTimeStart +
                ", updateTimeEnd=" + updateTimeEnd +
                ", blogType=" + blogType +
                ", seriesId=" + seriesId +
                ", seriesName='" + seriesName + '\'' +
                ", sourceFrom='" + sourceFrom + '\'' +
                ", listSort='" + listSort + '\'' +
                '}';
    }
}
