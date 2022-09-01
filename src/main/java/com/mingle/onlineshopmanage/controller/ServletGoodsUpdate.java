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

@WebServlet(name = "ServletGoodsUpdate", value = "/goodsupdate")
public class ServletGoodsUpdate extends HttpServlet {
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
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            ArrayList<Type> list1 = TypeDao.queryAll();
            request.setAttribute("typelist",list1);
            Goods goods = GoodsDao.querGoodsById(id);

            request.setAttribute("goods", goods);

            request.getRequestDispatcher("pages/goods/goodsupdate.jsp").forward(request,response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String type  = request.getParameter("type");
        Float price  = Float.valueOf(request.getParameter("price"));

        Goods goods = new Goods(id,name,price,type);

        System.out.println(goods);
        try {
            ArrayList<Type> list1 = TypeDao.queryAll();
            request.setAttribute("typelist",list1);
            boolean flag= GoodsDao.existUser(goods);
            if(flag){
                request.setAttribute("existGoods",flag);

                request.getRequestDispatcher("goodslist").forward(request,response);

            }else {
                GoodsDao.update(goods);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("goodslist");
    }
}
