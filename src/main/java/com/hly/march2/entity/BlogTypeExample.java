package com.hly.march2.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlogTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BlogTypeExample() {
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

        public Criteria andTypeIdIsNull() {
            addCriterion("type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(Integer value) {
            addCriterion("type_id =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(Integer value) {
            addCriterion("type_id <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(Integer value) {
            addCriterion("type_id >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("type_id >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(Integer value) {
            addCriterion("type_id <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("type_id <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<Integer> values) {
            addCriterion("type_id in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<Integer> values) {
            addCriterion("type_id not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("type_id between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("type_id not between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNull() {
            addCriterion("type_name is null");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNotNull() {
            addCriterion("type_name is not null");
            return (Criteria) this;
        }

        public Criteria andTypeNameEqualTo(String value) {
            addCriterion("type_name =", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotEqualTo(String value) {
            addCriterion("type_name <>", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThan(String value) {
            addCriterion("type_name >", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("type_name >=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThan(String value) {
            addCriterion("type_name <", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThanOrEqualTo(String value) {
            addCriterion("type_name <=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLike(String value) {
            addCriterion("type_name like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotLike(String value) {
            addCriterion("type_name not like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameIn(List<String> values) {
            addCriterion("type_name in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotIn(List<String> values) {
            addCriterion("type_name not in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameBetween(String value1, String value2) {
            addCriterion("type_name between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotBetween(String value1, String value2) {
            addCriterion("type_name not between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeCreateTimeIsNull() {
            addCriterion("type_create_time is null");
            return (Criteria) this;
        }

        public Criteria andTypeCreateTimeIsNotNull() {
            addCriterion("type_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andTypeCreateTimeEqualTo(Date value) {
            addCriterion("type_create_time =", value, "typeCreateTime");
            return (Criteria) this;
        }

        public Criteria andTypeCreateTimeNotEqualTo(Date value) {
            addCriterion("type_create_time <>", value, "typeCreateTime");
            return (Criteria) this;
        }

        public Criteria andTypeCreateTimeGreaterThan(Date value) {
            addCriterion("type_create_time >", value, "typeCreateTime");
            return (Criteria) this;
        }

        public Criteria andTypeCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("type_create_time >=", value, "typeCreateTime");
            return (Criteria) this;
        }

        public Criteria andTypeCreateTimeLessThan(Date value) {
            addCriterion("type_create_time <", value, "typeCreateTime");
            return (Criteria) this;
        }

        public Criteria andTypeCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("type_create_time <=", value, "typeCreateTime");
            return (Criteria) this;
        }

        public Criteria andTypeCreateTimeIn(List<Date> values) {
            addCriterion("type_create_time in", values, "typeCreateTime");
            return (Criteria) this;
        }

        public Criteria andTypeCreateTimeNotIn(List<Date> values) {
            addCriterion("type_create_time not in", values, "typeCreateTime");
            return (Criteria) this;
        }

        public Criteria andTypeCreateTimeBetween(Date value1, Date value2) {
            addCriterion("type_create_time between", value1, value2, "typeCreateTime");
            return (Criteria) this;
        }

        public Criteria andTypeCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("type_create_time not between", value1, value2, "typeCreateTime");
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

        public Criteria andTypeStatusIsNull() {
            addCriterion("type_status is null");
            return (Criteria) this;
        }

        public Criteria andTypeStatusIsNotNull() {
            addCriterion("type_status is not null");
            return (Criteria) this;
        }

        public Criteria andTypeStatusEqualTo(Integer value) {
            addCriterion("type_status =", value, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeStatusNotEqualTo(Integer value) {
            addCriterion("type_status <>", value, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeStatusGreaterThan(Integer value) {
            addCriterion("type_status >", value, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("type_status >=", value, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeStatusLessThan(Integer value) {
            addCriterion("type_status <", value, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeStatusLessThanOrEqualTo(Integer value) {
            addCriterion("type_status <=", value, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeStatusIn(List<Integer> values) {
            addCriterion("type_status in", values, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeStatusNotIn(List<Integer> values) {
            addCriterion("type_status not in", values, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeStatusBetween(Integer value1, Integer value2) {
            addCriterion("type_status between", value1, value2, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("type_status not between", value1, value2, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeLinkIsNull() {
            addCriterion("type_link is null");
            return (Criteria) this;
        }

        public Criteria andTypeLinkIsNotNull() {
            addCriterion("type_link is not null");
            return (Criteria) this;
        }

        public Criteria andTypeLinkEqualTo(String value) {
            addCriterion("type_link =", value, "typeLink");
            return (Criteria) this;
        }

        public Criteria andTypeLinkNotEqualTo(String value) {
            addCriterion("type_link <>", value, "typeLink");
            return (Criteria) this;
        }

        public Criteria andTypeLinkGreaterThan(String value) {
            addCriterion("type_link >", value, "typeLink");
            return (Criteria) this;
        }

        public Criteria andTypeLinkGreaterThanOrEqualTo(String value) {
            addCriterion("type_link >=", value, "typeLink");
            return (Criteria) this;
        }

        public Criteria andTypeLinkLessThan(String value) {
            addCriterion("type_link <", value, "typeLink");
            return (Criteria) this;
        }

        public Criteria andTypeLinkLessThanOrEqualTo(String value) {
            addCriterion("type_link <=", value, "typeLink");
            return (Criteria) this;
        }

        public Criteria andTypeLinkLike(String value) {
            addCriterion("type_link like", value, "typeLink");
            return (Criteria) this;
        }

        public Criteria andTypeLinkNotLike(String value) {
            addCriterion("type_link not like", value, "typeLink");
            return (Criteria) this;
        }

        public Criteria andTypeLinkIn(List<String> values) {
            addCriterion("type_link in", values, "typeLink");
            return (Criteria) this;
        }

        public Criteria andTypeLinkNotIn(List<String> values) {
            addCriterion("type_link not in", values, "typeLink");
            return (Criteria) this;
        }

        public Criteria andTypeLinkBetween(String value1, String value2) {
            addCriterion("type_link between", value1, value2, "typeLink");
            return (Criteria) this;
        }

        public Criteria andTypeLinkNotBetween(String value1, String value2) {
            addCriterion("type_link not between", value1, value2, "typeLink");
            return (Criteria) this;
        }

        public Criteria andTypePopularIsNull() {
            addCriterion("type_popular is null");
            return (Criteria) this;
        }

        public Criteria andTypePopularIsNotNull() {
            addCriterion("type_popular is not null");
            return (Criteria) this;
        }

        public Criteria andTypePopularEqualTo(Long value) {
            addCriterion("type_popular =", value, "typePopular");
            return (Criteria) this;
        }

        public Criteria andTypePopularNotEqualTo(Long value) {
            addCriterion("type_popular <>", value, "typePopular");
            return (Criteria) this;
        }

        public Criteria andTypePopularGreaterThan(Long value) {
            addCriterion("type_popular >", value, "typePopular");
            return (Criteria) this;
        }

        public Criteria andTypePopularGreaterThanOrEqualTo(Long value) {
            addCriterion("type_popular >=", value, "typePopular");
            return (Criteria) this;
        }

        public Criteria andTypePopularLessThan(Long value) {
            addCriterion("type_popular <", value, "typePopular");
            return (Criteria) this;
        }

        public Criteria andTypePopularLessThanOrEqualTo(Long value) {
            addCriterion("type_popular <=", value, "typePopular");
            return (Criteria) this;
        }

        public Criteria andTypePopularIn(List<Long> values) {
            addCriterion("type_popular in", values, "typePopular");
            return (Criteria) this;
        }

        public Criteria andTypePopularNotIn(List<Long> values) {
            addCriterion("type_popular not in", values, "typePopular");
            return (Criteria) this;
        }

        public Criteria andTypePopularBetween(Long value1, Long value2) {
            addCriterion("type_popular between", value1, value2, "typePopular");
            return (Criteria) this;
        }

        public Criteria andTypePopularNotBetween(Long value1, Long value2) {
            addCriterion("type_popular not between", value1, value2, "typePopular");
            return (Criteria) this;
        }

        public Criteria andTypeNumIsNull() {
            addCriterion("type_num is null");
            return (Criteria) this;
        }

        public Criteria andTypeNumIsNotNull() {
            addCriterion("type_num is not null");
            return (Criteria) this;
        }

        public Criteria andTypeNumEqualTo(Integer value) {
            addCriterion("type_num =", value, "typeNum");
            return (Criteria) this;
        }

        public Criteria andTypeNumNotEqualTo(Integer value) {
            addCriterion("type_num <>", value, "typeNum");
            return (Criteria) this;
        }

        public Criteria andTypeNumGreaterThan(Integer value) {
            addCriterion("type_num >", value, "typeNum");
            return (Criteria) this;
        }

        public Criteria andTypeNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("type_num >=", value, "typeNum");
            return (Criteria) this;
        }

        public Criteria andTypeNumLessThan(Integer value) {
            addCriterion("type_num <", value, "typeNum");
            return (Criteria) this;
        }

        public Criteria andTypeNumLessThanOrEqualTo(Integer value) {
            addCriterion("type_num <=", value, "typeNum");
            return (Criteria) this;
        }

        public Criteria andTypeNumIn(List<Integer> values) {
            addCriterion("type_num in", values, "typeNum");
            return (Criteria) this;
        }

        public Criteria andTypeNumNotIn(List<Integer> values) {
            addCriterion("type_num not in", values, "typeNum");
            return (Criteria) this;
        }

        public Criteria andTypeNumBetween(Integer value1, Integer value2) {
            addCriterion("type_num between", value1, value2, "typeNum");
            return (Criteria) this;
        }

        public Criteria andTypeNumNotBetween(Integer value1, Integer value2) {
            addCriterion("type_num not between", value1, value2, "typeNum");
            return (Criteria) this;
        }

        public Criteria andTypeShortNameIsNull() {
            addCriterion("type_short_name is null");
            return (Criteria) this;
        }

        public Criteria andTypeShortNameIsNotNull() {
            addCriterion("type_short_name is not null");
            return (Criteria) this;
        }

        public Criteria andTypeShortNameEqualTo(String value) {
            addCriterion("type_short_name =", value, "typeShortName");
            return (Criteria) this;
        }

        public Criteria andTypeShortNameNotEqualTo(String value) {
            addCriterion("type_short_name <>", value, "typeShortName");
            return (Criteria) this;
        }

        public Criteria andTypeShortNameGreaterThan(String value) {
            addCriterion("type_short_name >", value, "typeShortName");
            return (Criteria) this;
        }

        public Criteria andTypeShortNameGreaterThanOrEqualTo(String value) {
            addCriterion("type_short_name >=", value, "typeShortName");
            return (Criteria) this;
        }

        public Criteria andTypeShortNameLessThan(String value) {
            addCriterion("type_short_name <", value, "typeShortName");
            return (Criteria) this;
        }

        public Criteria andTypeShortNameLessThanOrEqualTo(String value) {
            addCriterion("type_short_name <=", value, "typeShortName");
            return (Criteria) this;
        }

        public Criteria andTypeShortNameLike(String value) {
            addCriterion("type_short_name like", value, "typeShortName");
            return (Criteria) this;
        }

        public Criteria andTypeShortNameNotLike(String value) {
            addCriterion("type_short_name not like", value, "typeShortName");
            return (Criteria) this;
        }

        public Criteria andTypeShortNameIn(List<String> values) {
            addCriterion("type_short_name in", values, "typeShortName");
            return (Criteria) this;
        }

        public Criteria andTypeShortNameNotIn(List<String> values) {
            addCriterion("type_short_name not in", values, "typeShortName");
            return (Criteria) this;
        }

        public Criteria andTypeShortNameBetween(String value1, String value2) {
            addCriterion("type_short_name between", value1, value2, "typeShortName");
            return (Criteria) this;
        }

        public Criteria andTypeShortNameNotBetween(String value1, String value2) {
            addCriterion("type_short_name not between", value1, value2, "typeShortName");
            return (Criteria) this;
        }

        public Criteria andFartherTypeIsNull() {
            addCriterion("farther_type is null");
            return (Criteria) this;
        }

        public Criteria andFartherTypeIsNotNull() {
            addCriterion("farther_type is not null");
            return (Criteria) this;
        }

        public Criteria andFartherTypeEqualTo(String value) {
            addCriterion("farther_type =", value, "fartherType");
            return (Criteria) this;
        }

        public Criteria andFartherTypeNotEqualTo(String value) {
            addCriterion("farther_type <>", value, "fartherType");
            return (Criteria) this;
        }

        public Criteria andFartherTypeGreaterThan(String value) {
            addCriterion("farther_type >", value, "fartherType");
            return (Criteria) this;
        }

        public Criteria andFartherTypeGreaterThanOrEqualTo(String value) {
            addCriterion("farther_type >=", value, "fartherType");
            return (Criteria) this;
        }

        public Criteria andFartherTypeLessThan(String value) {
            addCriterion("farther_type <", value, "fartherType");
            return (Criteria) this;
        }

        public Criteria andFartherTypeLessThanOrEqualTo(String value) {
            addCriterion("farther_type <=", value, "fartherType");
            return (Criteria) this;
        }

        public Criteria andFartherTypeLike(String value) {
            addCriterion("farther_type like", value, "fartherType");
            return (Criteria) this;
        }

        public Criteria andFartherTypeNotLike(String value) {
            addCriterion("farther_type not like", value, "fartherType");
            return (Criteria) this;
        }

        public Criteria andFartherTypeIn(List<String> values) {
            addCriterion("farther_type in", values, "fartherType");
            return (Criteria) this;
        }

        public Criteria andFartherTypeNotIn(List<String> values) {
            addCriterion("farther_type not in", values, "fartherType");
            return (Criteria) this;
        }

        public Criteria andFartherTypeBetween(String value1, String value2) {
            addCriterion("farther_type between", value1, value2, "fartherType");
            return (Criteria) this;
        }

        public Criteria andFartherTypeNotBetween(String value1, String value2) {
            addCriterion("farther_type not between", value1, value2, "fartherType");
            return (Criteria) this;
        }

        public Criteria andFartherIdIsNull() {
            addCriterion("farther_id is null");
            return (Criteria) this;
        }

        public Criteria andFartherIdIsNotNull() {
            addCriterion("farther_id is not null");
            return (Criteria) this;
        }

        public Criteria andFartherIdEqualTo(Integer value) {
            addCriterion("farther_id =", value, "fartherId");
            return (Criteria) this;
        }

        public Criteria andFartherIdNotEqualTo(Integer value) {
            addCriterion("farther_id <>", value, "fartherId");
            return (Criteria) this;
        }

        public Criteria andFartherIdGreaterThan(Integer value) {
            addCriterion("farther_id >", value, "fartherId");
            return (Criteria) this;
        }

        public Criteria andFartherIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("farther_id >=", value, "fartherId");
            return (Criteria) this;
        }

        public Criteria andFartherIdLessThan(Integer value) {
            addCriterion("farther_id <", value, "fartherId");
            return (Criteria) this;
        }

        public Criteria andFartherIdLessThanOrEqualTo(Integer value) {
            addCriterion("farther_id <=", value, "fartherId");
            return (Criteria) this;
        }

        public Criteria andFartherIdIn(List<Integer> values) {
            addCriterion("farther_id in", values, "fartherId");
            return (Criteria) this;
        }

        public Criteria andFartherIdNotIn(List<Integer> values) {
            addCriterion("farther_id not in", values, "fartherId");
            return (Criteria) this;
        }

        public Criteria andFartherIdBetween(Integer value1, Integer value2) {
            addCriterion("farther_id between", value1, value2, "fartherId");
            return (Criteria) this;
        }

        public Criteria andFartherIdNotBetween(Integer value1, Integer value2) {
            addCriterion("farther_id not between", value1, value2, "fartherId");
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