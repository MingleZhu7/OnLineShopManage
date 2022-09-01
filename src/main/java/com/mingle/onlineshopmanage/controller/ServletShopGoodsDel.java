package com.mingle.onlineshopmanage.controller;

import com.mingle.onlineshopmanage.bean.Goods_shop;
import com.mingle.onlineshopmanage.dao.Goods_shopDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ServletShopGoodsDel", value = "/shopgoodsdel")
public class ServletShopGoodsDel extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        int Sid = Integer.parseInt(request.getParameter("Sid"));
        int Gid = Integer.parseInt(request.getParameter("Gid"));
        try {
            Goods_shopDao.del(Gid,Sid);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("shopgoodslist?id="+Sid);
//        System.out.println(Sid+" "+Gid);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
