package com.learn.sqlsession;

import java.util.List;

/**
 * @Author liuxuke
 * @Title: SqlSession
 * @ProjectName IPersistence_test
 * @Description: //TODO
 * @date 2020/6/19 15:20
 */
public interface SqlSession {

    /**
     * 查询所有
     * @param <E>
     * @param statementId
     * @param params
     * @return
     */
    public <E> List<E> selectList(String statementId,Object... params) throws Exception;

    /**
     * 根据条件查询单个
     * @param statementId
     * @param params
     * @param <T>
     * @return
     */
    public <T> T selectOne(String statementId,Object... params) throws Exception;

    /**
     * 为dao接口生成代理实现类
     * @param mapperClass
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<?> mapperClass);
}
