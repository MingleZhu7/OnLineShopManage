package com.mingle.onlineshopmanage.dao;

import com.mingle.onlineshopmanage.bean.Goods;
import com.mingle.onlineshopmanage.bean.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.*;
import java.util.ArrayList;

public class OrderDao {
    static Connection connection;

    private static void openDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/myshop?characterEncoding=utf-8&serverTimezone=GMT%2B8",
                "root",
                "root"
        );
    }
    public static ArrayList<Order> queryAll() throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "select * from `order`";

        Statement statement = connection.createStatement();
        ArrayList<Order> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String id = resultSet.getString("id");
            String receive = resultSet.getString("receive");
            String receivephone = resultSet.getString("receivephone");
            float money = resultSet.getFloat("money");
            String orderstatus = resultSet.getString("orderstatus");
            String paystatus = resultSet.getString("paystatus");
            String payway = resultSet.getString("payway");
            String send = resultSet.getString("send");
            Order order =new Order(id,receive,receivephone,money,orderstatus,paystatus,payway,send);
            list.add(order);

        }

        resultSet.close();
        statement.close();
        closeDB();
        return list;
    }
    public static ArrayList<Order> queryByName(String orderstatus1,String paystatus1,String payway1,String id1 ) throws SQLException, ClassNotFoundException {
        openDB();

        String sql = "select * from `order` where orderstatus like ? and paystatus like ? and payway like ? and id like ?";


        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"%"+orderstatus1+"%");
        preparedStatement.setString(2,"%"+paystatus1+"%");
        preparedStatement.setString(3,"%"+payway1+"%");
        preparedStatement.setString(4,"%"+id1+"%");
        ArrayList<Order> list = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String id = resultSet.getString("id");
            String receive = resultSet.getString("receive");
            String receivephone = resultSet.getString("receivephone");
            float money = resultSet.getFloat("money");
            String orderstatus = resultSet.getString("orderstatus");
            String paystatus = resultSet.getString("paystatus");
            String payway = resultSet.getString("payway");
            String send = resultSet.getString("send");
            Order order =new Order(id,receive,receivephone,money,orderstatus,paystatus,payway,send);
            list.add(order);

        }

        resultSet.close();
        preparedStatement.close();
        closeDB();
        return list;
    }

    private static void closeDB() throws SQLException {
        connection.close();
    }
}
