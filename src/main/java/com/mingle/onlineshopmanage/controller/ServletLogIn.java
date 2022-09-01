package com.mingle.onlineshopmanage.controller;

import com.mingle.onlineshopmanage.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletLogIn", value = "/login")
public class ServletLogIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            if(UserDao.isOk(username,password)) {
                // 生命周期 默认 关闭浏览器
                Cookie cookie1 = new Cookie("isLogin", "yes");
                Cookie cookie2 = new Cookie("username", username);
                response.addCookie(cookie1);
                response.addCookie(cookie2);
                System.out.println("ok");
                response.sendRedirect("main");
            }else {
                request.setAttribute("isOk",false);
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
