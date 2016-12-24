package com.shsy.shsychatserver.db.service;

import com.shsy.shsychatserver.bean.UserBean;
import com.shsy.shsychatserver.db.dao.UserDao;
import com.shsy.shsychatserver.db.dao.impl.UserDaoImpl;

/**
 * Created by Shsy on 2016/12/24.
 * 操作用户数据库的service
 */
public class UserService {

    private static UserService mInstence = null;
    private static UserDao mUserDao;

    private UserService() {
        mUserDao = new UserDaoImpl();
    }

    public static UserService getInstence() {
        if (mInstence == null) {
            synchronized (UserService.class) {
                if (mInstence == null) {
                    mInstence = new UserService();
                }
            }
        }
        return mInstence;
    }

    public int addUser(UserBean userBean) {
        return mUserDao.addUser(userBean);
    }

    public int updateUser(String id, UserBean userBean) {
        return mUserDao.updateUser(id, userBean);
    }

    public UserBean selectUserFromName(String username) {
        return mUserDao.selectUserFromName(username);
    }
}
