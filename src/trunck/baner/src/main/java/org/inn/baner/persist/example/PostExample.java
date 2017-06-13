package org.inn.baner.persist.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table post
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table post
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table post
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated
     */
    public PostExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table post
     *
     * @mbg.generated
     */
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

        public Criteria andPostidIsNull() {
            addCriterion("postid is null");
            return (Criteria) this;
        }

        public Criteria andPostidIsNotNull() {
            addCriterion("postid is not null");
            return (Criteria) this;
        }

        public Criteria andPostidEqualTo(Integer value) {
            addCriterion("postid =", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidNotEqualTo(Integer value) {
            addCriterion("postid <>", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidGreaterThan(Integer value) {
            addCriterion("postid >", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidGreaterThanOrEqualTo(Integer value) {
            addCriterion("postid >=", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidLessThan(Integer value) {
            addCriterion("postid <", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidLessThanOrEqualTo(Integer value) {
            addCriterion("postid <=", value, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidIn(List<Integer> values) {
            addCriterion("postid in", values, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidNotIn(List<Integer> values) {
            addCriterion("postid not in", values, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidBetween(Integer value1, Integer value2) {
            addCriterion("postid between", value1, value2, "postid");
            return (Criteria) this;
        }

        public Criteria andPostidNotBetween(Integer value1, Integer value2) {
            addCriterion("postid not between", value1, value2, "postid");
            return (Criteria) this;
        }

        public Criteria andTopicidIsNull() {
            addCriterion("topicid is null");
            return (Criteria) this;
        }

        public Criteria andTopicidIsNotNull() {
            addCriterion("topicid is not null");
            return (Criteria) this;
        }

        public Criteria andTopicidEqualTo(Integer value) {
            addCriterion("topicid =", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidNotEqualTo(Integer value) {
            addCriterion("topicid <>", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidGreaterThan(Integer value) {
            addCriterion("topicid >", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidGreaterThanOrEqualTo(Integer value) {
            addCriterion("topicid >=", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidLessThan(Integer value) {
            addCriterion("topicid <", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidLessThanOrEqualTo(Integer value) {
            addCriterion("topicid <=", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidIn(List<Integer> values) {
            addCriterion("topicid in", values, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidNotIn(List<Integer> values) {
            addCriterion("topicid not in", values, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidBetween(Integer value1, Integer value2) {
            addCriterion("topicid between", value1, value2, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidNotBetween(Integer value1, Integer value2) {
            addCriterion("topicid not between", value1, value2, "topicid");
            return (Criteria) this;
        }

        public Criteria andCreatormobilenoIsNull() {
            addCriterion("creatormobileno is null");
            return (Criteria) this;
        }

        public Criteria andCreatormobilenoIsNotNull() {
            addCriterion("creatormobileno is not null");
            return (Criteria) this;
        }

        public Criteria andCreatormobilenoEqualTo(String value) {
            addCriterion("creatormobileno =", value, "creatormobileno");
            return (Criteria) this;
        }

        public Criteria andCreatormobilenoNotEqualTo(String value) {
            addCriterion("creatormobileno <>", value, "creatormobileno");
            return (Criteria) this;
        }

        public Criteria andCreatormobilenoGreaterThan(String value) {
            addCriterion("creatormobileno >", value, "creatormobileno");
            return (Criteria) this;
        }

        public Criteria andCreatormobilenoGreaterThanOrEqualTo(String value) {
            addCriterion("creatormobileno >=", value, "creatormobileno");
            return (Criteria) this;
        }

        public Criteria andCreatormobilenoLessThan(String value) {
            addCriterion("creatormobileno <", value, "creatormobileno");
            return (Criteria) this;
        }

        public Criteria andCreatormobilenoLessThanOrEqualTo(String value) {
            addCriterion("creatormobileno <=", value, "creatormobileno");
            return (Criteria) this;
        }

        public Criteria andCreatormobilenoLike(String value) {
            addCriterion("creatormobileno like", value, "creatormobileno");
            return (Criteria) this;
        }

        public Criteria andCreatormobilenoNotLike(String value) {
            addCriterion("creatormobileno not like", value, "creatormobileno");
            return (Criteria) this;
        }

        public Criteria andCreatormobilenoIn(List<String> values) {
            addCriterion("creatormobileno in", values, "creatormobileno");
            return (Criteria) this;
        }

        public Criteria andCreatormobilenoNotIn(List<String> values) {
            addCriterion("creatormobileno not in", values, "creatormobileno");
            return (Criteria) this;
        }

        public Criteria andCreatormobilenoBetween(String value1, String value2) {
            addCriterion("creatormobileno between", value1, value2, "creatormobileno");
            return (Criteria) this;
        }

        public Criteria andCreatormobilenoNotBetween(String value1, String value2) {
            addCriterion("creatormobileno not between", value1, value2, "creatormobileno");
            return (Criteria) this;
        }

        public Criteria andPostnameIsNull() {
            addCriterion("postname is null");
            return (Criteria) this;
        }

        public Criteria andPostnameIsNotNull() {
            addCriterion("postname is not null");
            return (Criteria) this;
        }

        public Criteria andPostnameEqualTo(String value) {
            addCriterion("postname =", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameNotEqualTo(String value) {
            addCriterion("postname <>", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameGreaterThan(String value) {
            addCriterion("postname >", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameGreaterThanOrEqualTo(String value) {
            addCriterion("postname >=", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameLessThan(String value) {
            addCriterion("postname <", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameLessThanOrEqualTo(String value) {
            addCriterion("postname <=", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameLike(String value) {
            addCriterion("postname like", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameNotLike(String value) {
            addCriterion("postname not like", value, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameIn(List<String> values) {
            addCriterion("postname in", values, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameNotIn(List<String> values) {
            addCriterion("postname not in", values, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameBetween(String value1, String value2) {
            addCriterion("postname between", value1, value2, "postname");
            return (Criteria) this;
        }

        public Criteria andPostnameNotBetween(String value1, String value2) {
            addCriterion("postname not between", value1, value2, "postname");
            return (Criteria) this;
        }

        public Criteria andPostdescIsNull() {
            addCriterion("postdesc is null");
            return (Criteria) this;
        }

        public Criteria andPostdescIsNotNull() {
            addCriterion("postdesc is not null");
            return (Criteria) this;
        }

        public Criteria andPostdescEqualTo(String value) {
            addCriterion("postdesc =", value, "postdesc");
            return (Criteria) this;
        }

        public Criteria andPostdescNotEqualTo(String value) {
            addCriterion("postdesc <>", value, "postdesc");
            return (Criteria) this;
        }

        public Criteria andPostdescGreaterThan(String value) {
            addCriterion("postdesc >", value, "postdesc");
            return (Criteria) this;
        }

        public Criteria andPostdescGreaterThanOrEqualTo(String value) {
            addCriterion("postdesc >=", value, "postdesc");
            return (Criteria) this;
        }

        public Criteria andPostdescLessThan(String value) {
            addCriterion("postdesc <", value, "postdesc");
            return (Criteria) this;
        }

        public Criteria andPostdescLessThanOrEqualTo(String value) {
            addCriterion("postdesc <=", value, "postdesc");
            return (Criteria) this;
        }

        public Criteria andPostdescLike(String value) {
            addCriterion("postdesc like", value, "postdesc");
            return (Criteria) this;
        }

        public Criteria andPostdescNotLike(String value) {
            addCriterion("postdesc not like", value, "postdesc");
            return (Criteria) this;
        }

        public Criteria andPostdescIn(List<String> values) {
            addCriterion("postdesc in", values, "postdesc");
            return (Criteria) this;
        }

        public Criteria andPostdescNotIn(List<String> values) {
            addCriterion("postdesc not in", values, "postdesc");
            return (Criteria) this;
        }

        public Criteria andPostdescBetween(String value1, String value2) {
            addCriterion("postdesc between", value1, value2, "postdesc");
            return (Criteria) this;
        }

        public Criteria andPostdescNotBetween(String value1, String value2) {
            addCriterion("postdesc not between", value1, value2, "postdesc");
            return (Criteria) this;
        }

        public Criteria andZantimesIsNull() {
            addCriterion("zantimes is null");
            return (Criteria) this;
        }

        public Criteria andZantimesIsNotNull() {
            addCriterion("zantimes is not null");
            return (Criteria) this;
        }

        public Criteria andZantimesEqualTo(Integer value) {
            addCriterion("zantimes =", value, "zantimes");
            return (Criteria) this;
        }

        public Criteria andZantimesNotEqualTo(Integer value) {
            addCriterion("zantimes <>", value, "zantimes");
            return (Criteria) this;
        }

        public Criteria andZantimesGreaterThan(Integer value) {
            addCriterion("zantimes >", value, "zantimes");
            return (Criteria) this;
        }

        public Criteria andZantimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("zantimes >=", value, "zantimes");
            return (Criteria) this;
        }

        public Criteria andZantimesLessThan(Integer value) {
            addCriterion("zantimes <", value, "zantimes");
            return (Criteria) this;
        }

        public Criteria andZantimesLessThanOrEqualTo(Integer value) {
            addCriterion("zantimes <=", value, "zantimes");
            return (Criteria) this;
        }

        public Criteria andZantimesIn(List<Integer> values) {
            addCriterion("zantimes in", values, "zantimes");
            return (Criteria) this;
        }

        public Criteria andZantimesNotIn(List<Integer> values) {
            addCriterion("zantimes not in", values, "zantimes");
            return (Criteria) this;
        }

        public Criteria andZantimesBetween(Integer value1, Integer value2) {
            addCriterion("zantimes between", value1, value2, "zantimes");
            return (Criteria) this;
        }

        public Criteria andZantimesNotBetween(Integer value1, Integer value2) {
            addCriterion("zantimes not between", value1, value2, "zantimes");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table post
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table post
     *
     * @mbg.generated
     */
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