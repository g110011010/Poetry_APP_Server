package com.leesanghyk.DBUtils;

import com.gqf.constants.CONSTANTS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    //jdbc driver name and database URL
    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://"+ CONSTANTS.DB_HOST+":"+CONSTANTS.DB_PORT+"/"+CONSTANTS.DB_NAME+"?useSSL=true&dbnameuseUnicode=true&characterEncoding=utf-8";
    //Database credentials
    static final String USER=CONSTANTS.DB_USERNAME;
    static final String PASS=CONSTANTS.DB_PASSWORD;

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
