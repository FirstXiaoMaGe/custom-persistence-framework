package com.learn.dao;

import com.learn.io.Resources;
import com.learn.pojo.User;
import com.learn.sqlsession.SqlSession;
import com.learn.sqlsession.SqlSessionFactory;
import com.learn.sqlsession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author liuxuke
 * @Title: UserDaoImpl
 * @ProjectName IPersistence_test
 * @Description: //TODO
 * @date 2020/6/19 17:30
 */
public class UserDaoImpl implements IUserDao {


    @Override
    public List<User> findAll() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("user.selectList");
        return users;
    }

    @Override
    public User findByCondition(User user) throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user1 = sqlSession.selectOne("user.selectOne", user);
        return user1;
    }
}
