package com.mingle.onlineshopmanage.controller;

import com.mingle.onlineshopmanage.bean.Goods_shop;
import com.mingle.onlineshopmanage.dao.GoodsDao;
import com.mingle.onlineshopmanage.dao.Goods_shopDao;
import com.mingle.onlineshopmanage.dao.ShopDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletLoaddel", value = "/loaddel")
public class ServletLoaddel extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        {
            Cookie[] cookies = request.getCookies();
            Cookie cookie1 = null;
            Cookie cookie2 = null;
            if( cookies!=null && cookies.length > 0){
                for (Cookie cookie :
                        cookies) {
                    if(cookie.getName().equals("isLogin") ){
                        cookie1 = cookie;
                    }
                    if(cookie.getName().equals("username"))
                        cookie2 = cookie;
                }
            }
            if(cookie1 == null ){
                response.sendRedirect("login");
                return;
            }
            request.setAttribute("username",cookie2.getValue());
        } // 检测cookie
        String Gname = request.getParameter("Gname");
        String Sname = request.getParameter("Sname");


        try {
            int Gid = GoodsDao.getIdByName(Gname);
            int Sid = ShopDao.getIdByName(Sname);
            Goods_shopDao.del(Gid,Sid);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("hasloadgoods");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
