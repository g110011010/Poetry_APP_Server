package com.gqf.db;

import java.sql.*;

public class DbTest {
    public Connection CONN=null;
    /*
     *连接到某个特定的数据库
     * @param userName 数据库用户名
     * @param password 数据库密码
     * @param sqlName 数据库名称
     * @return 连接成功后返回数据库连接
     * @author sf
     * @version 1.0*/
    void connectToMysql(String userName, String password, String sqlName) {
        String result = "success";
        try {
            //调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");
        } catch (ClassNotFoundException e1) {
            System.out.println("找不到MySQL驱动!");
            e1.printStackTrace();
        }
        String url = "jdbc:mysql://39.107.232.16:3306/" +sqlName+
                "?useSSL=true&dbnameuseUnicode=true&characterEncoding=utf-8";    //JDBC的URL
        try {
            CONN= DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DbTest dbTest=new DbTest();
        dbTest.connectToMysql("yundb","1.aly2.yundb3.finish","poetry");
        Connection connection=dbTest.CONN;
        try {
            Statement statement=connection.createStatement();
            String sql="select name from poet_l";
            statement.executeQuery(sql);
           ResultSet resultSet=statement.getResultSet();
           while(resultSet.next()){
               Statement statement1= dbTest.CONN.createStatement();
               String name=resultSet.getString("name");
               String sql1="select id from poets where name=\""+name.trim()+"\"";
               ResultSet resultSet1=statement1.executeQuery(sql1);
               resultSet1.next();
               int id=resultSet1.getInt("id");
               String sql2="update poetries_l set poet_id="+id+" where name=\""+name+"\"";
               statement1.execute(sql2);
           }
          /* resultSet.close();
           resultSet.next();*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
