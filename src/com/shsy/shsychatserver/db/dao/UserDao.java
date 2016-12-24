package com.shsy.shsychatserver.db.dao;

import com.shsy.shsychatserver.bean.UserBean;

import java.util.List;

/**
 * Created by 申尚宇 on 2016/12/23.
 * User增删改查的interface
 */
public interface UserDao {
    /**
     * 增加用户
     *
     * @param user 用户bean
     */
    int addUser(UserBean user);

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    void removeUser(String id);

    /**
     * 更改用户
     *
     * @param id   用户ID
     * @param user 用户bean
     */
    int updateUser(String id, UserBean user);

    /**
     * 查找用户
     *
     * @param id 用户id
     * @return 用户bean
     */
    UserBean selectUserFromId(String id);

    /**
     * 查找用户
     *
     * @param username 用户name
     * @return 用户bean
     */
    UserBean selectUserFromName(String username);

    /**
     * 查询所有
     *
     * @return 所有用户的list集合
     */
    List<UserBean> selectAllUser();
}
