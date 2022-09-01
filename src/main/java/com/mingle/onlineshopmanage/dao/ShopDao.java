package com.mingle.onlineshopmanage.dao;



import com.mingle.onlineshopmanage.bean.Shop;

import java.sql.*;
import java.util.ArrayList;

public class ShopDao {
    static Connection connection;

    private static void openDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/myshop?characterEncoding=utf-8&serverTimezone=GMT%2B8",
                "root",
                "root"
        );
    }
    public static ArrayList<Shop> queryAll() throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "select * from shop";
        Statement statement = connection.createStatement();
        ArrayList<Shop> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            float score = resultSet.getFloat("score");
            String describe = resultSet.getString("describe");
            Shop shop = new Shop(id,name,describe,score);
            list.add(shop);

        }

        resultSet.close();
        statement.close();
        closeDB();
        return list;
    }
    public static ArrayList<Shop> queryByName(String Qname ) throws SQLException, ClassNotFoundException {
        openDB();

        String sql = "select * from shop where name like ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"%"+Qname+"%");

        ArrayList<Shop> list = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            float score = resultSet.getFloat("score");
            String describe = resultSet.getString("describe");
            Shop shop = new Shop(id,name,describe,score);
            list.add(shop);

        }

        resultSet.close();
        preparedStatement.close();
        closeDB();
        return list;
    }


    public static void delById(int id) throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "delete from shop where id="+ id;
        Statement statement = connection.createStatement();

        statement.execute(sql);
        statement.close();
        closeDB();

    }
    public static void add(Shop shop) throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "insert into shop (name ,`describe`,score) values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,shop.getName());
        preparedStatement.setString(2, shop.getDescribe());
        preparedStatement.setFloat(3, shop.getScore());

        preparedStatement.execute();

        preparedStatement.close();
        closeDB();

    }
    public static  Shop queryShopById(int id) throws SQLException, ClassNotFoundException {
        openDB();

        String sql = "select * from shop where id="+id;

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        String name = resultSet.getString("name");
        float score = resultSet.getFloat("score");
        String describe = resultSet.getString("describe");

        Shop shop = new Shop(id,name,describe,score);

//        System.out.println(shop);

        statement.close();
        resultSet.close();
        closeDB();
        return shop;

    }



    private static void closeDB() throws SQLException {
        connection.close();
    }

    public static void update(Shop shop) throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "update shop set name=?,score=?,`describe` =? where id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, shop.getName());
        preparedStatement.setFloat(2,shop.getScore());
        preparedStatement.setString(3, shop.getDescribe());
        preparedStatement.setInt(4,shop.getId());

        preparedStatement.execute();

        preparedStatement.close();


        closeDB();

    }
    public static boolean existShop(Shop shop) throws SQLException, ClassNotFoundException {
        openDB();
        Boolean flag = true;
        String sql = "select * from shop where name=?  and id !=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, shop.getName());
        preparedStatement.setInt(2, shop.getId());
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            flag = true;
        }else {
            flag = false;
        }

        preparedStatement.close();
        resultSet.close();
        closeDB();

        return flag;
    }
    public static int  getIdByName(String name) throws SQLException, ClassNotFoundException {
        openDB();
        Boolean flag = true;
        String sql = "select id from shop where name=?  ";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        int id = resultSet.getInt("id");

        preparedStatement.close();
        resultSet.close();
        closeDB();

        return id;
    }

}
