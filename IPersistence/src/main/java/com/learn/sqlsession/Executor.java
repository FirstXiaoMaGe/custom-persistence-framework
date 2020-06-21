package com.learn.sqlsession;

import com.learn.pojo.Configuration;
import com.learn.pojo.MappedStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author liuxuke
 * @Title: Executor
 * @ProjectName IPersistence_test
 * @Description: 封装jdbc
 * @date 2020/6/19 15:42
 */
public interface Executor {

    /**
     * 查询接口
     * 对jdbc进行封装
     * @param configuration
     * @param mappedStatement
     * @param params
     * @param <E>
     * @return
     */
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement,Object... params) throws SQLException, Exception;
}
