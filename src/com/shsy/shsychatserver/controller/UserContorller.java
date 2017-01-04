package com.shsy.shsychatserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.shsy.shsychatserver.Config;
import com.shsy.shsychatserver.bean.ResultBean;
import com.shsy.shsychatserver.bean.UserBean;
import com.shsy.shsychatserver.db.dao.impl.UserDaoImpl;
import com.shsy.shsychatserver.db.service.UserService;
import com.shsy.shsychatserver.utils.EncryptionUtil;
import com.shsy.shsychatserver.utils.TextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * Created by 申尚宇 on 2016/12/23.
 * 关于用户的控制器
 */

@Controller
@RequestMapping("api/user")
public class UserContorller {

    /**
     * 登录
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public String login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String username = req.getParameter("username");
        final String password = req.getParameter("password");
        final String devices = req.getParameter("devices");

        if (TextUtil.isEmpty(username) || TextUtil.isEmpty(password)) {
            return JSONObject.toJSONString(new ResultBean(-1, "用户名或密码不能为空啊", ""));
        }
        UserBean user = UserService.getInstence().selectUserFromName(username);
        if (user == null) {
            return JSONObject.toJSONString(new ResultBean(-1, "用户不存在啊", ""));
        }
        if (!TextUtil.equals(user.getPassword(), EncryptionUtil.MD5(password))) {
            return JSONObject.toJSONString(new ResultBean(-1, "密码不对啊", ""));
        }
        user.setIsLogin("1");
        user.setToken(EncryptionUtil.MD5(String.valueOf(System.currentTimeMillis() + new Random().nextLong())));
        if (UserService.getInstence().updateUser(user.getId(), user) == -1) {
            return JSONObject.toJSONString(new ResultBean(-1, "登录失败请稍后再试", ""));
        }
        if (!TextUtil.equals("mobile", devices)) {// 不是移移动设备发来的请求
            req.getSession().setAttribute("username", user.getUsername());
            resp.sendRedirect("/api/sunnywall/findall");
        }
        return JSONObject.toJSONString(new ResultBean(0, "登录成功", JSONObject.toJSONString(user)));
    }

    /**
     * 注册
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "regist", method = RequestMethod.GET)
    @ResponseBody
    public String regist(HttpServletRequest req) {
        final String username = req.getParameter("username");
        final String password = req.getParameter("password");
        if (TextUtil.isEmpty(username) || TextUtil.isEmpty(password)) {
            return JSONObject.toJSONString(new ResultBean(-1, "用户名或密码不能为空啊", ""));
        }
        int result = UserService.getInstence().addUser(new UserBean(username, password));
        if (result == -1) {
            return JSONObject.toJSONString(new ResultBean(-1, "用户创建失败", ""));
        } else if (result == Config.ISADD) {
            return JSONObject.toJSONString(new ResultBean(-1, "用户已经存在啦", ""));
        } else {
            return JSONObject.toJSONString(new ResultBean(0, "用户创建成功", ""));
        }
    }

    /**
     * 退出登录
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    @ResponseBody
    public String logout(HttpServletRequest req) {
        final String uid = req.getParameter("uid");
        if (TextUtil.isEmpty(uid)) {
            return JSONObject.toJSONString(new ResultBean(0, "uid不能为空啊伙计", ""));
        }
        UserBean user = UserService.getInstence().selectUserFromId(uid);
        if (user == null) {
            return JSONObject.toJSONString(new ResultBean(0, "退出登录失败啊,没查到uid", ""));
        }
        user.setToken("");
        user.setIsLogin("0");
        int result = UserService.getInstence().updateUser(uid, user);
        if (result == -1) {
            return JSONObject.toJSONString(new ResultBean(0, "退出登录失败啊", ""));
        }
        return JSONObject.toJSONString(new ResultBean(0, "退出登录成功", ""));
    }
}
