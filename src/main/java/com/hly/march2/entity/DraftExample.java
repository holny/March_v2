package com.hly.march2.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DraftExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DraftExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andBlogIdIsNull() {
            addCriterion("blog_id is null");
            return (Criteria) this;
        }

        public Criteria andBlogIdIsNotNull() {
            addCriterion("blog_id is not null");
            return (Criteria) this;
        }

        public Criteria andBlogIdEqualTo(Long value) {
            addCriterion("blog_id =", value, "blogId");
            return (Criteria) this;
        }

        public Criteria andBlogIdNotEqualTo(Long value) {
            addCriterion("blog_id <>", value, "blogId");
            return (Criteria) this;
        }

        public Criteria andBlogIdGreaterThan(Long value) {
            addCriterion("blog_id >", value, "blogId");
            return (Criteria) this;
        }

        public Criteria andBlogIdGreaterThanOrEqualTo(Long value) {
            addCriterion("blog_id >=", value, "blogId");
            return (Criteria) this;
        }

        public Criteria andBlogIdLessThan(Long value) {
            addCriterion("blog_id <", value, "blogId");
            return (Criteria) this;
        }

        public Criteria andBlogIdLessThanOrEqualTo(Long value) {
            addCriterion("blog_id <=", value, "blogId");
            return (Criteria) this;
        }

        public Criteria andBlogIdIn(List<Long> values) {
            addCriterion("blog_id in", values, "blogId");
            return (Criteria) this;
        }

        public Criteria andBlogIdNotIn(List<Long> values) {
            addCriterion("blog_id not in", values, "blogId");
            return (Criteria) this;
        }

        public Criteria andBlogIdBetween(Long value1, Long value2) {
            addCriterion("blog_id between", value1, value2, "blogId");
            return (Criteria) this;
        }

        public Criteria andBlogIdNotBetween(Long value1, Long value2) {
            addCriterion("blog_id not between", value1, value2, "blogId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andBlogTitleIsNull() {
            addCriterion("blog_title is null");
            return (Criteria) this;
        }

        public Criteria andBlogTitleIsNotNull() {
            addCriterion("blog_title is not null");
            return (Criteria) this;
        }

        public Criteria andBlogTitleEqualTo(String value) {
            addCriterion("blog_title =", value, "blogTitle");
            return (Criteria) this;
        }

        public Criteria andBlogTitleNotEqualTo(String value) {
            addCriterion("blog_title <>", value, "blogTitle");
            return (Criteria) this;
        }

        public Criteria andBlogTitleGreaterThan(String value) {
            addCriterion("blog_title >", value, "blogTitle");
            return (Criteria) this;
        }

        public Criteria andBlogTitleGreaterThanOrEqualTo(String value) {
            addCriterion("blog_title >=", value, "blogTitle");
            return (Criteria) this;
        }

        public Criteria andBlogTitleLessThan(String value) {
            addCriterion("blog_title <", value, "blogTitle");
            return (Criteria) this;
        }

        public Criteria andBlogTitleLessThanOrEqualTo(String value) {
            addCriterion("blog_title <=", value, "blogTitle");
            return (Criteria) this;
        }

        public Criteria andBlogTitleLike(String value) {
            addCriterion("blog_title like", value, "blogTitle");
            return (Criteria) this;
        }

        public Criteria andBlogTitleNotLike(String value) {
            addCriterion("blog_title not like", value, "blogTitle");
            return (Criteria) this;
        }

        public Criteria andBlogTitleIn(List<String> values) {
            addCriterion("blog_title in", values, "blogTitle");
            return (Criteria) this;
        }

        public Criteria andBlogTitleNotIn(List<String> values) {
            addCriterion("blog_title not in", values, "blogTitle");
            return (Criteria) this;
        }

        public Criteria andBlogTitleBetween(String value1, String value2) {
            addCriterion("blog_title between", value1, value2, "blogTitle");
            return (Criteria) this;
        }

        public Criteria andBlogTitleNotBetween(String value1, String value2) {
            addCriterion("blog_title not between", value1, value2, "blogTitle");
            return (Criteria) this;
        }

        public Criteria andBlogStatusIsNull() {
            addCriterion("blog_status is null");
            return (Criteria) this;
        }

        public Criteria andBlogStatusIsNotNull() {
            addCriterion("blog_status is not null");
            return (Criteria) this;
        }

        public Criteria andBlogStatusEqualTo(Integer value) {
            addCriterion("blog_status =", value, "blogStatus");
            return (Criteria) this;
        }

        public Criteria andBlogStatusNotEqualTo(Integer value) {
            addCriterion("blog_status <>", value, "blogStatus");
            return (Criteria) this;
        }

        public Criteria andBlogStatusGreaterThan(Integer value) {
            addCriterion("blog_status >", value, "blogStatus");
            return (Criteria) this;
        }

        public Criteria andBlogStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("blog_status >=", value, "blogStatus");
            return (Criteria) this;
        }

        public Criteria andBlogStatusLessThan(Integer value) {
            addCriterion("blog_status <", value, "blogStatus");
            return (Criteria) this;
        }

        public Criteria andBlogStatusLessThanOrEqualTo(Integer value) {
            addCriterion("blog_status <=", value, "blogStatus");
            return (Criteria) this;
        }

        public Criteria andBlogStatusIn(List<Integer> values) {
            addCriterion("blog_status in", values, "blogStatus");
            return (Criteria) this;
        }

        public Criteria andBlogStatusNotIn(List<Integer> values) {
            addCriterion("blog_status not in", values, "blogStatus");
            return (Criteria) this;
        }

        public Criteria andBlogStatusBetween(Integer value1, Integer value2) {
            addCriterion("blog_status between", value1, value2, "blogStatus");
            return (Criteria) this;
        }

        public Criteria andBlogStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("blog_status not between", value1, value2, "blogStatus");
            return (Criteria) this;
        }

        public Criteria andBlogTypeIsNull() {
            addCriterion("blog_type is null");
            return (Criteria) this;
        }

        public Criteria andBlogTypeIsNotNull() {
            addCriterion("blog_type is not null");
            return (Criteria) this;
        }

        public Criteria andBlogTypeEqualTo(Integer value) {
            addCriterion("blog_type =", value, "blogType");
            return (Criteria) this;
        }

        public Criteria andBlogTypeNotEqualTo(Integer value) {
            addCriterion("blog_type <>", value, "blogType");
            return (Criteria) this;
        }

        public Criteria andBlogTypeGreaterThan(Integer value) {
            addCriterion("blog_type >", value, "blogType");
            return (Criteria) this;
        }

        public Criteria andBlogTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("blog_type >=", value, "blogType");
            return (Criteria) this;
        }

        public Criteria andBlogTypeLessThan(Integer value) {
            addCriterion("blog_type <", value, "blogType");
            return (Criteria) this;
        }

        public Criteria andBlogTypeLessThanOrEqualTo(Integer value) {
            addCriterion("blog_type <=", value, "blogType");
            return (Criteria) this;
        }

        public Criteria andBlogTypeIn(List<Integer> values) {
            addCriterion("blog_type in", values, "blogType");
            return (Criteria) this;
        }

        public Criteria andBlogTypeNotIn(List<Integer> values) {
            addCriterion("blog_type not in", values, "blogType");
            return (Criteria) this;
        }

        public Criteria andBlogTypeBetween(Integer value1, Integer value2) {
            addCriterion("blog_type between", value1, value2, "blogType");
            return (Criteria) this;
        }

        public Criteria andBlogTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("blog_type not between", value1, value2, "blogType");
            return (Criteria) this;
        }

        public Criteria andSeriesIdIsNull() {
            addCriterion("series_id is null");
            return (Criteria) this;
        }

        public Criteria andSeriesIdIsNotNull() {
            addCriterion("series_id is not null");
            return (Criteria) this;
        }

        public Criteria andSeriesIdEqualTo(Long value) {
            addCriterion("series_id =", value, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdNotEqualTo(Long value) {
            addCriterion("series_id <>", value, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdGreaterThan(Long value) {
            addCriterion("series_id >", value, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("series_id >=", value, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdLessThan(Long value) {
            addCriterion("series_id <", value, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdLessThanOrEqualTo(Long value) {
            addCriterion("series_id <=", value, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdIn(List<Long> values) {
            addCriterion("series_id in", values, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdNotIn(List<Long> values) {
            addCriterion("series_id not in", values, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdBetween(Long value1, Long value2) {
            addCriterion("series_id between", value1, value2, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdNotBetween(Long value1, Long value2) {
            addCriterion("series_id not between", value1, value2, "seriesId");
            return (Criteria) this;
        }

        public Criteria andBlogCreateTimeIsNull() {
            addCriterion("blog_create_time is null");
            return (Criteria) this;
        }

        public Criteria andBlogCreateTimeIsNotNull() {
            addCriterion("blog_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andBlogCreateTimeEqualTo(Date value) {
            addCriterion("blog_create_time =", value, "blogCreateTime");
            return (Criteria) this;
        }

        public Criteria andBlogCreateTimeNotEqualTo(Date value) {
            addCriterion("blog_create_time <>", value, "blogCreateTime");
            return (Criteria) this;
        }

        public Criteria andBlogCreateTimeGreaterThan(Date value) {
            addCriterion("blog_create_time >", value, "blogCreateTime");
            return (Criteria) this;
        }

        public Criteria andBlogCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("blog_create_time >=", value, "blogCreateTime");
            return (Criteria) this;
        }

        public Criteria andBlogCreateTimeLessThan(Date value) {
            addCriterion("blog_create_time <", value, "blogCreateTime");
            return (Criteria) this;
        }

        public Criteria andBlogCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("blog_create_time <=", value, "blogCreateTime");
            return (Criteria) this;
        }

        public Criteria andBlogCreateTimeIn(List<Date> values) {
            addCriterion("blog_create_time in", values, "blogCreateTime");
            return (Criteria) this;
        }

        public Criteria andBlogCreateTimeNotIn(List<Date> values) {
            addCriterion("blog_create_time not in", values, "blogCreateTime");
            return (Criteria) this;
        }

        public Criteria andBlogCreateTimeBetween(Date value1, Date value2) {
            addCriterion("blog_create_time between", value1, value2, "blogCreateTime");
            return (Criteria) this;
        }

        public Criteria andBlogCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("blog_create_time not between", value1, value2, "blogCreateTime");
            return (Criteria) this;
        }

        public Criteria andBlogUpdateTimeIsNull() {
            addCriterion("blog_update_time is null");
            return (Criteria) this;
        }

        public Criteria andBlogUpdateTimeIsNotNull() {
            addCriterion("blog_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andBlogUpdateTimeEqualTo(Date value) {
            addCriterion("blog_update_time =", value, "blogUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBlogUpdateTimeNotEqualTo(Date value) {
            addCriterion("blog_update_time <>", value, "blogUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBlogUpdateTimeGreaterThan(Date value) {
            addCriterion("blog_update_time >", value, "blogUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBlogUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("blog_update_time >=", value, "blogUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBlogUpdateTimeLessThan(Date value) {
            addCriterion("blog_update_time <", value, "blogUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBlogUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("blog_update_time <=", value, "blogUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBlogUpdateTimeIn(List<Date> values) {
            addCriterion("blog_update_time in", values, "blogUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBlogUpdateTimeNotIn(List<Date> values) {
            addCriterion("blog_update_time not in", values, "blogUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBlogUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("blog_update_time between", value1, value2, "blogUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBlogUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("blog_update_time not between", value1, value2, "blogUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBlogIntroIsNull() {
            addCriterion("blog_intro is null");
            return (Criteria) this;
        }

        public Criteria andBlogIntroIsNotNull() {
            addCriterion("blog_intro is not null");
            return (Criteria) this;
        }

        public Criteria andBlogIntroEqualTo(String value) {
            addCriterion("blog_intro =", value, "blogIntro");
            return (Criteria) this;
        }

        public Criteria andBlogIntroNotEqualTo(String value) {
            addCriterion("blog_intro <>", value, "blogIntro");
            return (Criteria) this;
        }

        public Criteria andBlogIntroGreaterThan(String value) {
            addCriterion("blog_intro >", value, "blogIntro");
            return (Criteria) this;
        }

        public Criteria andBlogIntroGreaterThanOrEqualTo(String value) {
            addCriterion("blog_intro >=", value, "blogIntro");
            return (Criteria) this;
        }

        public Criteria andBlogIntroLessThan(String value) {
            addCriterion("blog_intro <", value, "blogIntro");
            return (Criteria) this;
        }

        public Criteria andBlogIntroLessThanOrEqualTo(String value) {
            addCriterion("blog_intro <=", value, "blogIntro");
            return (Criteria) this;
        }

        public Criteria andBlogIntroLike(String value) {
            addCriterion("blog_intro like", value, "blogIntro");
            return (Criteria) this;
        }

        public Criteria andBlogIntroNotLike(String value) {
            addCriterion("blog_intro not like", value, "blogIntro");
            return (Criteria) this;
        }

        public Criteria andBlogIntroIn(List<String> values) {
            addCriterion("blog_intro in", values, "blogIntro");
            return (Criteria) this;
        }

        public Criteria andBlogIntroNotIn(List<String> values) {
            addCriterion("blog_intro not in", values, "blogIntro");
            return (Criteria) this;
        }

        public Criteria andBlogIntroBetween(String value1, String value2) {
            addCriterion("blog_intro between", value1, value2, "blogIntro");
            return (Criteria) this;
        }

        public Criteria andBlogIntroNotBetween(String value1, String value2) {
            addCriterion("blog_intro not between", value1, value2, "blogIntro");
            return (Criteria) this;
        }

        public Criteria andBlogMediaIsNull() {
            addCriterion("blog_media is null");
            return (Criteria) this;
        }

        public Criteria andBlogMediaIsNotNull() {
            addCriterion("blog_media is not null");
            return (Criteria) this;
        }

        public Criteria andBlogMediaEqualTo(String value) {
            addCriterion("blog_media =", value, "blogMedia");
            return (Criteria) this;
        }

        public Criteria andBlogMediaNotEqualTo(String value) {
            addCriterion("blog_media <>", value, "blogMedia");
            return (Criteria) this;
        }

        public Criteria andBlogMediaGreaterThan(String value) {
            addCriterion("blog_media >", value, "blogMedia");
            return (Criteria) this;
        }

        public Criteria andBlogMediaGreaterThanOrEqualTo(String value) {
            addCriterion("blog_media >=", value, "blogMedia");
            return (Criteria) this;
        }

        public Criteria andBlogMediaLessThan(String value) {
            addCriterion("blog_media <", value, "blogMedia");
            return (Criteria) this;
        }

        public Criteria andBlogMediaLessThanOrEqualTo(String value) {
            addCriterion("blog_media <=", value, "blogMedia");
            return (Criteria) this;
        }

        public Criteria andBlogMediaLike(String value) {
            addCriterion("blog_media like", value, "blogMedia");
            return (Criteria) this;
        }

        public Criteria andBlogMediaNotLike(String value) {
            addCriterion("blog_media not like", value, "blogMedia");
            return (Criteria) this;
        }

        public Criteria andBlogMediaIn(List<String> values) {
            addCriterion("blog_media in", values, "blogMedia");
            return (Criteria) this;
        }

        public Criteria andBlogMediaNotIn(List<String> values) {
            addCriterion("blog_media not in", values, "blogMedia");
            return (Criteria) this;
        }

        public Criteria andBlogMediaBetween(String value1, String value2) {
            addCriterion("blog_media between", value1, value2, "blogMedia");
            return (Criteria) this;
        }

        public Criteria andBlogMediaNotBetween(String value1, String value2) {
            addCriterion("blog_media not between", value1, value2, "blogMedia");
            return (Criteria) this;
        }

        public Criteria andBlogTagIsNull() {
            addCriterion("blog_tag is null");
            return (Criteria) this;
        }

        public Criteria andBlogTagIsNotNull() {
            addCriterion("blog_tag is not null");
            return (Criteria) this;
        }

        public Criteria andBlogTagEqualTo(String value) {
            addCriterion("blog_tag =", value, "blogTag");
            return (Criteria) this;
        }

        public Criteria andBlogTagNotEqualTo(String value) {
            addCriterion("blog_tag <>", value, "blogTag");
            return (Criteria) this;
        }

        public Criteria andBlogTagGreaterThan(String value) {
            addCriterion("blog_tag >", value, "blogTag");
            return (Criteria) this;
        }

        public Criteria andBlogTagGreaterThanOrEqualTo(String value) {
            addCriterion("blog_tag >=", value, "blogTag");
            return (Criteria) this;
        }

        public Criteria andBlogTagLessThan(String value) {
            addCriterion("blog_tag <", value, "blogTag");
            return (Criteria) this;
        }

        public Criteria andBlogTagLessThanOrEqualTo(String value) {
            addCriterion("blog_tag <=", value, "blogTag");
            return (Criteria) this;
        }

        public Criteria andBlogTagLike(String value) {
            addCriterion("blog_tag like", value, "blogTag");
            return (Criteria) this;
        }

        public Criteria andBlogTagNotLike(String value) {
            addCriterion("blog_tag not like", value, "blogTag");
            return (Criteria) this;
        }

        public Criteria andBlogTagIn(List<String> values) {
            addCriterion("blog_tag in", values, "blogTag");
            return (Criteria) this;
        }

        public Criteria andBlogTagNotIn(List<String> values) {
            addCriterion("blog_tag not in", values, "blogTag");
            return (Criteria) this;
        }

        public Criteria andBlogTagBetween(String value1, String value2) {
            addCriterion("blog_tag between", value1, value2, "blogTag");
            return (Criteria) this;
        }

        public Criteria andBlogTagNotBetween(String value1, String value2) {
            addCriterion("blog_tag not between", value1, value2, "blogTag");
            return (Criteria) this;
        }

        public Criteria andBlogInfoIsNull() {
            addCriterion("blog_info is null");
            return (Criteria) this;
        }

        public Criteria andBlogInfoIsNotNull() {
            addCriterion("blog_info is not null");
            return (Criteria) this;
        }

        public Criteria andBlogInfoEqualTo(String value) {
            addCriterion("blog_info =", value, "blogInfo");
            return (Criteria) this;
        }

        public Criteria andBlogInfoNotEqualTo(String value) {
            addCriterion("blog_info <>", value, "blogInfo");
            return (Criteria) this;
        }

        public Criteria andBlogInfoGreaterThan(String value) {
            addCriterion("blog_info >", value, "blogInfo");
            return (Criteria) this;
        }

        public Criteria andBlogInfoGreaterThanOrEqualTo(String value) {
            addCriterion("blog_info >=", value, "blogInfo");
            return (Criteria) this;
        }

        public Criteria andBlogInfoLessThan(String value) {
            addCriterion("blog_info <", value, "blogInfo");
            return (Criteria) this;
        }

        public Criteria andBlogInfoLessThanOrEqualTo(String value) {
            addCriterion("blog_info <=", value, "blogInfo");
            return (Criteria) this;
        }

        public Criteria andBlogInfoLike(String value) {
            addCriterion("blog_info like", value, "blogInfo");
            return (Criteria) this;
        }

        public Criteria andBlogInfoNotLike(String value) {
            addCriterion("blog_info not like", value, "blogInfo");
            return (Criteria) this;
        }

        public Criteria andBlogInfoIn(List<String> values) {
            addCriterion("blog_info in", values, "blogInfo");
            return (Criteria) this;
        }

        public Criteria andBlogInfoNotIn(List<String> values) {
            addCriterion("blog_info not in", values, "blogInfo");
            return (Criteria) this;
        }

        public Criteria andBlogInfoBetween(String value1, String value2) {
            addCriterion("blog_info between", value1, value2, "blogInfo");
            return (Criteria) this;
        }

        public Criteria andBlogInfoNotBetween(String value1, String value2) {
            addCriterion("blog_info not between", value1, value2, "blogInfo");
            return (Criteria) this;
        }

        public Criteria andBlogVersionIsNull() {
            addCriterion("blog_version is null");
            return (Criteria) this;
        }

        public Criteria andBlogVersionIsNotNull() {
            addCriterion("blog_version is not null");
            return (Criteria) this;
        }

        public Criteria andBlogVersionEqualTo(Long value) {
            addCriterion("blog_version =", value, "blogVersion");
            return (Criteria) this;
        }

        public Criteria andBlogVersionNotEqualTo(Long value) {
            addCriterion("blog_version <>", value, "blogVersion");
            return (Criteria) this;
        }

        public Criteria andBlogVersionGreaterThan(Long value) {
            addCriterion("blog_version >", value, "blogVersion");
            return (Criteria) this;
        }

        public Criteria andBlogVersionGreaterThanOrEqualTo(Long value) {
            addCriterion("blog_version >=", value, "blogVersion");
            return (Criteria) this;
        }

        public Criteria andBlogVersionLessThan(Long value) {
            addCriterion("blog_version <", value, "blogVersion");
            return (Criteria) this;
        }

        public Criteria andBlogVersionLessThanOrEqualTo(Long value) {
            addCriterion("blog_version <=", value, "blogVersion");
            return (Criteria) this;
        }

        public Criteria andBlogVersionIn(List<Long> values) {
            addCriterion("blog_version in", values, "blogVersion");
            return (Criteria) this;
        }

        public Criteria andBlogVersionNotIn(List<Long> values) {
            addCriterion("blog_version not in", values, "blogVersion");
            return (Criteria) this;
        }

        public Criteria andBlogVersionBetween(Long value1, Long value2) {
            addCriterion("blog_version between", value1, value2, "blogVersion");
            return (Criteria) this;
        }

        public Criteria andBlogVersionNotBetween(Long value1, Long value2) {
            addCriterion("blog_version not between", value1, value2, "blogVersion");
            return (Criteria) this;
        }

        public Criteria andOriginalBlogIdIsNull() {
            addCriterion("original_blog_id is null");
            return (Criteria) this;
        }

        public Criteria andOriginalBlogIdIsNotNull() {
            addCriterion("original_blog_id is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalBlogIdEqualTo(Long value) {
            addCriterion("original_blog_id =", value, "originalBlogId");
            return (Criteria) this;
        }

        public Criteria andOriginalBlogIdNotEqualTo(Long value) {
            addCriterion("original_blog_id <>", value, "originalBlogId");
            return (Criteria) this;
        }

        public Criteria andOriginalBlogIdGreaterThan(Long value) {
            addCriterion("original_blog_id >", value, "originalBlogId");
            return (Criteria) this;
        }

        public Criteria andOriginalBlogIdGreaterThanOrEqualTo(Long value) {
            addCriterion("original_blog_id >=", value, "originalBlogId");
            return (Criteria) this;
        }

        public Criteria andOriginalBlogIdLessThan(Long value) {
            addCriterion("original_blog_id <", value, "originalBlogId");
            return (Criteria) this;
        }

        public Criteria andOriginalBlogIdLessThanOrEqualTo(Long value) {
            addCriterion("original_blog_id <=", value, "originalBlogId");
            return (Criteria) this;
        }

        public Criteria andOriginalBlogIdIn(List<Long> values) {
            addCriterion("original_blog_id in", values, "originalBlogId");
            return (Criteria) this;
        }

        public Criteria andOriginalBlogIdNotIn(List<Long> values) {
            addCriterion("original_blog_id not in", values, "originalBlogId");
            return (Criteria) this;
        }

        public Criteria andOriginalBlogIdBetween(Long value1, Long value2) {
            addCriterion("original_blog_id between", value1, value2, "originalBlogId");
            return (Criteria) this;
        }

        public Criteria andOriginalBlogIdNotBetween(Long value1, Long value2) {
            addCriterion("original_blog_id not between", value1, value2, "originalBlogId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}