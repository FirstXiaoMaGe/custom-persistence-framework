package com.learn.sqlsession;

import com.learn.config.XMLConfigBuilder;
import com.learn.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @Author liuxuke
 * @Title: SqlSessionFactoryBuilder
 * @ProjectName IPersistence_test
 * @Description: //TODO
 * @date 2020/6/19 12:53
 */
public class SqlSessionFactoryBuilder {

    /**
     * 1.使用dom4j解析配置文件，将解析出来的内容封装到configuration中
     * 2.创建sqlSessionFactory对象
     *
     * @param inputStream
     * @return
     */
    public SqlSessionFactory build(InputStream inputStream) throws DocumentException, PropertyVetoException {
        //1.dom4j解析进行封装
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(inputStream);

        //2.创建sqlSessionFactory对象: 工厂类：生产sqlSession：会话对象  sqlSession封装 crud方法

        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return defaultSqlSessionFactory;
    }
}
