package com.mingle.onlineshopmanage.controller;

import com.mingle.onlineshopmanage.bean.Goods;
import com.mingle.onlineshopmanage.bean.Goods_shop;
import com.mingle.onlineshopmanage.bean.Shop;
import com.mingle.onlineshopmanage.bean.Type;
import com.mingle.onlineshopmanage.dao.GoodsDao;
import com.mingle.onlineshopmanage.dao.Goods_shopDao;
import com.mingle.onlineshopmanage.dao.ShopDao;
import com.mingle.onlineshopmanage.dao.TypeDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ServletShopsGoodsList", value = "/shopgoodslist")
public class ServletShopGoodsList extends HttpServlet {
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
        try{
            ArrayList<Type> list5 = TypeDao.queryAll();
            request.setAttribute("typelist",list5);
            Shop shop = ShopDao.queryShopById(id);
            ArrayList<Goods> list = GoodsDao.queryAll();
            ArrayList<Goods_shop> list1 = Goods_shopDao.selectByShopId(id);
            ArrayList<Goods> mylist =new ArrayList<>();
            // 得到货品的ids
            ArrayList<Integer> goodsId = new ArrayList<>();
            for (Goods_shop goods_shop : list1) {
                goodsId.add(goods_shop.getGid());
                System.out.println(goods_shop.getGid());
            }
            for (Goods goods : list) {
                if(goodsId.contains(goods.getId())){
                    mylist.add(goods);
                }
            }
            request.setAttribute("shop",shop);
            request.setAttribute("goodslist", mylist);
            request.getRequestDispatcher("pages/shop/shopgoodslist.jsp").forward(request,response);

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
        String type =  request.getParameter("type");
        String name =  request.getParameter("goodsName");
        int id = Integer.parseInt(request.getParameter("Sid"));
//        System.out.println(type+" "+ name);
        try {
            ArrayList<Type> list2 = TypeDao.queryAll();
            request.setAttribute("typelist",list2);
            Shop shop = ShopDao.queryShopById(id);
            ArrayList<Goods> list = GoodsDao.queryByName(name,type);
            ArrayList<Goods_shop> list1 = Goods_shopDao.selectByShopId(id);
            ArrayList<Goods> mylist =new ArrayList<>();
            // 得到货品的ids
            ArrayList<Integer> goodsId = new ArrayList<>();
            for (Goods_shop goods_shop : list1) {
                goodsId.add(goods_shop.getGid());
            }
            for (Goods goods : list) {
                if(goodsId.contains(goods.getId())){
                    mylist.add(goods);
                }
            }
            request.setAttribute("shop",shop);
            request.setAttribute("goodslist", mylist);
            request.getRequestDispatcher("pages/shop/shopgoodslist.jsp").forward(request,response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
