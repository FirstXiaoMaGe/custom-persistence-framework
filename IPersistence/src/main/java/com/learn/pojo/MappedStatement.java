package com.learn.pojo;

/**
 * @Author liuxuke
 * @Title: MappedStatement
 * @ProjectName IPersistence_test
 * @Description: //TODO
 * @date 2020/6/19 12:42
 */
public class MappedStatement {

    /**
     * 1.id标识
     * 2.返回值类型
     * 3.参数类型
     * sql语句
     */
    private String id;
    private String resultType;
    private String paramterType;
    private String sql;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParamterType() {
        return paramterType;
    }

    public void setParamterType(String paramterType) {
        this.paramterType = paramterType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
