package com.example.springcloud.provider.mybatis;

/**
 * Created by shiwen on 2017/9/12.
 */
public class MybatisSqlLogEntity {
    /**
     * 日志ID
     */
    private String sqlId;

    /**
     * sql语句
     */
    private String sql;


    /**
     * 参数
     */
    private String parameters;


    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
}
