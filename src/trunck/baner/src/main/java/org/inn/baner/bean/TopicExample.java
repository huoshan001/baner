package org.inn.baner.bean;

import java.util.ArrayList;
import java.util.List;

public class TopicExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table topic
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table topic
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table topic
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated
     */
    public TopicExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
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
     * This method corresponds to the database table topic
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
     * This method corresponds to the database table topic
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
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
     * This class corresponds to the database table topic
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

        public Criteria andTopicidIsNull() {
            addCriterion("topicid is null");
            return (Criteria) this;
        }

        public Criteria andTopicidIsNotNull() {
            addCriterion("topicid is not null");
            return (Criteria) this;
        }

        public Criteria andTopicidEqualTo(String value) {
            addCriterion("topicid =", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidNotEqualTo(String value) {
            addCriterion("topicid <>", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidGreaterThan(String value) {
            addCriterion("topicid >", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidGreaterThanOrEqualTo(String value) {
            addCriterion("topicid >=", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidLessThan(String value) {
            addCriterion("topicid <", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidLessThanOrEqualTo(String value) {
            addCriterion("topicid <=", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidLike(String value) {
            addCriterion("topicid like", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidNotLike(String value) {
            addCriterion("topicid not like", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidIn(List<String> values) {
            addCriterion("topicid in", values, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidNotIn(List<String> values) {
            addCriterion("topicid not in", values, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidBetween(String value1, String value2) {
            addCriterion("topicid between", value1, value2, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidNotBetween(String value1, String value2) {
            addCriterion("topicid not between", value1, value2, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicdescIsNull() {
            addCriterion("topicdesc is null");
            return (Criteria) this;
        }

        public Criteria andTopicdescIsNotNull() {
            addCriterion("topicdesc is not null");
            return (Criteria) this;
        }

        public Criteria andTopicdescEqualTo(String value) {
            addCriterion("topicdesc =", value, "topicdesc");
            return (Criteria) this;
        }

        public Criteria andTopicdescNotEqualTo(String value) {
            addCriterion("topicdesc <>", value, "topicdesc");
            return (Criteria) this;
        }

        public Criteria andTopicdescGreaterThan(String value) {
            addCriterion("topicdesc >", value, "topicdesc");
            return (Criteria) this;
        }

        public Criteria andTopicdescGreaterThanOrEqualTo(String value) {
            addCriterion("topicdesc >=", value, "topicdesc");
            return (Criteria) this;
        }

        public Criteria andTopicdescLessThan(String value) {
            addCriterion("topicdesc <", value, "topicdesc");
            return (Criteria) this;
        }

        public Criteria andTopicdescLessThanOrEqualTo(String value) {
            addCriterion("topicdesc <=", value, "topicdesc");
            return (Criteria) this;
        }

        public Criteria andTopicdescLike(String value) {
            addCriterion("topicdesc like", value, "topicdesc");
            return (Criteria) this;
        }

        public Criteria andTopicdescNotLike(String value) {
            addCriterion("topicdesc not like", value, "topicdesc");
            return (Criteria) this;
        }

        public Criteria andTopicdescIn(List<String> values) {
            addCriterion("topicdesc in", values, "topicdesc");
            return (Criteria) this;
        }

        public Criteria andTopicdescNotIn(List<String> values) {
            addCriterion("topicdesc not in", values, "topicdesc");
            return (Criteria) this;
        }

        public Criteria andTopicdescBetween(String value1, String value2) {
            addCriterion("topicdesc between", value1, value2, "topicdesc");
            return (Criteria) this;
        }

        public Criteria andTopicdescNotBetween(String value1, String value2) {
            addCriterion("topicdesc not between", value1, value2, "topicdesc");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table topic
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
     * This class corresponds to the database table topic
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