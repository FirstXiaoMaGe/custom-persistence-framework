package com.learn.config;

import com.learn.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author liuxuke
 * @Title: BoundSql
 * @ProjectName IPersistence_test
 * @Description: 用来存储转换过程中的sql语句，以及#{} 中的参数名称
 * @date 2020/6/19 16:02
 */
public class BoundSql {
    /**
     * 解析过后的sql
     */
    private String sqlText;
    private List<ParameterMapping> parameterMappings = new ArrayList<>();

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappings) {
        this.sqlText = sqlText;
        this.parameterMappings = parameterMappings;
    }
}
