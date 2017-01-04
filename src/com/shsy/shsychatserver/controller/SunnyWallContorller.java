package com.shsy.shsychatserver.controller;

import com.shsy.shsychatserver.bean.SunnyWallBean;
import com.shsy.shsychatserver.utils.TextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shsy on 2017/1/4.
 * 心晴墙的控制器
 */

@Controller
@RequestMapping("api/sunnywall")
public class SunnyWallContorller {


    /**
     * 查找所有的留言
     *
     * @param req
     */
    @RequestMapping(value = "findall", method = RequestMethod.GET)
    @ResponseBody
    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!TextUtil.isEmpty((CharSequence) req.getSession().getAttribute("username"))) {// 已经登录
            List<SunnyWallBean> sunnyWalls = new ArrayList<>();

            sunnyWalls.add(new SunnyWallBean("1", "a", "b", "c"));
            sunnyWalls.add(new SunnyWallBean("2", "a", "b", "c"));
            sunnyWalls.add(new SunnyWallBean("3", "a", "b", "c"));

            req.setAttribute("sunnyWalls", sunnyWalls);
            req.getRequestDispatcher("/jsp/sunnywall.jsp").forward(req, resp);
        } else {// 未登录
            resp.setHeader("Content-type", "text/html;charset=UTF-8");
            PrintWriter pw = resp.getWriter();
            pw.print("还没登录呢吧,伙计");
            pw.flush();
            pw.close();
        }
    }

    /**
     * 添加一条留言
     *
     * @param req
     */
    @RequestMapping(value = "addSunnyWall", method = RequestMethod.GET)
    @ResponseBody
    public void addSunnyWall(HttpServletRequest req) {
    }
}
