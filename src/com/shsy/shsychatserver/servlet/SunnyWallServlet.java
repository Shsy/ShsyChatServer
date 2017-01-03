package com.shsy.shsychatserver.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shsy on 2017/1/3.
 */
public class SunnyWallServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> strs = new ArrayList<>();
        req.setAttribute("strs", "asdf");

        req.getRequestDispatcher("/jsp/sunnywall.jsp").forward(req, resp);
    }
}
