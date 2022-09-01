package com.mingle.onlineshopmanage.controller;

import com.mingle.onlineshopmanage.bean.Goods;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ServletHasLoadGoods", value = "/hasloadgoods")
public class ServletHasLoadGoods extends HttpServlet {
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
        try {
            ArrayList<Shop> list1 = ShopDao.queryAll();
            request.setAttribute("shoplist",list1);;
            ArrayList<Goods_shop> list2 = Goods_shopDao.selectAll();
            ArrayList<Goods> list3 = GoodsDao.queryInId(list2);
            List<Map<String, Object> > list = new ArrayList<>();
            for (Goods_shop goods_shop : list2 ){
                Map<String, Object> map = new HashMap<>();
                Shop shop = ShopDao.queryShopById(goods_shop.getSid());
                for(Goods goods : list3){
                    if(goods.getId() == goods_shop.getGid()){
                        map.put("shop",shop.getName());
                        map.put("goods",goods.getName());
                        map.put("price",goods.getPrice());
                        list.add(map);
                    }
                }
            }
            request.setAttribute("maplist",list);
            request.getRequestDispatcher("pages/goods/hasloadgoods.jsp").forward(request,response);

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
        String shopname = request.getParameter("shop");
        if(shopname.equals("")){
            doGet(request, response);
        }else {
            try {

//                ArrayList<Shop> shops = ShopDao.queryByName(shopname);
                int shopid = ShopDao.getIdByName(shopname);
                ArrayList<Shop> list1 = ShopDao.queryAll();
                request.setAttribute("shoplist",list1);;
                ArrayList<Goods_shop> list2 = Goods_shopDao.selectAll();
                ArrayList<Goods> list3 = GoodsDao.queryInId(list2);
                List<Map<String, Object> > list = new ArrayList<>();
                for (Goods_shop goods_shop : list2 ){
                    Map<String, Object> map = new HashMap<>();
                    Shop shop = ShopDao.queryShopById(goods_shop.getSid());
                    for(Goods goods : list3){
                        if(goods.getId() == goods_shop.getGid() && goods_shop.getSid() == shopid){
                            map.put("shop",shop.getName());
                            map.put("goods",goods.getName());
                            map.put("price",goods.getPrice());
                            list.add(map);
                        }
                    }
                }
                request.setAttribute("maplist",list);
                request.getRequestDispatcher("pages/goods/hasloadgoods.jsp").forward(request,response);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


    }
}
