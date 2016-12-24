package com.shsy.shsychatserver.db.dao.impl;

import com.shsy.shsychatserver.Config;
import com.shsy.shsychatserver.bean.UserBean;
import com.shsy.shsychatserver.db.jdbc.DbcpBean;
import com.shsy.shsychatserver.db.dao.UserDao;
import com.shsy.shsychatserver.db.jdbc.JdbcUtil;
import com.shsy.shsychatserver.utils.EncryptionUtil;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by 申尚宇 on 2016/12/23.
 * 用户操作类
 */
@Component
public class UserDaoImpl implements UserDao {

    @Override
    public int addUser(UserBean user) {
        if (selectUserFromName(user.getUsername()) != null) {
            return Config.ISADD;
        }
        DbcpBean dbcpBean = JdbcUtil.getDbcpBean();
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "insert into tb_user (username,password) values (?,?);";

        try {
            conn = dbcpBean.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, EncryptionUtil.MD5(user.getPassword()));
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
    public void removeUser(String id) {

    }

    @Override
    public int updateUser(String id, UserBean user) {
        DbcpBean dbcpBean = JdbcUtil.getDbcpBean();
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "update tb_user set username = ?,password = ? ,token = ?, isLogin = ?,isDelete = ? where id = " + id + ";";

        try {
            conn = dbcpBean.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getToken());
            ps.setString(4, user.getIsLogin());
            ps.setString(5, user.getIsDelete());
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
    public UserBean selectUserFromId(String id) {

        DbcpBean dbcpBean = JdbcUtil.getDbcpBean();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tb_user WHERE id = ?";

        UserBean user = new UserBean();

        return selectUser(id, dbcpBean, conn, ps, rs, sql, user);
    }

    @Override
    public UserBean selectUserFromName(String username) {
        DbcpBean dbcpBean = JdbcUtil.getDbcpBean();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tb_user WHERE username = ?";

        UserBean user = new UserBean();

        return selectUser(username, dbcpBean, conn, ps, rs, sql, user);
    }

    private UserBean selectUser(String nameorid, DbcpBean dbcpBean, Connection conn, PreparedStatement ps, ResultSet rs, String sql, UserBean user) {
        try {
            conn = dbcpBean.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, nameorid);
            rs = ps.executeQuery();

            if (rs.next()) {
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setToken(rs.getString("token"));
                user.setIsLogin(rs.getString("isLogin"));
                user.setIsDelete(rs.getString("isDelete"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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

    @Override
    public List<UserBean> selectAllUser() {
        return null;
    }
}
