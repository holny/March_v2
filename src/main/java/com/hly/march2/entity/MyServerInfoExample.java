package com.hly.march2.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyServerInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MyServerInfoExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFirstStartTimeIsNull() {
            addCriterion("first_start_time is null");
            return (Criteria) this;
        }

        public Criteria andFirstStartTimeIsNotNull() {
            addCriterion("first_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andFirstStartTimeEqualTo(Date value) {
            addCriterion("first_start_time =", value, "firstStartTime");
            return (Criteria) this;
        }

        public Criteria andFirstStartTimeNotEqualTo(Date value) {
            addCriterion("first_start_time <>", value, "firstStartTime");
            return (Criteria) this;
        }

        public Criteria andFirstStartTimeGreaterThan(Date value) {
            addCriterion("first_start_time >", value, "firstStartTime");
            return (Criteria) this;
        }

        public Criteria andFirstStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("first_start_time >=", value, "firstStartTime");
            return (Criteria) this;
        }

        public Criteria andFirstStartTimeLessThan(Date value) {
            addCriterion("first_start_time <", value, "firstStartTime");
            return (Criteria) this;
        }

        public Criteria andFirstStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("first_start_time <=", value, "firstStartTime");
            return (Criteria) this;
        }

        public Criteria andFirstStartTimeIn(List<Date> values) {
            addCriterion("first_start_time in", values, "firstStartTime");
            return (Criteria) this;
        }

        public Criteria andFirstStartTimeNotIn(List<Date> values) {
            addCriterion("first_start_time not in", values, "firstStartTime");
            return (Criteria) this;
        }

        public Criteria andFirstStartTimeBetween(Date value1, Date value2) {
            addCriterion("first_start_time between", value1, value2, "firstStartTime");
            return (Criteria) this;
        }

        public Criteria andFirstStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("first_start_time not between", value1, value2, "firstStartTime");
            return (Criteria) this;
        }

        public Criteria andTotalRunningTimeIsNull() {
            addCriterion("total_running_time is null");
            return (Criteria) this;
        }

        public Criteria andTotalRunningTimeIsNotNull() {
            addCriterion("total_running_time is not null");
            return (Criteria) this;
        }

        public Criteria andTotalRunningTimeEqualTo(Long value) {
            addCriterion("total_running_time =", value, "totalRunningTime");
            return (Criteria) this;
        }

        public Criteria andTotalRunningTimeNotEqualTo(Long value) {
            addCriterion("total_running_time <>", value, "totalRunningTime");
            return (Criteria) this;
        }

        public Criteria andTotalRunningTimeGreaterThan(Long value) {
            addCriterion("total_running_time >", value, "totalRunningTime");
            return (Criteria) this;
        }

        public Criteria andTotalRunningTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("total_running_time >=", value, "totalRunningTime");
            return (Criteria) this;
        }

        public Criteria andTotalRunningTimeLessThan(Long value) {
            addCriterion("total_running_time <", value, "totalRunningTime");
            return (Criteria) this;
        }

        public Criteria andTotalRunningTimeLessThanOrEqualTo(Long value) {
            addCriterion("total_running_time <=", value, "totalRunningTime");
            return (Criteria) this;
        }

        public Criteria andTotalRunningTimeIn(List<Long> values) {
            addCriterion("total_running_time in", values, "totalRunningTime");
            return (Criteria) this;
        }

        public Criteria andTotalRunningTimeNotIn(List<Long> values) {
            addCriterion("total_running_time not in", values, "totalRunningTime");
            return (Criteria) this;
        }

        public Criteria andTotalRunningTimeBetween(Long value1, Long value2) {
            addCriterion("total_running_time between", value1, value2, "totalRunningTime");
            return (Criteria) this;
        }

        public Criteria andTotalRunningTimeNotBetween(Long value1, Long value2) {
            addCriterion("total_running_time not between", value1, value2, "totalRunningTime");
            return (Criteria) this;
        }

        public Criteria andThisStartTimeIsNull() {
            addCriterion("this_start_time is null");
            return (Criteria) this;
        }

        public Criteria andThisStartTimeIsNotNull() {
            addCriterion("this_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andThisStartTimeEqualTo(Date value) {
            addCriterion("this_start_time =", value, "thisStartTime");
            return (Criteria) this;
        }

        public Criteria andThisStartTimeNotEqualTo(Date value) {
            addCriterion("this_start_time <>", value, "thisStartTime");
            return (Criteria) this;
        }

        public Criteria andThisStartTimeGreaterThan(Date value) {
            addCriterion("this_start_time >", value, "thisStartTime");
            return (Criteria) this;
        }

        public Criteria andThisStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("this_start_time >=", value, "thisStartTime");
            return (Criteria) this;
        }

        public Criteria andThisStartTimeLessThan(Date value) {
            addCriterion("this_start_time <", value, "thisStartTime");
            return (Criteria) this;
        }

        public Criteria andThisStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("this_start_time <=", value, "thisStartTime");
            return (Criteria) this;
        }

        public Criteria andThisStartTimeIn(List<Date> values) {
            addCriterion("this_start_time in", values, "thisStartTime");
            return (Criteria) this;
        }

        public Criteria andThisStartTimeNotIn(List<Date> values) {
            addCriterion("this_start_time not in", values, "thisStartTime");
            return (Criteria) this;
        }

        public Criteria andThisStartTimeBetween(Date value1, Date value2) {
            addCriterion("this_start_time between", value1, value2, "thisStartTime");
            return (Criteria) this;
        }

        public Criteria andThisStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("this_start_time not between", value1, value2, "thisStartTime");
            return (Criteria) this;
        }

        public Criteria andThisEndTimeIsNull() {
            addCriterion("this_end_time is null");
            return (Criteria) this;
        }

        public Criteria andThisEndTimeIsNotNull() {
            addCriterion("this_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andThisEndTimeEqualTo(Date value) {
            addCriterion("this_end_time =", value, "thisEndTime");
            return (Criteria) this;
        }

        public Criteria andThisEndTimeNotEqualTo(Date value) {
            addCriterion("this_end_time <>", value, "thisEndTime");
            return (Criteria) this;
        }

        public Criteria andThisEndTimeGreaterThan(Date value) {
            addCriterion("this_end_time >", value, "thisEndTime");
            return (Criteria) this;
        }

        public Criteria andThisEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("this_end_time >=", value, "thisEndTime");
            return (Criteria) this;
        }

        public Criteria andThisEndTimeLessThan(Date value) {
            addCriterion("this_end_time <", value, "thisEndTime");
            return (Criteria) this;
        }

        public Criteria andThisEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("this_end_time <=", value, "thisEndTime");
            return (Criteria) this;
        }

        public Criteria andThisEndTimeIn(List<Date> values) {
            addCriterion("this_end_time in", values, "thisEndTime");
            return (Criteria) this;
        }

        public Criteria andThisEndTimeNotIn(List<Date> values) {
            addCriterion("this_end_time not in", values, "thisEndTime");
            return (Criteria) this;
        }

        public Criteria andThisEndTimeBetween(Date value1, Date value2) {
            addCriterion("this_end_time between", value1, value2, "thisEndTime");
            return (Criteria) this;
        }

        public Criteria andThisEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("this_end_time not between", value1, value2, "thisEndTime");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorNumIsNull() {
            addCriterion("total_visitor_num is null");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorNumIsNotNull() {
            addCriterion("total_visitor_num is not null");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorNumEqualTo(Long value) {
            addCriterion("total_visitor_num =", value, "totalVisitorNum");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorNumNotEqualTo(Long value) {
            addCriterion("total_visitor_num <>", value, "totalVisitorNum");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorNumGreaterThan(Long value) {
            addCriterion("total_visitor_num >", value, "totalVisitorNum");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorNumGreaterThanOrEqualTo(Long value) {
            addCriterion("total_visitor_num >=", value, "totalVisitorNum");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorNumLessThan(Long value) {
            addCriterion("total_visitor_num <", value, "totalVisitorNum");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorNumLessThanOrEqualTo(Long value) {
            addCriterion("total_visitor_num <=", value, "totalVisitorNum");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorNumIn(List<Long> values) {
            addCriterion("total_visitor_num in", values, "totalVisitorNum");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorNumNotIn(List<Long> values) {
            addCriterion("total_visitor_num not in", values, "totalVisitorNum");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorNumBetween(Long value1, Long value2) {
            addCriterion("total_visitor_num between", value1, value2, "totalVisitorNum");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorNumNotBetween(Long value1, Long value2) {
            addCriterion("total_visitor_num not between", value1, value2, "totalVisitorNum");
            return (Criteria) this;
        }

        public Criteria andThisVisitorNumIsNull() {
            addCriterion("this_visitor_num is null");
            return (Criteria) this;
        }

        public Criteria andThisVisitorNumIsNotNull() {
            addCriterion("this_visitor_num is not null");
            return (Criteria) this;
        }

        public Criteria andThisVisitorNumEqualTo(Long value) {
            addCriterion("this_visitor_num =", value, "thisVisitorNum");
            return (Criteria) this;
        }

        public Criteria andThisVisitorNumNotEqualTo(Long value) {
            addCriterion("this_visitor_num <>", value, "thisVisitorNum");
            return (Criteria) this;
        }

        public Criteria andThisVisitorNumGreaterThan(Long value) {
            addCriterion("this_visitor_num >", value, "thisVisitorNum");
            return (Criteria) this;
        }

        public Criteria andThisVisitorNumGreaterThanOrEqualTo(Long value) {
            addCriterion("this_visitor_num >=", value, "thisVisitorNum");
            return (Criteria) this;
        }

        public Criteria andThisVisitorNumLessThan(Long value) {
            addCriterion("this_visitor_num <", value, "thisVisitorNum");
            return (Criteria) this;
        }

        public Criteria andThisVisitorNumLessThanOrEqualTo(Long value) {
            addCriterion("this_visitor_num <=", value, "thisVisitorNum");
            return (Criteria) this;
        }

        public Criteria andThisVisitorNumIn(List<Long> values) {
            addCriterion("this_visitor_num in", values, "thisVisitorNum");
            return (Criteria) this;
        }

        public Criteria andThisVisitorNumNotIn(List<Long> values) {
            addCriterion("this_visitor_num not in", values, "thisVisitorNum");
            return (Criteria) this;
        }

        public Criteria andThisVisitorNumBetween(Long value1, Long value2) {
            addCriterion("this_visitor_num between", value1, value2, "thisVisitorNum");
            return (Criteria) this;
        }

        public Criteria andThisVisitorNumNotBetween(Long value1, Long value2) {
            addCriterion("this_visitor_num not between", value1, value2, "thisVisitorNum");
            return (Criteria) this;
        }

        public Criteria andThisUpdateTimeIsNull() {
            addCriterion("this_update_time is null");
            return (Criteria) this;
        }

        public Criteria andThisUpdateTimeIsNotNull() {
            addCriterion("this_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andThisUpdateTimeEqualTo(Date value) {
            addCriterion("this_update_time =", value, "thisUpdateTime");
            return (Criteria) this;
        }

        public Criteria andThisUpdateTimeNotEqualTo(Date value) {
            addCriterion("this_update_time <>", value, "thisUpdateTime");
            return (Criteria) this;
        }

        public Criteria andThisUpdateTimeGreaterThan(Date value) {
            addCriterion("this_update_time >", value, "thisUpdateTime");
            return (Criteria) this;
        }

        public Criteria andThisUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("this_update_time >=", value, "thisUpdateTime");
            return (Criteria) this;
        }

        public Criteria andThisUpdateTimeLessThan(Date value) {
            addCriterion("this_update_time <", value, "thisUpdateTime");
            return (Criteria) this;
        }

        public Criteria andThisUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("this_update_time <=", value, "thisUpdateTime");
            return (Criteria) this;
        }

        public Criteria andThisUpdateTimeIn(List<Date> values) {
            addCriterion("this_update_time in", values, "thisUpdateTime");
            return (Criteria) this;
        }

        public Criteria andThisUpdateTimeNotIn(List<Date> values) {
            addCriterion("this_update_time not in", values, "thisUpdateTime");
            return (Criteria) this;
        }

        public Criteria andThisUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("this_update_time between", value1, value2, "thisUpdateTime");
            return (Criteria) this;
        }

        public Criteria andThisUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("this_update_time not between", value1, value2, "thisUpdateTime");
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