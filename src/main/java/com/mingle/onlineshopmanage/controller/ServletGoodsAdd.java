package com.mingle.onlineshopmanage.controller;



import com.mingle.onlineshopmanage.bean.Goods;
import com.mingle.onlineshopmanage.bean.Type;
import com.mingle.onlineshopmanage.dao.GoodsDao;
import com.mingle.onlineshopmanage.dao.TypeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ServletGoodsAdd", value = "/goodsadd")
public class ServletGoodsAdd extends HttpServlet {
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
        ArrayList<Type> list1 = null;
        try {
            list1 = TypeDao.queryAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("typelist",list1);
        request.getRequestDispatcher("/pages/goods/goodsadd.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        {
            request.setCharacterEncoding("utf-8");
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
        String type  = request.getParameter("type");
        Float price  = Float.valueOf(request.getParameter("price"));
//        System.out.println(dname+" "+loc);
        Goods goods = new Goods(0,name,price,type);


        try {
            ArrayList<Type> list1 = TypeDao.queryAll();
            request.setAttribute("typelist",list1);
            boolean flag =  GoodsDao.existUser(goods); // 改商品已经有了，不能添加
            request.setAttribute("existGoods",flag);
            System.out.println(flag);
            if(flag) {
                request.getRequestDispatcher("pages/goods/goodsadd.jsp").forward(request,response);
            }else {
                GoodsDao.add(goods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("goodslist");
    }
}
