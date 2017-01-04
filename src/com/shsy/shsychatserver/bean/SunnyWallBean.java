package com.shsy.shsychatserver.bean;

/**
 * Created by Shsy on 2017/1/4.
 */
public class SunnyWallBean {
    private String id;
    private String username;
    private String msg;
    private String date;

    public SunnyWallBean() {
    }

    public SunnyWallBean(String id, String username, String msg, String date) {
        this.id = id;
        this.username = username;
        this.msg = msg;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
