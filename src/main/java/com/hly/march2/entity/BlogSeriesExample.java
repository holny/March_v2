package com.hly.march2.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlogSeriesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BlogSeriesExample() {
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

        public Criteria andSeriesShortNameIsNull() {
            addCriterion("series_short_name is null");
            return (Criteria) this;
        }

        public Criteria andSeriesShortNameIsNotNull() {
            addCriterion("series_short_name is not null");
            return (Criteria) this;
        }

        public Criteria andSeriesShortNameEqualTo(String value) {
            addCriterion("series_short_name =", value, "seriesShortName");
            return (Criteria) this;
        }

        public Criteria andSeriesShortNameNotEqualTo(String value) {
            addCriterion("series_short_name <>", value, "seriesShortName");
            return (Criteria) this;
        }

        public Criteria andSeriesShortNameGreaterThan(String value) {
            addCriterion("series_short_name >", value, "seriesShortName");
            return (Criteria) this;
        }

        public Criteria andSeriesShortNameGreaterThanOrEqualTo(String value) {
            addCriterion("series_short_name >=", value, "seriesShortName");
            return (Criteria) this;
        }

        public Criteria andSeriesShortNameLessThan(String value) {
            addCriterion("series_short_name <", value, "seriesShortName");
            return (Criteria) this;
        }

        public Criteria andSeriesShortNameLessThanOrEqualTo(String value) {
            addCriterion("series_short_name <=", value, "seriesShortName");
            return (Criteria) this;
        }

        public Criteria andSeriesShortNameLike(String value) {
            addCriterion("series_short_name like", value, "seriesShortName");
            return (Criteria) this;
        }

        public Criteria andSeriesShortNameNotLike(String value) {
            addCriterion("series_short_name not like", value, "seriesShortName");
            return (Criteria) this;
        }

        public Criteria andSeriesShortNameIn(List<String> values) {
            addCriterion("series_short_name in", values, "seriesShortName");
            return (Criteria) this;
        }

        public Criteria andSeriesShortNameNotIn(List<String> values) {
            addCriterion("series_short_name not in", values, "seriesShortName");
            return (Criteria) this;
        }

        public Criteria andSeriesShortNameBetween(String value1, String value2) {
            addCriterion("series_short_name between", value1, value2, "seriesShortName");
            return (Criteria) this;
        }

        public Criteria andSeriesShortNameNotBetween(String value1, String value2) {
            addCriterion("series_short_name not between", value1, value2, "seriesShortName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameIsNull() {
            addCriterion("series_name is null");
            return (Criteria) this;
        }

        public Criteria andSeriesNameIsNotNull() {
            addCriterion("series_name is not null");
            return (Criteria) this;
        }

        public Criteria andSeriesNameEqualTo(String value) {
            addCriterion("series_name =", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameNotEqualTo(String value) {
            addCriterion("series_name <>", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameGreaterThan(String value) {
            addCriterion("series_name >", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameGreaterThanOrEqualTo(String value) {
            addCriterion("series_name >=", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameLessThan(String value) {
            addCriterion("series_name <", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameLessThanOrEqualTo(String value) {
            addCriterion("series_name <=", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameLike(String value) {
            addCriterion("series_name like", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameNotLike(String value) {
            addCriterion("series_name not like", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameIn(List<String> values) {
            addCriterion("series_name in", values, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameNotIn(List<String> values) {
            addCriterion("series_name not in", values, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameBetween(String value1, String value2) {
            addCriterion("series_name between", value1, value2, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameNotBetween(String value1, String value2) {
            addCriterion("series_name not between", value1, value2, "seriesName");
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

        public Criteria andSeriesCreateTimeIsNull() {
            addCriterion("series_create_time is null");
            return (Criteria) this;
        }

        public Criteria andSeriesCreateTimeIsNotNull() {
            addCriterion("series_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andSeriesCreateTimeEqualTo(Date value) {
            addCriterion("series_create_time =", value, "seriesCreateTime");
            return (Criteria) this;
        }

        public Criteria andSeriesCreateTimeNotEqualTo(Date value) {
            addCriterion("series_create_time <>", value, "seriesCreateTime");
            return (Criteria) this;
        }

        public Criteria andSeriesCreateTimeGreaterThan(Date value) {
            addCriterion("series_create_time >", value, "seriesCreateTime");
            return (Criteria) this;
        }

        public Criteria andSeriesCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("series_create_time >=", value, "seriesCreateTime");
            return (Criteria) this;
        }

        public Criteria andSeriesCreateTimeLessThan(Date value) {
            addCriterion("series_create_time <", value, "seriesCreateTime");
            return (Criteria) this;
        }

        public Criteria andSeriesCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("series_create_time <=", value, "seriesCreateTime");
            return (Criteria) this;
        }

        public Criteria andSeriesCreateTimeIn(List<Date> values) {
            addCriterion("series_create_time in", values, "seriesCreateTime");
            return (Criteria) this;
        }

        public Criteria andSeriesCreateTimeNotIn(List<Date> values) {
            addCriterion("series_create_time not in", values, "seriesCreateTime");
            return (Criteria) this;
        }

        public Criteria andSeriesCreateTimeBetween(Date value1, Date value2) {
            addCriterion("series_create_time between", value1, value2, "seriesCreateTime");
            return (Criteria) this;
        }

        public Criteria andSeriesCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("series_create_time not between", value1, value2, "seriesCreateTime");
            return (Criteria) this;
        }

        public Criteria andSeriesUpdateTimeIsNull() {
            addCriterion("series_update_time is null");
            return (Criteria) this;
        }

        public Criteria andSeriesUpdateTimeIsNotNull() {
            addCriterion("series_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andSeriesUpdateTimeEqualTo(Date value) {
            addCriterion("series_update_time =", value, "seriesUpdateTime");
            return (Criteria) this;
        }

        public Criteria andSeriesUpdateTimeNotEqualTo(Date value) {
            addCriterion("series_update_time <>", value, "seriesUpdateTime");
            return (Criteria) this;
        }

        public Criteria andSeriesUpdateTimeGreaterThan(Date value) {
            addCriterion("series_update_time >", value, "seriesUpdateTime");
            return (Criteria) this;
        }

        public Criteria andSeriesUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("series_update_time >=", value, "seriesUpdateTime");
            return (Criteria) this;
        }

        public Criteria andSeriesUpdateTimeLessThan(Date value) {
            addCriterion("series_update_time <", value, "seriesUpdateTime");
            return (Criteria) this;
        }

        public Criteria andSeriesUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("series_update_time <=", value, "seriesUpdateTime");
            return (Criteria) this;
        }

        public Criteria andSeriesUpdateTimeIn(List<Date> values) {
            addCriterion("series_update_time in", values, "seriesUpdateTime");
            return (Criteria) this;
        }

        public Criteria andSeriesUpdateTimeNotIn(List<Date> values) {
            addCriterion("series_update_time not in", values, "seriesUpdateTime");
            return (Criteria) this;
        }

        public Criteria andSeriesUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("series_update_time between", value1, value2, "seriesUpdateTime");
            return (Criteria) this;
        }

        public Criteria andSeriesUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("series_update_time not between", value1, value2, "seriesUpdateTime");
            return (Criteria) this;
        }

        public Criteria andSeriesNumIsNull() {
            addCriterion("series_num is null");
            return (Criteria) this;
        }

        public Criteria andSeriesNumIsNotNull() {
            addCriterion("series_num is not null");
            return (Criteria) this;
        }

        public Criteria andSeriesNumEqualTo(Integer value) {
            addCriterion("series_num =", value, "seriesNum");
            return (Criteria) this;
        }

        public Criteria andSeriesNumNotEqualTo(Integer value) {
            addCriterion("series_num <>", value, "seriesNum");
            return (Criteria) this;
        }

        public Criteria andSeriesNumGreaterThan(Integer value) {
            addCriterion("series_num >", value, "seriesNum");
            return (Criteria) this;
        }

        public Criteria andSeriesNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("series_num >=", value, "seriesNum");
            return (Criteria) this;
        }

        public Criteria andSeriesNumLessThan(Integer value) {
            addCriterion("series_num <", value, "seriesNum");
            return (Criteria) this;
        }

        public Criteria andSeriesNumLessThanOrEqualTo(Integer value) {
            addCriterion("series_num <=", value, "seriesNum");
            return (Criteria) this;
        }

        public Criteria andSeriesNumIn(List<Integer> values) {
            addCriterion("series_num in", values, "seriesNum");
            return (Criteria) this;
        }

        public Criteria andSeriesNumNotIn(List<Integer> values) {
            addCriterion("series_num not in", values, "seriesNum");
            return (Criteria) this;
        }

        public Criteria andSeriesNumBetween(Integer value1, Integer value2) {
            addCriterion("series_num between", value1, value2, "seriesNum");
            return (Criteria) this;
        }

        public Criteria andSeriesNumNotBetween(Integer value1, Integer value2) {
            addCriterion("series_num not between", value1, value2, "seriesNum");
            return (Criteria) this;
        }

        public Criteria andSeriesStatusIsNull() {
            addCriterion("series_status is null");
            return (Criteria) this;
        }

        public Criteria andSeriesStatusIsNotNull() {
            addCriterion("series_status is not null");
            return (Criteria) this;
        }

        public Criteria andSeriesStatusEqualTo(Integer value) {
            addCriterion("series_status =", value, "seriesStatus");
            return (Criteria) this;
        }

        public Criteria andSeriesStatusNotEqualTo(Integer value) {
            addCriterion("series_status <>", value, "seriesStatus");
            return (Criteria) this;
        }

        public Criteria andSeriesStatusGreaterThan(Integer value) {
            addCriterion("series_status >", value, "seriesStatus");
            return (Criteria) this;
        }

        public Criteria andSeriesStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("series_status >=", value, "seriesStatus");
            return (Criteria) this;
        }

        public Criteria andSeriesStatusLessThan(Integer value) {
            addCriterion("series_status <", value, "seriesStatus");
            return (Criteria) this;
        }

        public Criteria andSeriesStatusLessThanOrEqualTo(Integer value) {
            addCriterion("series_status <=", value, "seriesStatus");
            return (Criteria) this;
        }

        public Criteria andSeriesStatusIn(List<Integer> values) {
            addCriterion("series_status in", values, "seriesStatus");
            return (Criteria) this;
        }

        public Criteria andSeriesStatusNotIn(List<Integer> values) {
            addCriterion("series_status not in", values, "seriesStatus");
            return (Criteria) this;
        }

        public Criteria andSeriesStatusBetween(Integer value1, Integer value2) {
            addCriterion("series_status between", value1, value2, "seriesStatus");
            return (Criteria) this;
        }

        public Criteria andSeriesStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("series_status not between", value1, value2, "seriesStatus");
            return (Criteria) this;
        }

        public Criteria andSeriesTypeIsNull() {
            addCriterion("series_type is null");
            return (Criteria) this;
        }

        public Criteria andSeriesTypeIsNotNull() {
            addCriterion("series_type is not null");
            return (Criteria) this;
        }

        public Criteria andSeriesTypeEqualTo(Integer value) {
            addCriterion("series_type =", value, "seriesType");
            return (Criteria) this;
        }

        public Criteria andSeriesTypeNotEqualTo(Integer value) {
            addCriterion("series_type <>", value, "seriesType");
            return (Criteria) this;
        }

        public Criteria andSeriesTypeGreaterThan(Integer value) {
            addCriterion("series_type >", value, "seriesType");
            return (Criteria) this;
        }

        public Criteria andSeriesTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("series_type >=", value, "seriesType");
            return (Criteria) this;
        }

        public Criteria andSeriesTypeLessThan(Integer value) {
            addCriterion("series_type <", value, "seriesType");
            return (Criteria) this;
        }

        public Criteria andSeriesTypeLessThanOrEqualTo(Integer value) {
            addCriterion("series_type <=", value, "seriesType");
            return (Criteria) this;
        }

        public Criteria andSeriesTypeIn(List<Integer> values) {
            addCriterion("series_type in", values, "seriesType");
            return (Criteria) this;
        }

        public Criteria andSeriesTypeNotIn(List<Integer> values) {
            addCriterion("series_type not in", values, "seriesType");
            return (Criteria) this;
        }

        public Criteria andSeriesTypeBetween(Integer value1, Integer value2) {
            addCriterion("series_type between", value1, value2, "seriesType");
            return (Criteria) this;
        }

        public Criteria andSeriesTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("series_type not between", value1, value2, "seriesType");
            return (Criteria) this;
        }

        public Criteria andSeriesLinkIsNull() {
            addCriterion("series_link is null");
            return (Criteria) this;
        }

        public Criteria andSeriesLinkIsNotNull() {
            addCriterion("series_link is not null");
            return (Criteria) this;
        }

        public Criteria andSeriesLinkEqualTo(String value) {
            addCriterion("series_link =", value, "seriesLink");
            return (Criteria) this;
        }

        public Criteria andSeriesLinkNotEqualTo(String value) {
            addCriterion("series_link <>", value, "seriesLink");
            return (Criteria) this;
        }

        public Criteria andSeriesLinkGreaterThan(String value) {
            addCriterion("series_link >", value, "seriesLink");
            return (Criteria) this;
        }

        public Criteria andSeriesLinkGreaterThanOrEqualTo(String value) {
            addCriterion("series_link >=", value, "seriesLink");
            return (Criteria) this;
        }

        public Criteria andSeriesLinkLessThan(String value) {
            addCriterion("series_link <", value, "seriesLink");
            return (Criteria) this;
        }

        public Criteria andSeriesLinkLessThanOrEqualTo(String value) {
            addCriterion("series_link <=", value, "seriesLink");
            return (Criteria) this;
        }

        public Criteria andSeriesLinkLike(String value) {
            addCriterion("series_link like", value, "seriesLink");
            return (Criteria) this;
        }

        public Criteria andSeriesLinkNotLike(String value) {
            addCriterion("series_link not like", value, "seriesLink");
            return (Criteria) this;
        }

        public Criteria andSeriesLinkIn(List<String> values) {
            addCriterion("series_link in", values, "seriesLink");
            return (Criteria) this;
        }

        public Criteria andSeriesLinkNotIn(List<String> values) {
            addCriterion("series_link not in", values, "seriesLink");
            return (Criteria) this;
        }

        public Criteria andSeriesLinkBetween(String value1, String value2) {
            addCriterion("series_link between", value1, value2, "seriesLink");
            return (Criteria) this;
        }

        public Criteria andSeriesLinkNotBetween(String value1, String value2) {
            addCriterion("series_link not between", value1, value2, "seriesLink");
            return (Criteria) this;
        }

        public Criteria andSeriesPopularIsNull() {
            addCriterion("series_popular is null");
            return (Criteria) this;
        }

        public Criteria andSeriesPopularIsNotNull() {
            addCriterion("series_popular is not null");
            return (Criteria) this;
        }

        public Criteria andSeriesPopularEqualTo(Long value) {
            addCriterion("series_popular =", value, "seriesPopular");
            return (Criteria) this;
        }

        public Criteria andSeriesPopularNotEqualTo(Long value) {
            addCriterion("series_popular <>", value, "seriesPopular");
            return (Criteria) this;
        }

        public Criteria andSeriesPopularGreaterThan(Long value) {
            addCriterion("series_popular >", value, "seriesPopular");
            return (Criteria) this;
        }

        public Criteria andSeriesPopularGreaterThanOrEqualTo(Long value) {
            addCriterion("series_popular >=", value, "seriesPopular");
            return (Criteria) this;
        }

        public Criteria andSeriesPopularLessThan(Long value) {
            addCriterion("series_popular <", value, "seriesPopular");
            return (Criteria) this;
        }

        public Criteria andSeriesPopularLessThanOrEqualTo(Long value) {
            addCriterion("series_popular <=", value, "seriesPopular");
            return (Criteria) this;
        }

        public Criteria andSeriesPopularIn(List<Long> values) {
            addCriterion("series_popular in", values, "seriesPopular");
            return (Criteria) this;
        }

        public Criteria andSeriesPopularNotIn(List<Long> values) {
            addCriterion("series_popular not in", values, "seriesPopular");
            return (Criteria) this;
        }

        public Criteria andSeriesPopularBetween(Long value1, Long value2) {
            addCriterion("series_popular between", value1, value2, "seriesPopular");
            return (Criteria) this;
        }

        public Criteria andSeriesPopularNotBetween(Long value1, Long value2) {
            addCriterion("series_popular not between", value1, value2, "seriesPopular");
            return (Criteria) this;
        }

        public Criteria andSeriesIntroIsNull() {
            addCriterion("series_intro is null");
            return (Criteria) this;
        }

        public Criteria andSeriesIntroIsNotNull() {
            addCriterion("series_intro is not null");
            return (Criteria) this;
        }

        public Criteria andSeriesIntroEqualTo(String value) {
            addCriterion("series_intro =", value, "seriesIntro");
            return (Criteria) this;
        }

        public Criteria andSeriesIntroNotEqualTo(String value) {
            addCriterion("series_intro <>", value, "seriesIntro");
            return (Criteria) this;
        }

        public Criteria andSeriesIntroGreaterThan(String value) {
            addCriterion("series_intro >", value, "seriesIntro");
            return (Criteria) this;
        }

        public Criteria andSeriesIntroGreaterThanOrEqualTo(String value) {
            addCriterion("series_intro >=", value, "seriesIntro");
            return (Criteria) this;
        }

        public Criteria andSeriesIntroLessThan(String value) {
            addCriterion("series_intro <", value, "seriesIntro");
            return (Criteria) this;
        }

        public Criteria andSeriesIntroLessThanOrEqualTo(String value) {
            addCriterion("series_intro <=", value, "seriesIntro");
            return (Criteria) this;
        }

        public Criteria andSeriesIntroLike(String value) {
            addCriterion("series_intro like", value, "seriesIntro");
            return (Criteria) this;
        }

        public Criteria andSeriesIntroNotLike(String value) {
            addCriterion("series_intro not like", value, "seriesIntro");
            return (Criteria) this;
        }

        public Criteria andSeriesIntroIn(List<String> values) {
            addCriterion("series_intro in", values, "seriesIntro");
            return (Criteria) this;
        }

        public Criteria andSeriesIntroNotIn(List<String> values) {
            addCriterion("series_intro not in", values, "seriesIntro");
            return (Criteria) this;
        }

        public Criteria andSeriesIntroBetween(String value1, String value2) {
            addCriterion("series_intro between", value1, value2, "seriesIntro");
            return (Criteria) this;
        }

        public Criteria andSeriesIntroNotBetween(String value1, String value2) {
            addCriterion("series_intro not between", value1, value2, "seriesIntro");
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