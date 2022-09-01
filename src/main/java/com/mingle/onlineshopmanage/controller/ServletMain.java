package com.mingle.onlineshopmanage.controller;

import com.mingle.onlineshopmanage.dao.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ServletMain", value = "/main")
public class ServletMain extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Cookie cookie1 = null;
        Cookie cookie2 = null;
//        response.sendRedirect("login.jsp");
//        System.out.println(cookies.length);
        if( cookies!=null && cookies.length > 0){
            for (Cookie cookie :
                    cookies) {
                if(cookie.getName().equals("isLogin") ){
                    cookie1 = cookie;
                    System.out.println("jj");
                }
                if(cookie.getName().equals("username"))
                    cookie2 = cookie;
            }
        }
        if(cookie1 == null ) { // 有cookie

            response.sendRedirect("login");
            return;
        }
        try {
            int goodscount = GoodsDao.queryAll().size();
            int shopcount = ShopDao.queryAll().size();
            int typecount = TypeDao.queryAll().size();
            int ordercount = OrderDao.queryAll().size();
            int shopgoodscount = Goods_shopDao.selectAll().size();
            Map<String ,Integer> map = new HashMap<>();
            map.put("goodscount",goodscount);
            map.put("shopcount",shopcount);
            map.put("typecount",typecount);
            map.put("ordercount",ordercount);
            map.put("shopgoodscount",shopgoodscount);
            request.setAttribute("map",map);
            request.setAttribute("username",cookie2.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("index.jsp").forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
