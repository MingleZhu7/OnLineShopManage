package com.mingle.onlineshopmanage.controller;

import com.mingle.onlineshopmanage.bean.Goods;
import com.mingle.onlineshopmanage.bean.Type;
import com.mingle.onlineshopmanage.dao.GoodsDao;
import com.mingle.onlineshopmanage.dao.TypeDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ServletTypeList", value = "/typelist")
public class ServletTypeList extends HttpServlet {
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
        try {
            ArrayList<Type> list = TypeDao.queryAll();
            ArrayList<Goods> list1 = GoodsDao.queryAll();
            ArrayList<Map<String,Object>> list2 = new ArrayList<>();

//            System.out.println(GoodsDao.queryByName("","电子产品").size());
            for(Type type:list){
                int count = GoodsDao.queryByName("",type.getTypename()).size();
                Map<String, Object> map1 = new HashMap<>();
                map1.put("type",type.getTypename());
                map1.put("id",type.getId());
                map1.put("count", count);
                list2.add(map1);
            }

            System.out.println(list.size()+" "+list1.size()+" "+ list2.size());
//            System.out.println(list.size()+" "+list1.size()+" "+ list2.size());

            request.setAttribute("maplist",list2);
            request.getRequestDispatcher("pages/goods/typelist.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

}
