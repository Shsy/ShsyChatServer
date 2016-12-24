package com.shsy.shsychatserver.db.jdbc;

/**
 * Created by Shsy on 2016/12/24.
 */
public class JdbcUtil {

    public static DbcpBean getDbcpBean() {
        return new DbcpBean("jdbc:mariadb://45.78.5.42:3306/ShsyChatServer");
    }
}
