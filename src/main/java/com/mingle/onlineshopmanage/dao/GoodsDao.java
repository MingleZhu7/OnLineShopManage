package com.mingle.onlineshopmanage.dao;



import com.mingle.onlineshopmanage.bean.Goods;
import com.mingle.onlineshopmanage.bean.Goods_shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsDao {
    static Connection connection;

    private static void openDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/myshop?characterEncoding=utf-8&serverTimezone=GMT%2B8",
                "root",
                "root"
        );
    }
    public static ArrayList<Goods> queryAll() throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "select * from goods";
        Statement statement = connection.createStatement();
        ArrayList<Goods> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            float price = resultSet.getFloat("price");
            String type = resultSet.getString("type");
            Goods goods = new Goods(id,name,price,type);
            list.add(goods);

        }

        resultSet.close();
        statement.close();
        closeDB();
        return list;
    }
    public static ArrayList<Goods> queryByName(String Qname,String Qtype ) throws SQLException, ClassNotFoundException {
        openDB();

         String  sql = "select * from goods where name like ? and type like ?";


        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"%"+Qname+"%");
        preparedStatement.setString(2,"%"+Qtype+"%");
        ArrayList<Goods> list = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            float price = resultSet.getFloat("price");
            String type = resultSet.getString("type");
            Goods goods = new Goods(id,name,price,type);
            list.add(goods);

        }

        resultSet.close();
        preparedStatement.close();
        closeDB();
        return list;
    }


    public static void delById(int id) throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "delete from goods where id="+ id;
        Statement statement = connection.createStatement();

        statement.execute(sql);
        statement.close();
        closeDB();

    }
    public static void add(Goods goods) throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "insert into goods (name , price, type) values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,goods.getName());
        preparedStatement.setFloat(2, goods.getPrice());
        preparedStatement.setString(3, goods.getType());

        preparedStatement.execute();

        preparedStatement.close();
        closeDB();

    }
    public static  Goods querGoodsById(int id) throws SQLException, ClassNotFoundException {
        openDB();

        String sql = "select * from goods where id="+id;

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        String name = resultSet.getString("name");
        float price = resultSet.getFloat("price");
        String type = resultSet.getString("type");

        Goods goods = new Goods(id,name,price,type);

        System.out.println(goods);

        statement.close();
        resultSet.close();
        closeDB();
        return goods;

    }



    private static void closeDB() throws SQLException {
        connection.close();
    }

    public static void update(Goods goods) throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "update goods set name=?,price=?,type=? where id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, goods.getName());
        preparedStatement.setFloat(2,goods.getPrice());
        preparedStatement.setString(3, goods.getType());
        preparedStatement.setInt(4,goods.getId());

        preparedStatement.execute();

        preparedStatement.close();


        closeDB();

    }
    public static boolean existUser(Goods goods) throws SQLException, ClassNotFoundException {
        openDB();
        Boolean flag = true;
        String sql = "select * from goods where name=? and id !=? ";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, goods.getName());
        preparedStatement.setInt(2, goods.getId());
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
        String sql = "select id from goods where name=?  ";
        System.out.println(name);
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
    public static ArrayList<Goods> queryInId(ArrayList<Goods_shop> alist) throws SQLException, ClassNotFoundException {
        openDB();

        StringBuffer sql = new StringBuffer();

        sql.append("select * from goods where id in (");

        for (int i=0; i<alist.size(); i++){
            if(i == alist.size()-1){
                sql.append("?)");
            }else {
                sql.append("?,");
            }
        }
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

        for (int i=0; i<alist.size(); i++){
            preparedStatement.setInt(i+1, alist.get(i).getGid());
        }

        ArrayList<Goods> list = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            float price = resultSet.getFloat("price");
            String type = resultSet.getString("type");
            Goods goods = new Goods(id,name,price,type);
            list.add(goods);

        }

        resultSet.close();
        preparedStatement.close();
        closeDB();
        return list;
    }

}
