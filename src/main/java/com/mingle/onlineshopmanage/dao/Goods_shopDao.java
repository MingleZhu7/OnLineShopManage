package com.mingle.onlineshopmanage.dao;

import com.mingle.onlineshopmanage.bean.Goods_shop;
import com.mingle.onlineshopmanage.bean.Shop;

import java.sql.*;
import java.util.ArrayList;

public class Goods_shopDao {
    static Connection connection;

    private static void openDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/myshop?characterEncoding=utf-8&serverTimezone=GMT%2B8",
                "root",
                "root"
        );
    }

    public static ArrayList<Goods_shop> selectById(int Gid,int Sid) throws SQLException, ClassNotFoundException {
        openDB();

        String sql = "select * from goods_shop where Sid=? and Gid =? ";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,Sid);
        preparedStatement.setInt(2,Gid);

        ArrayList<Goods_shop> list = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int gid = resultSet.getInt("Gid");
            int sid = resultSet.getInt("Sid");
            Goods_shop goods_shop = new Goods_shop(gid,sid);
            list.add(goods_shop);
        }

        resultSet.close();
        preparedStatement.close();
        closeDB();
        return list;

    }
    public static ArrayList<Goods_shop> selectByShopId(int Sid) throws SQLException, ClassNotFoundException {
        openDB();

        String sql = "select * from goods_shop where Sid=?  ";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,Sid);

        ArrayList<Goods_shop> list = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int gid = resultSet.getInt("Gid");
            int sid = resultSet.getInt("Sid");
            Goods_shop goods_shop = new Goods_shop(gid,sid);
            list.add(goods_shop);
        }

        resultSet.close();
        preparedStatement.close();
        closeDB();
        return list;

    }
    public static ArrayList<Goods_shop> selectAll() throws SQLException, ClassNotFoundException {
        openDB();

        String sql = "select * from goods_shop ";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ArrayList<Goods_shop> list = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int gid = resultSet.getInt("Gid");
            int sid = resultSet.getInt("Sid");
            Goods_shop goods_shop = new Goods_shop(gid,sid);
            list.add(goods_shop);
        }
        resultSet.close();
        preparedStatement.close();
        closeDB();
        return list;

    }
    public static void add(int Gid,int  Sid) throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "insert into goods_shop (Gid ,Sid) values (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,Gid);
        preparedStatement.setInt(2,Sid);

        preparedStatement.execute();

        preparedStatement.close();
        closeDB();
    }

    public static void del(int Gid,int  Sid) throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "delete from goods_shop where Gid=? and Sid=? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,Gid);
        preparedStatement.setInt(2,Sid);

        preparedStatement.execute();

        preparedStatement.close();
        closeDB();
    }


    private static void closeDB() throws SQLException {
        connection.close();
    }

}
