package com.hly.march2.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andUserIpIsNull() {
            addCriterion("user_ip is null");
            return (Criteria) this;
        }

        public Criteria andUserIpIsNotNull() {
            addCriterion("user_ip is not null");
            return (Criteria) this;
        }

        public Criteria andUserIpEqualTo(String value) {
            addCriterion("user_ip =", value, "userIp");
            return (Criteria) this;
        }

        public Criteria andUserIpNotEqualTo(String value) {
            addCriterion("user_ip <>", value, "userIp");
            return (Criteria) this;
        }

        public Criteria andUserIpGreaterThan(String value) {
            addCriterion("user_ip >", value, "userIp");
            return (Criteria) this;
        }

        public Criteria andUserIpGreaterThanOrEqualTo(String value) {
            addCriterion("user_ip >=", value, "userIp");
            return (Criteria) this;
        }

        public Criteria andUserIpLessThan(String value) {
            addCriterion("user_ip <", value, "userIp");
            return (Criteria) this;
        }

        public Criteria andUserIpLessThanOrEqualTo(String value) {
            addCriterion("user_ip <=", value, "userIp");
            return (Criteria) this;
        }

        public Criteria andUserIpLike(String value) {
            addCriterion("user_ip like", value, "userIp");
            return (Criteria) this;
        }

        public Criteria andUserIpNotLike(String value) {
            addCriterion("user_ip not like", value, "userIp");
            return (Criteria) this;
        }

        public Criteria andUserIpIn(List<String> values) {
            addCriterion("user_ip in", values, "userIp");
            return (Criteria) this;
        }

        public Criteria andUserIpNotIn(List<String> values) {
            addCriterion("user_ip not in", values, "userIp");
            return (Criteria) this;
        }

        public Criteria andUserIpBetween(String value1, String value2) {
            addCriterion("user_ip between", value1, value2, "userIp");
            return (Criteria) this;
        }

        public Criteria andUserIpNotBetween(String value1, String value2) {
            addCriterion("user_ip not between", value1, value2, "userIp");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserPasswordIsNull() {
            addCriterion("user_password is null");
            return (Criteria) this;
        }

        public Criteria andUserPasswordIsNotNull() {
            addCriterion("user_password is not null");
            return (Criteria) this;
        }

        public Criteria andUserPasswordEqualTo(String value) {
            addCriterion("user_password =", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotEqualTo(String value) {
            addCriterion("user_password <>", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordGreaterThan(String value) {
            addCriterion("user_password >", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("user_password >=", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordLessThan(String value) {
            addCriterion("user_password <", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordLessThanOrEqualTo(String value) {
            addCriterion("user_password <=", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordLike(String value) {
            addCriterion("user_password like", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotLike(String value) {
            addCriterion("user_password not like", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordIn(List<String> values) {
            addCriterion("user_password in", values, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotIn(List<String> values) {
            addCriterion("user_password not in", values, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordBetween(String value1, String value2) {
            addCriterion("user_password between", value1, value2, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotBetween(String value1, String value2) {
            addCriterion("user_password not between", value1, value2, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserEmailIsNull() {
            addCriterion("user_email is null");
            return (Criteria) this;
        }

        public Criteria andUserEmailIsNotNull() {
            addCriterion("user_email is not null");
            return (Criteria) this;
        }

        public Criteria andUserEmailEqualTo(String value) {
            addCriterion("user_email =", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotEqualTo(String value) {
            addCriterion("user_email <>", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailGreaterThan(String value) {
            addCriterion("user_email >", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailGreaterThanOrEqualTo(String value) {
            addCriterion("user_email >=", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLessThan(String value) {
            addCriterion("user_email <", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLessThanOrEqualTo(String value) {
            addCriterion("user_email <=", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLike(String value) {
            addCriterion("user_email like", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotLike(String value) {
            addCriterion("user_email not like", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailIn(List<String> values) {
            addCriterion("user_email in", values, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotIn(List<String> values) {
            addCriterion("user_email not in", values, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailBetween(String value1, String value2) {
            addCriterion("user_email between", value1, value2, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotBetween(String value1, String value2) {
            addCriterion("user_email not between", value1, value2, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserProfilePicIsNull() {
            addCriterion("user_profile_pic is null");
            return (Criteria) this;
        }

        public Criteria andUserProfilePicIsNotNull() {
            addCriterion("user_profile_pic is not null");
            return (Criteria) this;
        }

        public Criteria andUserProfilePicEqualTo(String value) {
            addCriterion("user_profile_pic =", value, "userProfilePic");
            return (Criteria) this;
        }

        public Criteria andUserProfilePicNotEqualTo(String value) {
            addCriterion("user_profile_pic <>", value, "userProfilePic");
            return (Criteria) this;
        }

        public Criteria andUserProfilePicGreaterThan(String value) {
            addCriterion("user_profile_pic >", value, "userProfilePic");
            return (Criteria) this;
        }

        public Criteria andUserProfilePicGreaterThanOrEqualTo(String value) {
            addCriterion("user_profile_pic >=", value, "userProfilePic");
            return (Criteria) this;
        }

        public Criteria andUserProfilePicLessThan(String value) {
            addCriterion("user_profile_pic <", value, "userProfilePic");
            return (Criteria) this;
        }

        public Criteria andUserProfilePicLessThanOrEqualTo(String value) {
            addCriterion("user_profile_pic <=", value, "userProfilePic");
            return (Criteria) this;
        }

        public Criteria andUserProfilePicLike(String value) {
            addCriterion("user_profile_pic like", value, "userProfilePic");
            return (Criteria) this;
        }

        public Criteria andUserProfilePicNotLike(String value) {
            addCriterion("user_profile_pic not like", value, "userProfilePic");
            return (Criteria) this;
        }

        public Criteria andUserProfilePicIn(List<String> values) {
            addCriterion("user_profile_pic in", values, "userProfilePic");
            return (Criteria) this;
        }

        public Criteria andUserProfilePicNotIn(List<String> values) {
            addCriterion("user_profile_pic not in", values, "userProfilePic");
            return (Criteria) this;
        }

        public Criteria andUserProfilePicBetween(String value1, String value2) {
            addCriterion("user_profile_pic between", value1, value2, "userProfilePic");
            return (Criteria) this;
        }

        public Criteria andUserProfilePicNotBetween(String value1, String value2) {
            addCriterion("user_profile_pic not between", value1, value2, "userProfilePic");
            return (Criteria) this;
        }

        public Criteria andUserCreditIsNull() {
            addCriterion("user_credit is null");
            return (Criteria) this;
        }

        public Criteria andUserCreditIsNotNull() {
            addCriterion("user_credit is not null");
            return (Criteria) this;
        }

        public Criteria andUserCreditEqualTo(Integer value) {
            addCriterion("user_credit =", value, "userCredit");
            return (Criteria) this;
        }

        public Criteria andUserCreditNotEqualTo(Integer value) {
            addCriterion("user_credit <>", value, "userCredit");
            return (Criteria) this;
        }

        public Criteria andUserCreditGreaterThan(Integer value) {
            addCriterion("user_credit >", value, "userCredit");
            return (Criteria) this;
        }

        public Criteria andUserCreditGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_credit >=", value, "userCredit");
            return (Criteria) this;
        }

        public Criteria andUserCreditLessThan(Integer value) {
            addCriterion("user_credit <", value, "userCredit");
            return (Criteria) this;
        }

        public Criteria andUserCreditLessThanOrEqualTo(Integer value) {
            addCriterion("user_credit <=", value, "userCredit");
            return (Criteria) this;
        }

        public Criteria andUserCreditIn(List<Integer> values) {
            addCriterion("user_credit in", values, "userCredit");
            return (Criteria) this;
        }

        public Criteria andUserCreditNotIn(List<Integer> values) {
            addCriterion("user_credit not in", values, "userCredit");
            return (Criteria) this;
        }

        public Criteria andUserCreditBetween(Integer value1, Integer value2) {
            addCriterion("user_credit between", value1, value2, "userCredit");
            return (Criteria) this;
        }

        public Criteria andUserCreditNotBetween(Integer value1, Integer value2) {
            addCriterion("user_credit not between", value1, value2, "userCredit");
            return (Criteria) this;
        }

        public Criteria andUserRightsIsNull() {
            addCriterion("user_rights is null");
            return (Criteria) this;
        }

        public Criteria andUserRightsIsNotNull() {
            addCriterion("user_rights is not null");
            return (Criteria) this;
        }

        public Criteria andUserRightsEqualTo(Integer value) {
            addCriterion("user_rights =", value, "userRights");
            return (Criteria) this;
        }

        public Criteria andUserRightsNotEqualTo(Integer value) {
            addCriterion("user_rights <>", value, "userRights");
            return (Criteria) this;
        }

        public Criteria andUserRightsGreaterThan(Integer value) {
            addCriterion("user_rights >", value, "userRights");
            return (Criteria) this;
        }

        public Criteria andUserRightsGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_rights >=", value, "userRights");
            return (Criteria) this;
        }

        public Criteria andUserRightsLessThan(Integer value) {
            addCriterion("user_rights <", value, "userRights");
            return (Criteria) this;
        }

        public Criteria andUserRightsLessThanOrEqualTo(Integer value) {
            addCriterion("user_rights <=", value, "userRights");
            return (Criteria) this;
        }

        public Criteria andUserRightsIn(List<Integer> values) {
            addCriterion("user_rights in", values, "userRights");
            return (Criteria) this;
        }

        public Criteria andUserRightsNotIn(List<Integer> values) {
            addCriterion("user_rights not in", values, "userRights");
            return (Criteria) this;
        }

        public Criteria andUserRightsBetween(Integer value1, Integer value2) {
            addCriterion("user_rights between", value1, value2, "userRights");
            return (Criteria) this;
        }

        public Criteria andUserRightsNotBetween(Integer value1, Integer value2) {
            addCriterion("user_rights not between", value1, value2, "userRights");
            return (Criteria) this;
        }

        public Criteria andUserRegisterTimeIsNull() {
            addCriterion("user_register_time is null");
            return (Criteria) this;
        }

        public Criteria andUserRegisterTimeIsNotNull() {
            addCriterion("user_register_time is not null");
            return (Criteria) this;
        }

        public Criteria andUserRegisterTimeEqualTo(Date value) {
            addCriterion("user_register_time =", value, "userRegisterTime");
            return (Criteria) this;
        }

        public Criteria andUserRegisterTimeNotEqualTo(Date value) {
            addCriterion("user_register_time <>", value, "userRegisterTime");
            return (Criteria) this;
        }

        public Criteria andUserRegisterTimeGreaterThan(Date value) {
            addCriterion("user_register_time >", value, "userRegisterTime");
            return (Criteria) this;
        }

        public Criteria andUserRegisterTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("user_register_time >=", value, "userRegisterTime");
            return (Criteria) this;
        }

        public Criteria andUserRegisterTimeLessThan(Date value) {
            addCriterion("user_register_time <", value, "userRegisterTime");
            return (Criteria) this;
        }

        public Criteria andUserRegisterTimeLessThanOrEqualTo(Date value) {
            addCriterion("user_register_time <=", value, "userRegisterTime");
            return (Criteria) this;
        }

        public Criteria andUserRegisterTimeIn(List<Date> values) {
            addCriterion("user_register_time in", values, "userRegisterTime");
            return (Criteria) this;
        }

        public Criteria andUserRegisterTimeNotIn(List<Date> values) {
            addCriterion("user_register_time not in", values, "userRegisterTime");
            return (Criteria) this;
        }

        public Criteria andUserRegisterTimeBetween(Date value1, Date value2) {
            addCriterion("user_register_time between", value1, value2, "userRegisterTime");
            return (Criteria) this;
        }

        public Criteria andUserRegisterTimeNotBetween(Date value1, Date value2) {
            addCriterion("user_register_time not between", value1, value2, "userRegisterTime");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayIsNull() {
            addCriterion("user_birthday is null");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayIsNotNull() {
            addCriterion("user_birthday is not null");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayEqualTo(Date value) {
            addCriterionForJDBCDate("user_birthday =", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayNotEqualTo(Date value) {
            addCriterionForJDBCDate("user_birthday <>", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayGreaterThan(Date value) {
            addCriterionForJDBCDate("user_birthday >", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("user_birthday >=", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayLessThan(Date value) {
            addCriterionForJDBCDate("user_birthday <", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("user_birthday <=", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayIn(List<Date> values) {
            addCriterionForJDBCDate("user_birthday in", values, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayNotIn(List<Date> values) {
            addCriterionForJDBCDate("user_birthday not in", values, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("user_birthday between", value1, value2, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("user_birthday not between", value1, value2, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserAgeIsNull() {
            addCriterion("user_age is null");
            return (Criteria) this;
        }

        public Criteria andUserAgeIsNotNull() {
            addCriterion("user_age is not null");
            return (Criteria) this;
        }

        public Criteria andUserAgeEqualTo(Integer value) {
            addCriterion("user_age =", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeNotEqualTo(Integer value) {
            addCriterion("user_age <>", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeGreaterThan(Integer value) {
            addCriterion("user_age >", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_age >=", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeLessThan(Integer value) {
            addCriterion("user_age <", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeLessThanOrEqualTo(Integer value) {
            addCriterion("user_age <=", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeIn(List<Integer> values) {
            addCriterion("user_age in", values, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeNotIn(List<Integer> values) {
            addCriterion("user_age not in", values, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeBetween(Integer value1, Integer value2) {
            addCriterion("user_age between", value1, value2, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeNotBetween(Integer value1, Integer value2) {
            addCriterion("user_age not between", value1, value2, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneNumberIsNull() {
            addCriterion("user_telephone_number is null");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneNumberIsNotNull() {
            addCriterion("user_telephone_number is not null");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneNumberEqualTo(String value) {
            addCriterion("user_telephone_number =", value, "userTelephoneNumber");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneNumberNotEqualTo(String value) {
            addCriterion("user_telephone_number <>", value, "userTelephoneNumber");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneNumberGreaterThan(String value) {
            addCriterion("user_telephone_number >", value, "userTelephoneNumber");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneNumberGreaterThanOrEqualTo(String value) {
            addCriterion("user_telephone_number >=", value, "userTelephoneNumber");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneNumberLessThan(String value) {
            addCriterion("user_telephone_number <", value, "userTelephoneNumber");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneNumberLessThanOrEqualTo(String value) {
            addCriterion("user_telephone_number <=", value, "userTelephoneNumber");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneNumberLike(String value) {
            addCriterion("user_telephone_number like", value, "userTelephoneNumber");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneNumberNotLike(String value) {
            addCriterion("user_telephone_number not like", value, "userTelephoneNumber");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneNumberIn(List<String> values) {
            addCriterion("user_telephone_number in", values, "userTelephoneNumber");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneNumberNotIn(List<String> values) {
            addCriterion("user_telephone_number not in", values, "userTelephoneNumber");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneNumberBetween(String value1, String value2) {
            addCriterion("user_telephone_number between", value1, value2, "userTelephoneNumber");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneNumberNotBetween(String value1, String value2) {
            addCriterion("user_telephone_number not between", value1, value2, "userTelephoneNumber");
            return (Criteria) this;
        }

        public Criteria andUserNicknameIsNull() {
            addCriterion("user_nickname is null");
            return (Criteria) this;
        }

        public Criteria andUserNicknameIsNotNull() {
            addCriterion("user_nickname is not null");
            return (Criteria) this;
        }

        public Criteria andUserNicknameEqualTo(String value) {
            addCriterion("user_nickname =", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameNotEqualTo(String value) {
            addCriterion("user_nickname <>", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameGreaterThan(String value) {
            addCriterion("user_nickname >", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("user_nickname >=", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameLessThan(String value) {
            addCriterion("user_nickname <", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameLessThanOrEqualTo(String value) {
            addCriterion("user_nickname <=", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameLike(String value) {
            addCriterion("user_nickname like", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameNotLike(String value) {
            addCriterion("user_nickname not like", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameIn(List<String> values) {
            addCriterion("user_nickname in", values, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameNotIn(List<String> values) {
            addCriterion("user_nickname not in", values, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameBetween(String value1, String value2) {
            addCriterion("user_nickname between", value1, value2, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameNotBetween(String value1, String value2) {
            addCriterion("user_nickname not between", value1, value2, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserLastLoginTimeIsNull() {
            addCriterion("user_last_login_time is null");
            return (Criteria) this;
        }

        public Criteria andUserLastLoginTimeIsNotNull() {
            addCriterion("user_last_login_time is not null");
            return (Criteria) this;
        }

        public Criteria andUserLastLoginTimeEqualTo(Date value) {
            addCriterion("user_last_login_time =", value, "userLastLoginTime");
            return (Criteria) this;
        }

        public Criteria andUserLastLoginTimeNotEqualTo(Date value) {
            addCriterion("user_last_login_time <>", value, "userLastLoginTime");
            return (Criteria) this;
        }

        public Criteria andUserLastLoginTimeGreaterThan(Date value) {
            addCriterion("user_last_login_time >", value, "userLastLoginTime");
            return (Criteria) this;
        }

        public Criteria andUserLastLoginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("user_last_login_time >=", value, "userLastLoginTime");
            return (Criteria) this;
        }

        public Criteria andUserLastLoginTimeLessThan(Date value) {
            addCriterion("user_last_login_time <", value, "userLastLoginTime");
            return (Criteria) this;
        }

        public Criteria andUserLastLoginTimeLessThanOrEqualTo(Date value) {
            addCriterion("user_last_login_time <=", value, "userLastLoginTime");
            return (Criteria) this;
        }

        public Criteria andUserLastLoginTimeIn(List<Date> values) {
            addCriterion("user_last_login_time in", values, "userLastLoginTime");
            return (Criteria) this;
        }

        public Criteria andUserLastLoginTimeNotIn(List<Date> values) {
            addCriterion("user_last_login_time not in", values, "userLastLoginTime");
            return (Criteria) this;
        }

        public Criteria andUserLastLoginTimeBetween(Date value1, Date value2) {
            addCriterion("user_last_login_time between", value1, value2, "userLastLoginTime");
            return (Criteria) this;
        }

        public Criteria andUserLastLoginTimeNotBetween(Date value1, Date value2) {
            addCriterion("user_last_login_time not between", value1, value2, "userLastLoginTime");
            return (Criteria) this;
        }

        public Criteria andAccountStatusIsNull() {
            addCriterion("account_status is null");
            return (Criteria) this;
        }

        public Criteria andAccountStatusIsNotNull() {
            addCriterion("account_status is not null");
            return (Criteria) this;
        }

        public Criteria andAccountStatusEqualTo(Integer value) {
            addCriterion("account_status =", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusNotEqualTo(Integer value) {
            addCriterion("account_status <>", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusGreaterThan(Integer value) {
            addCriterion("account_status >", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("account_status >=", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusLessThan(Integer value) {
            addCriterion("account_status <", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusLessThanOrEqualTo(Integer value) {
            addCriterion("account_status <=", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusIn(List<Integer> values) {
            addCriterion("account_status in", values, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusNotIn(List<Integer> values) {
            addCriterion("account_status not in", values, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusBetween(Integer value1, Integer value2) {
            addCriterion("account_status between", value1, value2, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("account_status not between", value1, value2, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andSaltIsNull() {
            addCriterion("salt is null");
            return (Criteria) this;
        }

        public Criteria andSaltIsNotNull() {
            addCriterion("salt is not null");
            return (Criteria) this;
        }

        public Criteria andSaltEqualTo(String value) {
            addCriterion("salt =", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotEqualTo(String value) {
            addCriterion("salt <>", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThan(String value) {
            addCriterion("salt >", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThanOrEqualTo(String value) {
            addCriterion("salt >=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThan(String value) {
            addCriterion("salt <", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThanOrEqualTo(String value) {
            addCriterion("salt <=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLike(String value) {
            addCriterion("salt like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotLike(String value) {
            addCriterion("salt not like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltIn(List<String> values) {
            addCriterion("salt in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotIn(List<String> values) {
            addCriterion("salt not in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltBetween(String value1, String value2) {
            addCriterion("salt between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotBetween(String value1, String value2) {
            addCriterion("salt not between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andUserSessionIsNull() {
            addCriterion("user_session is null");
            return (Criteria) this;
        }

        public Criteria andUserSessionIsNotNull() {
            addCriterion("user_session is not null");
            return (Criteria) this;
        }

        public Criteria andUserSessionEqualTo(String value) {
            addCriterion("user_session =", value, "userSession");
            return (Criteria) this;
        }

        public Criteria andUserSessionNotEqualTo(String value) {
            addCriterion("user_session <>", value, "userSession");
            return (Criteria) this;
        }

        public Criteria andUserSessionGreaterThan(String value) {
            addCriterion("user_session >", value, "userSession");
            return (Criteria) this;
        }

        public Criteria andUserSessionGreaterThanOrEqualTo(String value) {
            addCriterion("user_session >=", value, "userSession");
            return (Criteria) this;
        }

        public Criteria andUserSessionLessThan(String value) {
            addCriterion("user_session <", value, "userSession");
            return (Criteria) this;
        }

        public Criteria andUserSessionLessThanOrEqualTo(String value) {
            addCriterion("user_session <=", value, "userSession");
            return (Criteria) this;
        }

        public Criteria andUserSessionLike(String value) {
            addCriterion("user_session like", value, "userSession");
            return (Criteria) this;
        }

        public Criteria andUserSessionNotLike(String value) {
            addCriterion("user_session not like", value, "userSession");
            return (Criteria) this;
        }

        public Criteria andUserSessionIn(List<String> values) {
            addCriterion("user_session in", values, "userSession");
            return (Criteria) this;
        }

        public Criteria andUserSessionNotIn(List<String> values) {
            addCriterion("user_session not in", values, "userSession");
            return (Criteria) this;
        }

        public Criteria andUserSessionBetween(String value1, String value2) {
            addCriterion("user_session between", value1, value2, "userSession");
            return (Criteria) this;
        }

        public Criteria andUserSessionNotBetween(String value1, String value2) {
            addCriterion("user_session not between", value1, value2, "userSession");
            return (Criteria) this;
        }

        public Criteria andUserConfigIsNull() {
            addCriterion("user_config is null");
            return (Criteria) this;
        }

        public Criteria andUserConfigIsNotNull() {
            addCriterion("user_config is not null");
            return (Criteria) this;
        }

        public Criteria andUserConfigEqualTo(String value) {
            addCriterion("user_config =", value, "userConfig");
            return (Criteria) this;
        }

        public Criteria andUserConfigNotEqualTo(String value) {
            addCriterion("user_config <>", value, "userConfig");
            return (Criteria) this;
        }

        public Criteria andUserConfigGreaterThan(String value) {
            addCriterion("user_config >", value, "userConfig");
            return (Criteria) this;
        }

        public Criteria andUserConfigGreaterThanOrEqualTo(String value) {
            addCriterion("user_config >=", value, "userConfig");
            return (Criteria) this;
        }

        public Criteria andUserConfigLessThan(String value) {
            addCriterion("user_config <", value, "userConfig");
            return (Criteria) this;
        }

        public Criteria andUserConfigLessThanOrEqualTo(String value) {
            addCriterion("user_config <=", value, "userConfig");
            return (Criteria) this;
        }

        public Criteria andUserConfigLike(String value) {
            addCriterion("user_config like", value, "userConfig");
            return (Criteria) this;
        }

        public Criteria andUserConfigNotLike(String value) {
            addCriterion("user_config not like", value, "userConfig");
            return (Criteria) this;
        }

        public Criteria andUserConfigIn(List<String> values) {
            addCriterion("user_config in", values, "userConfig");
            return (Criteria) this;
        }

        public Criteria andUserConfigNotIn(List<String> values) {
            addCriterion("user_config not in", values, "userConfig");
            return (Criteria) this;
        }

        public Criteria andUserConfigBetween(String value1, String value2) {
            addCriterion("user_config between", value1, value2, "userConfig");
            return (Criteria) this;
        }

        public Criteria andUserConfigNotBetween(String value1, String value2) {
            addCriterion("user_config not between", value1, value2, "userConfig");
            return (Criteria) this;
        }

        public Criteria andUserSexIsNull() {
            addCriterion("user_sex is null");
            return (Criteria) this;
        }

        public Criteria andUserSexIsNotNull() {
            addCriterion("user_sex is not null");
            return (Criteria) this;
        }

        public Criteria andUserSexEqualTo(String value) {
            addCriterion("user_sex =", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexNotEqualTo(String value) {
            addCriterion("user_sex <>", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexGreaterThan(String value) {
            addCriterion("user_sex >", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexGreaterThanOrEqualTo(String value) {
            addCriterion("user_sex >=", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexLessThan(String value) {
            addCriterion("user_sex <", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexLessThanOrEqualTo(String value) {
            addCriterion("user_sex <=", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexLike(String value) {
            addCriterion("user_sex like", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexNotLike(String value) {
            addCriterion("user_sex not like", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexIn(List<String> values) {
            addCriterion("user_sex in", values, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexNotIn(List<String> values) {
            addCriterion("user_sex not in", values, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexBetween(String value1, String value2) {
            addCriterion("user_sex between", value1, value2, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexNotBetween(String value1, String value2) {
            addCriterion("user_sex not between", value1, value2, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserMottoIsNull() {
            addCriterion("user_motto is null");
            return (Criteria) this;
        }

        public Criteria andUserMottoIsNotNull() {
            addCriterion("user_motto is not null");
            return (Criteria) this;
        }

        public Criteria andUserMottoEqualTo(String value) {
            addCriterion("user_motto =", value, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoNotEqualTo(String value) {
            addCriterion("user_motto <>", value, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoGreaterThan(String value) {
            addCriterion("user_motto >", value, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoGreaterThanOrEqualTo(String value) {
            addCriterion("user_motto >=", value, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoLessThan(String value) {
            addCriterion("user_motto <", value, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoLessThanOrEqualTo(String value) {
            addCriterion("user_motto <=", value, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoLike(String value) {
            addCriterion("user_motto like", value, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoNotLike(String value) {
            addCriterion("user_motto not like", value, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoIn(List<String> values) {
            addCriterion("user_motto in", values, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoNotIn(List<String> values) {
            addCriterion("user_motto not in", values, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoBetween(String value1, String value2) {
            addCriterion("user_motto between", value1, value2, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoNotBetween(String value1, String value2) {
            addCriterion("user_motto not between", value1, value2, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserBgIsNull() {
            addCriterion("user_bg is null");
            return (Criteria) this;
        }

        public Criteria andUserBgIsNotNull() {
            addCriterion("user_bg is not null");
            return (Criteria) this;
        }

        public Criteria andUserBgEqualTo(String value) {
            addCriterion("user_bg =", value, "userBg");
            return (Criteria) this;
        }

        public Criteria andUserBgNotEqualTo(String value) {
            addCriterion("user_bg <>", value, "userBg");
            return (Criteria) this;
        }

        public Criteria andUserBgGreaterThan(String value) {
            addCriterion("user_bg >", value, "userBg");
            return (Criteria) this;
        }

        public Criteria andUserBgGreaterThanOrEqualTo(String value) {
            addCriterion("user_bg >=", value, "userBg");
            return (Criteria) this;
        }

        public Criteria andUserBgLessThan(String value) {
            addCriterion("user_bg <", value, "userBg");
            return (Criteria) this;
        }

        public Criteria andUserBgLessThanOrEqualTo(String value) {
            addCriterion("user_bg <=", value, "userBg");
            return (Criteria) this;
        }

        public Criteria andUserBgLike(String value) {
            addCriterion("user_bg like", value, "userBg");
            return (Criteria) this;
        }

        public Criteria andUserBgNotLike(String value) {
            addCriterion("user_bg not like", value, "userBg");
            return (Criteria) this;
        }

        public Criteria andUserBgIn(List<String> values) {
            addCriterion("user_bg in", values, "userBg");
            return (Criteria) this;
        }

        public Criteria andUserBgNotIn(List<String> values) {
            addCriterion("user_bg not in", values, "userBg");
            return (Criteria) this;
        }

        public Criteria andUserBgBetween(String value1, String value2) {
            addCriterion("user_bg between", value1, value2, "userBg");
            return (Criteria) this;
        }

        public Criteria andUserBgNotBetween(String value1, String value2) {
            addCriterion("user_bg not between", value1, value2, "userBg");
            return (Criteria) this;
        }

        public Criteria andGithubLinkIsNull() {
            addCriterion("github_link is null");
            return (Criteria) this;
        }

        public Criteria andGithubLinkIsNotNull() {
            addCriterion("github_link is not null");
            return (Criteria) this;
        }

        public Criteria andGithubLinkEqualTo(String value) {
            addCriterion("github_link =", value, "githubLink");
            return (Criteria) this;
        }

        public Criteria andGithubLinkNotEqualTo(String value) {
            addCriterion("github_link <>", value, "githubLink");
            return (Criteria) this;
        }

        public Criteria andGithubLinkGreaterThan(String value) {
            addCriterion("github_link >", value, "githubLink");
            return (Criteria) this;
        }

        public Criteria andGithubLinkGreaterThanOrEqualTo(String value) {
            addCriterion("github_link >=", value, "githubLink");
            return (Criteria) this;
        }

        public Criteria andGithubLinkLessThan(String value) {
            addCriterion("github_link <", value, "githubLink");
            return (Criteria) this;
        }

        public Criteria andGithubLinkLessThanOrEqualTo(String value) {
            addCriterion("github_link <=", value, "githubLink");
            return (Criteria) this;
        }

        public Criteria andGithubLinkLike(String value) {
            addCriterion("github_link like", value, "githubLink");
            return (Criteria) this;
        }

        public Criteria andGithubLinkNotLike(String value) {
            addCriterion("github_link not like", value, "githubLink");
            return (Criteria) this;
        }

        public Criteria andGithubLinkIn(List<String> values) {
            addCriterion("github_link in", values, "githubLink");
            return (Criteria) this;
        }

        public Criteria andGithubLinkNotIn(List<String> values) {
            addCriterion("github_link not in", values, "githubLink");
            return (Criteria) this;
        }

        public Criteria andGithubLinkBetween(String value1, String value2) {
            addCriterion("github_link between", value1, value2, "githubLink");
            return (Criteria) this;
        }

        public Criteria andGithubLinkNotBetween(String value1, String value2) {
            addCriterion("github_link not between", value1, value2, "githubLink");
            return (Criteria) this;
        }

        public Criteria andWeiboLinkIsNull() {
            addCriterion("weibo_link is null");
            return (Criteria) this;
        }

        public Criteria andWeiboLinkIsNotNull() {
            addCriterion("weibo_link is not null");
            return (Criteria) this;
        }

        public Criteria andWeiboLinkEqualTo(String value) {
            addCriterion("weibo_link =", value, "weiboLink");
            return (Criteria) this;
        }

        public Criteria andWeiboLinkNotEqualTo(String value) {
            addCriterion("weibo_link <>", value, "weiboLink");
            return (Criteria) this;
        }

        public Criteria andWeiboLinkGreaterThan(String value) {
            addCriterion("weibo_link >", value, "weiboLink");
            return (Criteria) this;
        }

        public Criteria andWeiboLinkGreaterThanOrEqualTo(String value) {
            addCriterion("weibo_link >=", value, "weiboLink");
            return (Criteria) this;
        }

        public Criteria andWeiboLinkLessThan(String value) {
            addCriterion("weibo_link <", value, "weiboLink");
            return (Criteria) this;
        }

        public Criteria andWeiboLinkLessThanOrEqualTo(String value) {
            addCriterion("weibo_link <=", value, "weiboLink");
            return (Criteria) this;
        }

        public Criteria andWeiboLinkLike(String value) {
            addCriterion("weibo_link like", value, "weiboLink");
            return (Criteria) this;
        }

        public Criteria andWeiboLinkNotLike(String value) {
            addCriterion("weibo_link not like", value, "weiboLink");
            return (Criteria) this;
        }

        public Criteria andWeiboLinkIn(List<String> values) {
            addCriterion("weibo_link in", values, "weiboLink");
            return (Criteria) this;
        }

        public Criteria andWeiboLinkNotIn(List<String> values) {
            addCriterion("weibo_link not in", values, "weiboLink");
            return (Criteria) this;
        }

        public Criteria andWeiboLinkBetween(String value1, String value2) {
            addCriterion("weibo_link between", value1, value2, "weiboLink");
            return (Criteria) this;
        }

        public Criteria andWeiboLinkNotBetween(String value1, String value2) {
            addCriterion("weibo_link not between", value1, value2, "weiboLink");
            return (Criteria) this;
        }

        public Criteria andQqLinkIsNull() {
            addCriterion("qq_link is null");
            return (Criteria) this;
        }

        public Criteria andQqLinkIsNotNull() {
            addCriterion("qq_link is not null");
            return (Criteria) this;
        }

        public Criteria andQqLinkEqualTo(String value) {
            addCriterion("qq_link =", value, "qqLink");
            return (Criteria) this;
        }

        public Criteria andQqLinkNotEqualTo(String value) {
            addCriterion("qq_link <>", value, "qqLink");
            return (Criteria) this;
        }

        public Criteria andQqLinkGreaterThan(String value) {
            addCriterion("qq_link >", value, "qqLink");
            return (Criteria) this;
        }

        public Criteria andQqLinkGreaterThanOrEqualTo(String value) {
            addCriterion("qq_link >=", value, "qqLink");
            return (Criteria) this;
        }

        public Criteria andQqLinkLessThan(String value) {
            addCriterion("qq_link <", value, "qqLink");
            return (Criteria) this;
        }

        public Criteria andQqLinkLessThanOrEqualTo(String value) {
            addCriterion("qq_link <=", value, "qqLink");
            return (Criteria) this;
        }

        public Criteria andQqLinkLike(String value) {
            addCriterion("qq_link like", value, "qqLink");
            return (Criteria) this;
        }

        public Criteria andQqLinkNotLike(String value) {
            addCriterion("qq_link not like", value, "qqLink");
            return (Criteria) this;
        }

        public Criteria andQqLinkIn(List<String> values) {
            addCriterion("qq_link in", values, "qqLink");
            return (Criteria) this;
        }

        public Criteria andQqLinkNotIn(List<String> values) {
            addCriterion("qq_link not in", values, "qqLink");
            return (Criteria) this;
        }

        public Criteria andQqLinkBetween(String value1, String value2) {
            addCriterion("qq_link between", value1, value2, "qqLink");
            return (Criteria) this;
        }

        public Criteria andQqLinkNotBetween(String value1, String value2) {
            addCriterion("qq_link not between", value1, value2, "qqLink");
            return (Criteria) this;
        }

        public Criteria andWechatLinkIsNull() {
            addCriterion("wechat_link is null");
            return (Criteria) this;
        }

        public Criteria andWechatLinkIsNotNull() {
            addCriterion("wechat_link is not null");
            return (Criteria) this;
        }

        public Criteria andWechatLinkEqualTo(String value) {
            addCriterion("wechat_link =", value, "wechatLink");
            return (Criteria) this;
        }

        public Criteria andWechatLinkNotEqualTo(String value) {
            addCriterion("wechat_link <>", value, "wechatLink");
            return (Criteria) this;
        }

        public Criteria andWechatLinkGreaterThan(String value) {
            addCriterion("wechat_link >", value, "wechatLink");
            return (Criteria) this;
        }

        public Criteria andWechatLinkGreaterThanOrEqualTo(String value) {
            addCriterion("wechat_link >=", value, "wechatLink");
            return (Criteria) this;
        }

        public Criteria andWechatLinkLessThan(String value) {
            addCriterion("wechat_link <", value, "wechatLink");
            return (Criteria) this;
        }

        public Criteria andWechatLinkLessThanOrEqualTo(String value) {
            addCriterion("wechat_link <=", value, "wechatLink");
            return (Criteria) this;
        }

        public Criteria andWechatLinkLike(String value) {
            addCriterion("wechat_link like", value, "wechatLink");
            return (Criteria) this;
        }

        public Criteria andWechatLinkNotLike(String value) {
            addCriterion("wechat_link not like", value, "wechatLink");
            return (Criteria) this;
        }

        public Criteria andWechatLinkIn(List<String> values) {
            addCriterion("wechat_link in", values, "wechatLink");
            return (Criteria) this;
        }

        public Criteria andWechatLinkNotIn(List<String> values) {
            addCriterion("wechat_link not in", values, "wechatLink");
            return (Criteria) this;
        }

        public Criteria andWechatLinkBetween(String value1, String value2) {
            addCriterion("wechat_link between", value1, value2, "wechatLink");
            return (Criteria) this;
        }

        public Criteria andWechatLinkNotBetween(String value1, String value2) {
            addCriterion("wechat_link not between", value1, value2, "wechatLink");
            return (Criteria) this;
        }

        public Criteria andEmailLinkIsNull() {
            addCriterion("email_link is null");
            return (Criteria) this;
        }

        public Criteria andEmailLinkIsNotNull() {
            addCriterion("email_link is not null");
            return (Criteria) this;
        }

        public Criteria andEmailLinkEqualTo(String value) {
            addCriterion("email_link =", value, "emailLink");
            return (Criteria) this;
        }

        public Criteria andEmailLinkNotEqualTo(String value) {
            addCriterion("email_link <>", value, "emailLink");
            return (Criteria) this;
        }

        public Criteria andEmailLinkGreaterThan(String value) {
            addCriterion("email_link >", value, "emailLink");
            return (Criteria) this;
        }

        public Criteria andEmailLinkGreaterThanOrEqualTo(String value) {
            addCriterion("email_link >=", value, "emailLink");
            return (Criteria) this;
        }

        public Criteria andEmailLinkLessThan(String value) {
            addCriterion("email_link <", value, "emailLink");
            return (Criteria) this;
        }

        public Criteria andEmailLinkLessThanOrEqualTo(String value) {
            addCriterion("email_link <=", value, "emailLink");
            return (Criteria) this;
        }

        public Criteria andEmailLinkLike(String value) {
            addCriterion("email_link like", value, "emailLink");
            return (Criteria) this;
        }

        public Criteria andEmailLinkNotLike(String value) {
            addCriterion("email_link not like", value, "emailLink");
            return (Criteria) this;
        }

        public Criteria andEmailLinkIn(List<String> values) {
            addCriterion("email_link in", values, "emailLink");
            return (Criteria) this;
        }

        public Criteria andEmailLinkNotIn(List<String> values) {
            addCriterion("email_link not in", values, "emailLink");
            return (Criteria) this;
        }

        public Criteria andEmailLinkBetween(String value1, String value2) {
            addCriterion("email_link between", value1, value2, "emailLink");
            return (Criteria) this;
        }

        public Criteria andEmailLinkNotBetween(String value1, String value2) {
            addCriterion("email_link not between", value1, value2, "emailLink");
            return (Criteria) this;
        }

        public Criteria andCsdnLinkIsNull() {
            addCriterion("csdn_link is null");
            return (Criteria) this;
        }

        public Criteria andCsdnLinkIsNotNull() {
            addCriterion("csdn_link is not null");
            return (Criteria) this;
        }

        public Criteria andCsdnLinkEqualTo(String value) {
            addCriterion("csdn_link =", value, "csdnLink");
            return (Criteria) this;
        }

        public Criteria andCsdnLinkNotEqualTo(String value) {
            addCriterion("csdn_link <>", value, "csdnLink");
            return (Criteria) this;
        }

        public Criteria andCsdnLinkGreaterThan(String value) {
            addCriterion("csdn_link >", value, "csdnLink");
            return (Criteria) this;
        }

        public Criteria andCsdnLinkGreaterThanOrEqualTo(String value) {
            addCriterion("csdn_link >=", value, "csdnLink");
            return (Criteria) this;
        }

        public Criteria andCsdnLinkLessThan(String value) {
            addCriterion("csdn_link <", value, "csdnLink");
            return (Criteria) this;
        }

        public Criteria andCsdnLinkLessThanOrEqualTo(String value) {
            addCriterion("csdn_link <=", value, "csdnLink");
            return (Criteria) this;
        }

        public Criteria andCsdnLinkLike(String value) {
            addCriterion("csdn_link like", value, "csdnLink");
            return (Criteria) this;
        }

        public Criteria andCsdnLinkNotLike(String value) {
            addCriterion("csdn_link not like", value, "csdnLink");
            return (Criteria) this;
        }

        public Criteria andCsdnLinkIn(List<String> values) {
            addCriterion("csdn_link in", values, "csdnLink");
            return (Criteria) this;
        }

        public Criteria andCsdnLinkNotIn(List<String> values) {
            addCriterion("csdn_link not in", values, "csdnLink");
            return (Criteria) this;
        }

        public Criteria andCsdnLinkBetween(String value1, String value2) {
            addCriterion("csdn_link between", value1, value2, "csdnLink");
            return (Criteria) this;
        }

        public Criteria andCsdnLinkNotBetween(String value1, String value2) {
            addCriterion("csdn_link not between", value1, value2, "csdnLink");
            return (Criteria) this;
        }

        public Criteria andOtherLinkIsNull() {
            addCriterion("other_link is null");
            return (Criteria) this;
        }

        public Criteria andOtherLinkIsNotNull() {
            addCriterion("other_link is not null");
            return (Criteria) this;
        }

        public Criteria andOtherLinkEqualTo(String value) {
            addCriterion("other_link =", value, "otherLink");
            return (Criteria) this;
        }

        public Criteria andOtherLinkNotEqualTo(String value) {
            addCriterion("other_link <>", value, "otherLink");
            return (Criteria) this;
        }

        public Criteria andOtherLinkGreaterThan(String value) {
            addCriterion("other_link >", value, "otherLink");
            return (Criteria) this;
        }

        public Criteria andOtherLinkGreaterThanOrEqualTo(String value) {
            addCriterion("other_link >=", value, "otherLink");
            return (Criteria) this;
        }

        public Criteria andOtherLinkLessThan(String value) {
            addCriterion("other_link <", value, "otherLink");
            return (Criteria) this;
        }

        public Criteria andOtherLinkLessThanOrEqualTo(String value) {
            addCriterion("other_link <=", value, "otherLink");
            return (Criteria) this;
        }

        public Criteria andOtherLinkLike(String value) {
            addCriterion("other_link like", value, "otherLink");
            return (Criteria) this;
        }

        public Criteria andOtherLinkNotLike(String value) {
            addCriterion("other_link not like", value, "otherLink");
            return (Criteria) this;
        }

        public Criteria andOtherLinkIn(List<String> values) {
            addCriterion("other_link in", values, "otherLink");
            return (Criteria) this;
        }

        public Criteria andOtherLinkNotIn(List<String> values) {
            addCriterion("other_link not in", values, "otherLink");
            return (Criteria) this;
        }

        public Criteria andOtherLinkBetween(String value1, String value2) {
            addCriterion("other_link between", value1, value2, "otherLink");
            return (Criteria) this;
        }

        public Criteria andOtherLinkNotBetween(String value1, String value2) {
            addCriterion("other_link not between", value1, value2, "otherLink");
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