package com.shsy.shsychatserver.controller;

import com.shsy.shsychatserver.bean.SunnyWallBean;
import com.shsy.shsychatserver.db.service.SunnyWallService;
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
        final String username = (String) req.getSession().getAttribute("username");
        if (!TextUtil.isEmpty(username)) {// 已经登录
            List<SunnyWallBean> sunnyWalls = SunnyWallService.getInstence().findAll();
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
    public void addSunnyWall(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String username = (String) req.getSession().getAttribute("username");
        final String msg = req.getParameter("msg");
        final String date = String.valueOf(System.currentTimeMillis());

        int result = SunnyWallService.getInstence().addSunnyWall(new SunnyWallBean("", username, msg, date));

        if (result != -1) {
            resp.setHeader("refresh", "0;URL=/api/sunnywall/findall");
        } else {
            resp.setHeader("Content-type", "text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.write("添加失败");
            writer.flush();
            writer.close();
        }
    }
}
