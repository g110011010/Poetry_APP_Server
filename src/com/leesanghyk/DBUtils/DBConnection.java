package com.leesanghyk.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    //jdbc driver name and database URL
    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://39.107.232.16:3306/poetry?useSSL=true&dbnameuseUnicode=true&characterEncoding=utf-8";
    //Database credentials
    static final String USER="yundb";
    static final String PASS="1.aly2.yundb3.finish";

    static Connection connection=null;

    public static Connection getConnection(){
        try {
            Class.forName(JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,USER,PASS);
            //System.out.println("success");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
