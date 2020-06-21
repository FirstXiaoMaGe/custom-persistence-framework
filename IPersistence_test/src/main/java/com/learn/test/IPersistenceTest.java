package com.learn.test;

import com.learn.dao.IUserDao;
import com.learn.io.Resources;
import com.learn.pojo.User;
import com.learn.sqlsession.SqlSession;
import com.learn.sqlsession.SqlSessionFactory;
import com.learn.sqlsession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @Author liuxuke
 * @Title: IPersistenceTest
 * @ProjectName IPersistence_test
 * @Description: //TODO
 * @date 2020/6/19 12:34
 */
public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        User user = new User();
        user.setId(1);
        user.setUsername("小鸟伏特加");
//        User user1 = sqlSession.selectOne("user.selectOne", user);
//        System.out.println(user1);

//        List<User> users = sqlSession.selectList("user.selectList");
//        for (User user2 : users) {
//            System.out.println(user2.getId());
//            System.out.println(user2.getUsername());
//        }

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        List<User> users = userDao.findAll();
    }
}
