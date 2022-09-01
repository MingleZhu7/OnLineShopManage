package com.mingle.onlineshopmanage.controller;

import com.mingle.onlineshopmanage.bean.Goods;
import com.mingle.onlineshopmanage.bean.Shop;
import com.mingle.onlineshopmanage.dao.GoodsDao;
import com.mingle.onlineshopmanage.dao.ShopDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletShopAdd", value = "/shopadd")
public class ServletShopAdd extends HttpServlet {
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
        request.getRequestDispatcher("/pages/shop/shopadd.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        String name = request.getParameter("name");
        String describe  = request.getParameter("describe");
        Float score  = Float.valueOf(request.getParameter("score"));
//        System.out.println(dname+" "+loc);
        Shop shop = new Shop(0,name,describe,score);
        try {
            boolean flag = ShopDao.existShop(shop); // 改商铺已经有了，不能添加
            request.setAttribute("existShop",flag);
            System.out.println(flag);
            if(flag) {
                request.getRequestDispatcher("pages/shop/shopadd.jsp").forward(request,response);
            }else {
                System.out.println(shop);
               ShopDao.add(shop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("shoplist");
    }

}
