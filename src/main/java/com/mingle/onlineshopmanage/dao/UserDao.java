package com.mingle.onlineshopmanage.dao;

import com.mingle.onlineshopmanage.bean.Type;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    static Connection connection;

    private static void openDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/myshop?characterEncoding=utf-8&serverTimezone=GMT%2B8",
                "root",
                "root"
        );
    }



    public static boolean isOk(String username, String password) throws SQLException, ClassNotFoundException {
        openDB();
        boolean flag;
        String sql = "select * from user  where username=? and password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        preparedStatement.executeQuery();
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            flag = true;
        }else {
            flag = false;
        }

        resultSet.close();
        preparedStatement.close();
        closeDB();
       return flag;
    }



    private static void closeDB() throws SQLException {
        connection.close();
    }
}
