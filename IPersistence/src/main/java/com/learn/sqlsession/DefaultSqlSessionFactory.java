package com.learn.sqlsession;

import com.learn.pojo.Configuration;

/**
 * @Author liuxuke
 * @Title: DefaultSqlSessionFactory
 * @ProjectName IPersistence_test
 * @Description: //TODO
 * @date 2020/6/19 15:16
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
