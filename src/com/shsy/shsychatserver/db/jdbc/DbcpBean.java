package com.shsy.shsychatserver.db.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shsy on 2016/12/24.
 */
public class DbcpBean {

    /** 数据源，static */
    private static DataSource DS;

    /** 从数据源获得一个连接 */
    public Connection getConn() {
        Connection con = null;
        if (DS != null) {
            try {
                con = DS.getConnection();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }

            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return con;
        }
        return con;
    }

    /** 默认的构造函数 */
    public DbcpBean() {
    }

    /** 构造函数，初始化了 DS ，指定 数据库 */
    public DbcpBean(String connectURI) {
        initDS(connectURI);
    }

    /** 构造函数，初始化了 DS ，指定 所有参数 */
    public DbcpBean(String connectURI, String username, String pswd,
                    String driverClass, int initialSize, int maxActive, int maxIdle,
                    int maxWait, int minIdle) {
        initDS(connectURI, username, pswd, driverClass, initialSize, maxActive,
                maxIdle, maxWait, minIdle);
    }

    /**
     * 创建数据源，除了数据库外，都使用硬编码默认参数；
     *
     * @param connectURI
     *            数据库
     * @return
     */
    public static void initDS(String connectURI) {
        initDS(connectURI, "root", "Shsy123.", "org.mariadb.jdbc.Driver", 5, 100,
                30, 10000, 1);
    }

    /**
     * 指定所有参数连接数据源
     *
     * @param connectURI
     *            数据库
     * @param username
     *            用户名
     * @param pswd
     *            密码
     * @param driverClass
     *            数据库连接驱动名
     * @param initialSize
     *            初始连接池连接个数
     * @param maxtotal
     *            最大活动连接数
     * @param maxIdle
     *            最大连接数
     * @param maxWaitMillis
     *            获得连接的最大等待毫秒数
     * @param minIdle
     *            最小连接数
     * @return
     */
    public static void initDS(String connectURI, String username, String pswd,
                              String driverClass, int initialSize, int maxtotal, int maxIdle,
                              int maxWaitMillis , int minIdle) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driverClass);
        ds.setUsername(username);
        ds.setPassword(pswd);
        ds.setUrl(connectURI);
        ds.setInitialSize(initialSize); // 初始的连接数；
        ds.setMaxTotal(maxtotal);
        ds.setMaxIdle(maxIdle);
        ds.setMaxWaitMillis(maxWaitMillis);
        ds.setMinIdle(minIdle);
        DS = ds;
    }

    /** 获得数据源连接状态 */
    public static Map<String, Integer> getDataSourceStats() throws SQLException {
        BasicDataSource bds = (BasicDataSource) DS;
        Map<String, Integer> map = new HashMap<String, Integer>(2);
        map.put("active_number", bds.getNumActive());
        map.put("idle_number", bds.getNumIdle());
        return map;
    }

    /** 关闭数据源 */
    protected static void shutdownDataSource() throws SQLException {
        BasicDataSource bds = (BasicDataSource) DS;
        bds.close();
    }
}
