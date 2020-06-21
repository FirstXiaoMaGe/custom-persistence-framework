package com.learn.sqlsession;

import com.learn.pojo.Configuration;
import com.learn.pojo.MappedStatement;

import java.lang.reflect.*;
import java.util.List;

/**
 * @Author liuxuke
 * @Title: DefaultSqlSession
 * @ProjectName IPersistence_test
 * @Description: //TODO
 * @date 2020/6/19 15:21
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        //完成对jdbc的封装，完成对simpleExecutor里的query方法的调用
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        List<E> list = simpleExecutor.query(configuration, mappedStatement, params);
        return list;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<T> t = selectList(statementId, params);
        if (t.size() == 1) {
            return t.get(0);
        } else {
            throw new RuntimeException("查询结果为空或者返回结果过多");
        }
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        //使用jdk动态代理 来为dao接口生成代理对象并返回
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //底层都还是去执行jdbc代码 根据不同情况调用不同的crud方法
                //准备参数 1.statmentId :sql语句的唯一标识： namespace.id
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();
                String statmentId = className + "." + methodName;
                //准备参数2 : params:args
                //获取被调用方法的返回值类型
                Type genericReturnType = method.getGenericReturnType();
                if (genericReturnType instanceof ParameterizedType) {
                    List<Object> objects = selectList(statmentId, args);
                    return objects;
                }
                return selectOne(statmentId, args);
            }
        });
        return (T) proxyInstance;
    }
}
