package com.learn.sqlsession;

/**
 * @Author liuxuke
 * @Title: SqlSessionFactory
 * @ProjectName IPersistence_test
 * @Description: //TODO
 * @date 2020/6/19 12:56
 */
public interface SqlSessionFactory {

    public SqlSession openSession();
}
