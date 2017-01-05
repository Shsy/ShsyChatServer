package com.shsy.shsychatserver.db.dao.impl;

import com.shsy.shsychatserver.bean.ResultBean;
import com.shsy.shsychatserver.bean.SunnyWallBean;
import com.shsy.shsychatserver.db.dao.SunnyWallDao;
import com.shsy.shsychatserver.db.jdbc.DbcpBean;
import com.shsy.shsychatserver.db.jdbc.JdbcUtil;
import com.shsy.shsychatserver.utils.EncryptionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 申尚宇 on 2017/1/5.
 * 留言板操作
 */
public class SunnyWallDaoImpl implements SunnyWallDao {
    @Override
    public int addSunnyWall(SunnyWallBean bean) {

        DbcpBean dbcpBean = JdbcUtil.getDbcpBean();
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "insert into tb_sunnywall (username,msg,`date`) values (?,?,?);";

        try {
            conn = dbcpBean.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, bean.getUsername());
            ps.setString(2, bean.getMsg());
            ps.setString(3, bean.getDate());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            if (ps != null) try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (conn != null) try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<SunnyWallBean> findAll() {
        DbcpBean dbcpBean = JdbcUtil.getDbcpBean();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SunnyWallBean bean;

        String sql = "select * from tb_sunnywall";

        List<SunnyWallBean> beans = new ArrayList<>();

        try {
            conn = dbcpBean.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                bean = new SunnyWallBean();

                bean.setUsername(rs.getString("username"));
                bean.setMsg(rs.getString("msg"));
                bean.setDate(rs.getString("date"));

                beans.add(bean);
                bean = null;
            }
            return beans;
        } catch (SQLException e) {
            e.printStackTrace();
            beans.clear();
            return beans;
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (ps != null) try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (conn != null) try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
