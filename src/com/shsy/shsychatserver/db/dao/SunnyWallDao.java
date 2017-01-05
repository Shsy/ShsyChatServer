package com.shsy.shsychatserver.db.dao;

import com.shsy.shsychatserver.bean.SunnyWallBean;

import java.util.List;

/**
 * Created by 申尚宇 on 2017/1/5.
 * 留言板接口
 */
public interface SunnyWallDao {
    int addSunnyWall(SunnyWallBean bean);

    List<SunnyWallBean> findAll();
}
