package com.learn.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author liuxuke
 * @Title: Configuration
 * @ProjectName IPersistence_test
 * @Description: //TODO
 * @date 2020/6/19 12:46
 */
public class Configuration {

    private DataSource dataSource;
    /**
     * key: statementId sql唯一标识 由xml文件中的namespace和id组成
     */
    Map<String,MappedStatement> mappedStatementMap = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }
}
