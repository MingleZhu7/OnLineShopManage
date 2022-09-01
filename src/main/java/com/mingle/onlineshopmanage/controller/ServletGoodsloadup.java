package com.mingle.onlineshopmanage.controller;

import com.mingle.onlineshopmanage.bean.Goods_shop;
import com.mingle.onlineshopmanage.bean.Shop;
import com.mingle.onlineshopmanage.dao.GoodsDao;
import com.mingle.onlineshopmanage.dao.Goods_shopDao;
import com.mingle.onlineshopmanage.dao.ShopDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ServletGoodsloadup", value = "/goodsloadup")
public class ServletGoodsloadup extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取商铺信息
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
        try {
            int Gid = Integer.parseInt(request.getParameter("Gid"));
            ArrayList<Shop> list = ShopDao.queryAll();
            request.setAttribute("shoplist",list);
            request.setAttribute("Gid",Gid);
            request.getRequestDispatcher("pages/goods/goodsloadup.jsp").forward(request,response);
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
            }
            request.setAttribute("username",cookie2.getValue());
        } // 检测cookie
        int Gid = Integer.parseInt(request.getParameter("Gid"));
        String name = request.getParameter("shopName");
//        System.out.println(name);
        try {
            int Sid = ShopDao.getIdByName(name);
            System.out.println(Gid+" "+Sid);
            ArrayList<Goods_shop> list = Goods_shopDao.selectById(Gid,Sid);
            if(list.size()==0){
                Goods_shopDao.add(Gid,Sid);
                request.setAttribute("hasLoadUp",false);
            }else{
                request.setAttribute("hasLoadUp",true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        doGet(request, response);
    }
}
