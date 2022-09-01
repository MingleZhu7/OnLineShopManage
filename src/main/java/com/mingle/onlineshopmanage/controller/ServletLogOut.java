package com.mingle.onlineshopmanage.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletLogOut", value = "/logout")
public class ServletLogOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 销毁cookie
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie: cookies){

            System.out.println(cookie.getName());
            cookie.setMaxAge(0);
            cookie.setValue("");
            cookie.setPath("/");
            response.addCookie(cookie);
        }
//        for (Cookie cookie: cookies){
//            if (cookie.getName().equals("username")){
//                System.out.println(111);
//            }
//        }
        response.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
