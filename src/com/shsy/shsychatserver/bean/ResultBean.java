package com.shsy.shsychatserver.bean;

/**
 * Created by Shsy on 2016/12/24.
 */
public class ResultBean {
    private int status;
    private String msg;
    private String result;

    public ResultBean(int status, String msg, String result) {
        this.status = status;
        this.msg = msg;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
