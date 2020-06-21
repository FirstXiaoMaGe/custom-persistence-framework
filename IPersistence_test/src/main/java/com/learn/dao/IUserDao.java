package com.learn.dao;

import com.learn.pojo.User;

import java.util.List;

/**
 * @Author liuxuke
 * @Title: IUserDao
 * @ProjectName IPersistence_test
 * @Description: //TODO
 * @date 2020/6/19 17:28
 */
public interface IUserDao {


    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    public List<User> findAll() throws Exception;

    /**
     * 根据条件查询
     * @param user
     * @return
     * @throws Exception
     */
    public User findByCondition(User user) throws Exception;
}
