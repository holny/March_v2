package com.hly.march2.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileHelperExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FileHelperExample() {
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

        public Criteria andFileIdIsNull() {
            addCriterion("file_id is null");
            return (Criteria) this;
        }

        public Criteria andFileIdIsNotNull() {
            addCriterion("file_id is not null");
            return (Criteria) this;
        }

        public Criteria andFileIdEqualTo(Long value) {
            addCriterion("file_id =", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotEqualTo(Long value) {
            addCriterion("file_id <>", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThan(Long value) {
            addCriterion("file_id >", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThanOrEqualTo(Long value) {
            addCriterion("file_id >=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThan(Long value) {
            addCriterion("file_id <", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThanOrEqualTo(Long value) {
            addCriterion("file_id <=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdIn(List<Long> values) {
            addCriterion("file_id in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotIn(List<Long> values) {
            addCriterion("file_id not in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdBetween(Long value1, Long value2) {
            addCriterion("file_id between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotBetween(Long value1, Long value2) {
            addCriterion("file_id not between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andCaseIdIsNull() {
            addCriterion("case_id is null");
            return (Criteria) this;
        }

        public Criteria andCaseIdIsNotNull() {
            addCriterion("case_id is not null");
            return (Criteria) this;
        }

        public Criteria andCaseIdEqualTo(Long value) {
            addCriterion("case_id =", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdNotEqualTo(Long value) {
            addCriterion("case_id <>", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdGreaterThan(Long value) {
            addCriterion("case_id >", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("case_id >=", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdLessThan(Long value) {
            addCriterion("case_id <", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdLessThanOrEqualTo(Long value) {
            addCriterion("case_id <=", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdIn(List<Long> values) {
            addCriterion("case_id in", values, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdNotIn(List<Long> values) {
            addCriterion("case_id not in", values, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdBetween(Long value1, Long value2) {
            addCriterion("case_id between", value1, value2, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdNotBetween(Long value1, Long value2) {
            addCriterion("case_id not between", value1, value2, "caseId");
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

        public Criteria andSaveTypeIsNull() {
            addCriterion("save_type is null");
            return (Criteria) this;
        }

        public Criteria andSaveTypeIsNotNull() {
            addCriterion("save_type is not null");
            return (Criteria) this;
        }

        public Criteria andSaveTypeEqualTo(String value) {
            addCriterion("save_type =", value, "saveType");
            return (Criteria) this;
        }

        public Criteria andSaveTypeNotEqualTo(String value) {
            addCriterion("save_type <>", value, "saveType");
            return (Criteria) this;
        }

        public Criteria andSaveTypeGreaterThan(String value) {
            addCriterion("save_type >", value, "saveType");
            return (Criteria) this;
        }

        public Criteria andSaveTypeGreaterThanOrEqualTo(String value) {
            addCriterion("save_type >=", value, "saveType");
            return (Criteria) this;
        }

        public Criteria andSaveTypeLessThan(String value) {
            addCriterion("save_type <", value, "saveType");
            return (Criteria) this;
        }

        public Criteria andSaveTypeLessThanOrEqualTo(String value) {
            addCriterion("save_type <=", value, "saveType");
            return (Criteria) this;
        }

        public Criteria andSaveTypeLike(String value) {
            addCriterion("save_type like", value, "saveType");
            return (Criteria) this;
        }

        public Criteria andSaveTypeNotLike(String value) {
            addCriterion("save_type not like", value, "saveType");
            return (Criteria) this;
        }

        public Criteria andSaveTypeIn(List<String> values) {
            addCriterion("save_type in", values, "saveType");
            return (Criteria) this;
        }

        public Criteria andSaveTypeNotIn(List<String> values) {
            addCriterion("save_type not in", values, "saveType");
            return (Criteria) this;
        }

        public Criteria andSaveTypeBetween(String value1, String value2) {
            addCriterion("save_type between", value1, value2, "saveType");
            return (Criteria) this;
        }

        public Criteria andSaveTypeNotBetween(String value1, String value2) {
            addCriterion("save_type not between", value1, value2, "saveType");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNull() {
            addCriterion("file_path is null");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNotNull() {
            addCriterion("file_path is not null");
            return (Criteria) this;
        }

        public Criteria andFilePathEqualTo(String value) {
            addCriterion("file_path =", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotEqualTo(String value) {
            addCriterion("file_path <>", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThan(String value) {
            addCriterion("file_path >", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("file_path >=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThan(String value) {
            addCriterion("file_path <", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThanOrEqualTo(String value) {
            addCriterion("file_path <=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLike(String value) {
            addCriterion("file_path like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotLike(String value) {
            addCriterion("file_path not like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathIn(List<String> values) {
            addCriterion("file_path in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotIn(List<String> values) {
            addCriterion("file_path not in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathBetween(String value1, String value2) {
            addCriterion("file_path between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotBetween(String value1, String value2) {
            addCriterion("file_path not between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("file_name is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("file_name is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("file_name =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("file_name <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("file_name >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("file_name >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("file_name <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("file_name <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("file_name like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("file_name not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("file_name in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("file_name not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("file_name between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("file_name not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileSuffixIsNull() {
            addCriterion("file_suffix is null");
            return (Criteria) this;
        }

        public Criteria andFileSuffixIsNotNull() {
            addCriterion("file_suffix is not null");
            return (Criteria) this;
        }

        public Criteria andFileSuffixEqualTo(String value) {
            addCriterion("file_suffix =", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixNotEqualTo(String value) {
            addCriterion("file_suffix <>", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixGreaterThan(String value) {
            addCriterion("file_suffix >", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixGreaterThanOrEqualTo(String value) {
            addCriterion("file_suffix >=", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixLessThan(String value) {
            addCriterion("file_suffix <", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixLessThanOrEqualTo(String value) {
            addCriterion("file_suffix <=", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixLike(String value) {
            addCriterion("file_suffix like", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixNotLike(String value) {
            addCriterion("file_suffix not like", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixIn(List<String> values) {
            addCriterion("file_suffix in", values, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixNotIn(List<String> values) {
            addCriterion("file_suffix not in", values, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixBetween(String value1, String value2) {
            addCriterion("file_suffix between", value1, value2, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixNotBetween(String value1, String value2) {
            addCriterion("file_suffix not between", value1, value2, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSizeIsNull() {
            addCriterion("file_size is null");
            return (Criteria) this;
        }

        public Criteria andFileSizeIsNotNull() {
            addCriterion("file_size is not null");
            return (Criteria) this;
        }

        public Criteria andFileSizeEqualTo(Long value) {
            addCriterion("file_size =", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotEqualTo(Long value) {
            addCriterion("file_size <>", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThan(Long value) {
            addCriterion("file_size >", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("file_size >=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThan(Long value) {
            addCriterion("file_size <", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThanOrEqualTo(Long value) {
            addCriterion("file_size <=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeIn(List<Long> values) {
            addCriterion("file_size in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotIn(List<Long> values) {
            addCriterion("file_size not in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeBetween(Long value1, Long value2) {
            addCriterion("file_size between", value1, value2, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotBetween(Long value1, Long value2) {
            addCriterion("file_size not between", value1, value2, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileUsageIsNull() {
            addCriterion("file_usage is null");
            return (Criteria) this;
        }

        public Criteria andFileUsageIsNotNull() {
            addCriterion("file_usage is not null");
            return (Criteria) this;
        }

        public Criteria andFileUsageEqualTo(String value) {
            addCriterion("file_usage =", value, "fileUsage");
            return (Criteria) this;
        }

        public Criteria andFileUsageNotEqualTo(String value) {
            addCriterion("file_usage <>", value, "fileUsage");
            return (Criteria) this;
        }

        public Criteria andFileUsageGreaterThan(String value) {
            addCriterion("file_usage >", value, "fileUsage");
            return (Criteria) this;
        }

        public Criteria andFileUsageGreaterThanOrEqualTo(String value) {
            addCriterion("file_usage >=", value, "fileUsage");
            return (Criteria) this;
        }

        public Criteria andFileUsageLessThan(String value) {
            addCriterion("file_usage <", value, "fileUsage");
            return (Criteria) this;
        }

        public Criteria andFileUsageLessThanOrEqualTo(String value) {
            addCriterion("file_usage <=", value, "fileUsage");
            return (Criteria) this;
        }

        public Criteria andFileUsageLike(String value) {
            addCriterion("file_usage like", value, "fileUsage");
            return (Criteria) this;
        }

        public Criteria andFileUsageNotLike(String value) {
            addCriterion("file_usage not like", value, "fileUsage");
            return (Criteria) this;
        }

        public Criteria andFileUsageIn(List<String> values) {
            addCriterion("file_usage in", values, "fileUsage");
            return (Criteria) this;
        }

        public Criteria andFileUsageNotIn(List<String> values) {
            addCriterion("file_usage not in", values, "fileUsage");
            return (Criteria) this;
        }

        public Criteria andFileUsageBetween(String value1, String value2) {
            addCriterion("file_usage between", value1, value2, "fileUsage");
            return (Criteria) this;
        }

        public Criteria andFileUsageNotBetween(String value1, String value2) {
            addCriterion("file_usage not between", value1, value2, "fileUsage");
            return (Criteria) this;
        }

        public Criteria andFileCreateTimeIsNull() {
            addCriterion("file_create_time is null");
            return (Criteria) this;
        }

        public Criteria andFileCreateTimeIsNotNull() {
            addCriterion("file_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andFileCreateTimeEqualTo(Date value) {
            addCriterion("file_create_time =", value, "fileCreateTime");
            return (Criteria) this;
        }

        public Criteria andFileCreateTimeNotEqualTo(Date value) {
            addCriterion("file_create_time <>", value, "fileCreateTime");
            return (Criteria) this;
        }

        public Criteria andFileCreateTimeGreaterThan(Date value) {
            addCriterion("file_create_time >", value, "fileCreateTime");
            return (Criteria) this;
        }

        public Criteria andFileCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("file_create_time >=", value, "fileCreateTime");
            return (Criteria) this;
        }

        public Criteria andFileCreateTimeLessThan(Date value) {
            addCriterion("file_create_time <", value, "fileCreateTime");
            return (Criteria) this;
        }

        public Criteria andFileCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("file_create_time <=", value, "fileCreateTime");
            return (Criteria) this;
        }

        public Criteria andFileCreateTimeIn(List<Date> values) {
            addCriterion("file_create_time in", values, "fileCreateTime");
            return (Criteria) this;
        }

        public Criteria andFileCreateTimeNotIn(List<Date> values) {
            addCriterion("file_create_time not in", values, "fileCreateTime");
            return (Criteria) this;
        }

        public Criteria andFileCreateTimeBetween(Date value1, Date value2) {
            addCriterion("file_create_time between", value1, value2, "fileCreateTime");
            return (Criteria) this;
        }

        public Criteria andFileCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("file_create_time not between", value1, value2, "fileCreateTime");
            return (Criteria) this;
        }

        public Criteria andFileStatusIsNull() {
            addCriterion("file_status is null");
            return (Criteria) this;
        }

        public Criteria andFileStatusIsNotNull() {
            addCriterion("file_status is not null");
            return (Criteria) this;
        }

        public Criteria andFileStatusEqualTo(Integer value) {
            addCriterion("file_status =", value, "fileStatus");
            return (Criteria) this;
        }

        public Criteria andFileStatusNotEqualTo(Integer value) {
            addCriterion("file_status <>", value, "fileStatus");
            return (Criteria) this;
        }

        public Criteria andFileStatusGreaterThan(Integer value) {
            addCriterion("file_status >", value, "fileStatus");
            return (Criteria) this;
        }

        public Criteria andFileStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("file_status >=", value, "fileStatus");
            return (Criteria) this;
        }

        public Criteria andFileStatusLessThan(Integer value) {
            addCriterion("file_status <", value, "fileStatus");
            return (Criteria) this;
        }

        public Criteria andFileStatusLessThanOrEqualTo(Integer value) {
            addCriterion("file_status <=", value, "fileStatus");
            return (Criteria) this;
        }

        public Criteria andFileStatusIn(List<Integer> values) {
            addCriterion("file_status in", values, "fileStatus");
            return (Criteria) this;
        }

        public Criteria andFileStatusNotIn(List<Integer> values) {
            addCriterion("file_status not in", values, "fileStatus");
            return (Criteria) this;
        }

        public Criteria andFileStatusBetween(Integer value1, Integer value2) {
            addCriterion("file_status between", value1, value2, "fileStatus");
            return (Criteria) this;
        }

        public Criteria andFileStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("file_status not between", value1, value2, "fileStatus");
            return (Criteria) this;
        }

        public Criteria andServerPathIsNull() {
            addCriterion("server_path is null");
            return (Criteria) this;
        }

        public Criteria andServerPathIsNotNull() {
            addCriterion("server_path is not null");
            return (Criteria) this;
        }

        public Criteria andServerPathEqualTo(String value) {
            addCriterion("server_path =", value, "serverPath");
            return (Criteria) this;
        }

        public Criteria andServerPathNotEqualTo(String value) {
            addCriterion("server_path <>", value, "serverPath");
            return (Criteria) this;
        }

        public Criteria andServerPathGreaterThan(String value) {
            addCriterion("server_path >", value, "serverPath");
            return (Criteria) this;
        }

        public Criteria andServerPathGreaterThanOrEqualTo(String value) {
            addCriterion("server_path >=", value, "serverPath");
            return (Criteria) this;
        }

        public Criteria andServerPathLessThan(String value) {
            addCriterion("server_path <", value, "serverPath");
            return (Criteria) this;
        }

        public Criteria andServerPathLessThanOrEqualTo(String value) {
            addCriterion("server_path <=", value, "serverPath");
            return (Criteria) this;
        }

        public Criteria andServerPathLike(String value) {
            addCriterion("server_path like", value, "serverPath");
            return (Criteria) this;
        }

        public Criteria andServerPathNotLike(String value) {
            addCriterion("server_path not like", value, "serverPath");
            return (Criteria) this;
        }

        public Criteria andServerPathIn(List<String> values) {
            addCriterion("server_path in", values, "serverPath");
            return (Criteria) this;
        }

        public Criteria andServerPathNotIn(List<String> values) {
            addCriterion("server_path not in", values, "serverPath");
            return (Criteria) this;
        }

        public Criteria andServerPathBetween(String value1, String value2) {
            addCriterion("server_path between", value1, value2, "serverPath");
            return (Criteria) this;
        }

        public Criteria andServerPathNotBetween(String value1, String value2) {
            addCriterion("server_path not between", value1, value2, "serverPath");
            return (Criteria) this;
        }

        public Criteria andServerKeyIsNull() {
            addCriterion("server_key is null");
            return (Criteria) this;
        }

        public Criteria andServerKeyIsNotNull() {
            addCriterion("server_key is not null");
            return (Criteria) this;
        }

        public Criteria andServerKeyEqualTo(String value) {
            addCriterion("server_key =", value, "serverKey");
            return (Criteria) this;
        }

        public Criteria andServerKeyNotEqualTo(String value) {
            addCriterion("server_key <>", value, "serverKey");
            return (Criteria) this;
        }

        public Criteria andServerKeyGreaterThan(String value) {
            addCriterion("server_key >", value, "serverKey");
            return (Criteria) this;
        }

        public Criteria andServerKeyGreaterThanOrEqualTo(String value) {
            addCriterion("server_key >=", value, "serverKey");
            return (Criteria) this;
        }

        public Criteria andServerKeyLessThan(String value) {
            addCriterion("server_key <", value, "serverKey");
            return (Criteria) this;
        }

        public Criteria andServerKeyLessThanOrEqualTo(String value) {
            addCriterion("server_key <=", value, "serverKey");
            return (Criteria) this;
        }

        public Criteria andServerKeyLike(String value) {
            addCriterion("server_key like", value, "serverKey");
            return (Criteria) this;
        }

        public Criteria andServerKeyNotLike(String value) {
            addCriterion("server_key not like", value, "serverKey");
            return (Criteria) this;
        }

        public Criteria andServerKeyIn(List<String> values) {
            addCriterion("server_key in", values, "serverKey");
            return (Criteria) this;
        }

        public Criteria andServerKeyNotIn(List<String> values) {
            addCriterion("server_key not in", values, "serverKey");
            return (Criteria) this;
        }

        public Criteria andServerKeyBetween(String value1, String value2) {
            addCriterion("server_key between", value1, value2, "serverKey");
            return (Criteria) this;
        }

        public Criteria andServerKeyNotBetween(String value1, String value2) {
            addCriterion("server_key not between", value1, value2, "serverKey");
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

        public Criteria andFartherNameIsNull() {
            addCriterion("farther_name is null");
            return (Criteria) this;
        }

        public Criteria andFartherNameIsNotNull() {
            addCriterion("farther_name is not null");
            return (Criteria) this;
        }

        public Criteria andFartherNameEqualTo(String value) {
            addCriterion("farther_name =", value, "fartherName");
            return (Criteria) this;
        }

        public Criteria andFartherNameNotEqualTo(String value) {
            addCriterion("farther_name <>", value, "fartherName");
            return (Criteria) this;
        }

        public Criteria andFartherNameGreaterThan(String value) {
            addCriterion("farther_name >", value, "fartherName");
            return (Criteria) this;
        }

        public Criteria andFartherNameGreaterThanOrEqualTo(String value) {
            addCriterion("farther_name >=", value, "fartherName");
            return (Criteria) this;
        }

        public Criteria andFartherNameLessThan(String value) {
            addCriterion("farther_name <", value, "fartherName");
            return (Criteria) this;
        }

        public Criteria andFartherNameLessThanOrEqualTo(String value) {
            addCriterion("farther_name <=", value, "fartherName");
            return (Criteria) this;
        }

        public Criteria andFartherNameLike(String value) {
            addCriterion("farther_name like", value, "fartherName");
            return (Criteria) this;
        }

        public Criteria andFartherNameNotLike(String value) {
            addCriterion("farther_name not like", value, "fartherName");
            return (Criteria) this;
        }

        public Criteria andFartherNameIn(List<String> values) {
            addCriterion("farther_name in", values, "fartherName");
            return (Criteria) this;
        }

        public Criteria andFartherNameNotIn(List<String> values) {
            addCriterion("farther_name not in", values, "fartherName");
            return (Criteria) this;
        }

        public Criteria andFartherNameBetween(String value1, String value2) {
            addCriterion("farther_name between", value1, value2, "fartherName");
            return (Criteria) this;
        }

        public Criteria andFartherNameNotBetween(String value1, String value2) {
            addCriterion("farther_name not between", value1, value2, "fartherName");
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