package com.hly.march2.dto;

import java.util.Date;

/**
 * 作为前台新建/编辑博客的临时存放简短说明。
 */
public class EditBlogDTO {
    private Long blogId;

    private Long userId;

    private String blogTitle;

    private Date blogCreateDate;

    private Date blogUpdateDate;

    private String blogBriefIntroduction;

    private String blogStatus;

    private String blogMedia;

    private String blogTag;

    private String blogCategory;

    private String blogInfo;

}
