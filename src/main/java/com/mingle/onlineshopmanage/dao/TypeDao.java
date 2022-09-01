package com.mingle.onlineshopmanage.dao;

import com.mingle.onlineshopmanage.bean.Goods_shop;
import com.mingle.onlineshopmanage.bean.Shop;
import com.mingle.onlineshopmanage.bean.Type;

import java.sql.*;
import java.util.ArrayList;

public class TypeDao {
    static Connection connection;

    private static void openDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/myshop?characterEncoding=utf-8&serverTimezone=GMT%2B8",
                "root",
                "root"
        );
    }
    public static ArrayList<Type> queryAll() throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "select * from type  order by id";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ArrayList<Type> list = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("typename");
            Type type = new Type(id,name);
            list.add(type);
        }

        resultSet.close();
        preparedStatement.close();
        closeDB();
        return list;
    }


    public static boolean existName(String Tname) throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "select * from type  where typename=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,Tname);
        preparedStatement.executeQuery();
        ArrayList<Type> list = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("typename");
            Type type = new Type(id,name);
            list.add(type);
        }

        resultSet.close();
        preparedStatement.close();
        closeDB();
        if(list.size()>0){
            return true;
        }else  {
            return false;
        }
    }
    public static void add(String name) throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "insert into type (typename) values (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,name);
        preparedStatement.execute();

        preparedStatement.close();
        closeDB();
    }


    public static void del(String name) throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "delete from type where typename=? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,name);
        preparedStatement.execute();

        preparedStatement.close();
        closeDB();
    }


    private static void closeDB() throws SQLException {
        connection.close();
    }

}
