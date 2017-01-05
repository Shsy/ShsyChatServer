package com.shsy.shsychatserver.db.service;

import com.shsy.shsychatserver.bean.SunnyWallBean;
import com.shsy.shsychatserver.db.dao.SunnyWallDao;
import com.shsy.shsychatserver.db.dao.impl.SunnyWallDaoImpl;

import java.util.List;

/**
 * Created by 申尚宇 on 2017/1/5.
 */
public class SunnyWallService {

    private static SunnyWallService mInstence = null;
    private static SunnyWallDao mSunnyWallDao;

    private SunnyWallService() {
        mSunnyWallDao = new SunnyWallDaoImpl();
    }

    public static SunnyWallService getInstence() {
        if (mInstence == null) {
            synchronized (UserService.class) {
                if (mInstence == null) {
                    mInstence = new SunnyWallService();
                }
            }
        }
        return mInstence;
    }

    public int addSunnyWall(SunnyWallBean bean) {
        return mSunnyWallDao.addSunnyWall(bean);
    }

    public List<SunnyWallBean> findAll() {
        return mSunnyWallDao.findAll();
    }

}
