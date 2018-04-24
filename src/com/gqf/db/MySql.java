package com.gqf.db;

import com.gqf.constants.CONSTANTS;

import java.sql.*;

public class MySql {
    private static String USER_NAME;
    private static String PASSWORD;
    private static String SQLNAME;
    private static String HOST;
    private static String PORT;
    private static Connection CONN;
    public MySql(String username,String password,String sqlName,String host,String port){
        this.USER_NAME=username;
        this.PASSWORD=password;
        this.SQLNAME=sqlName;
        this.HOST=host;
        this.PORT=port;
        connectToMysql(USER_NAME,PASSWORD,SQLNAME);
    }
    public MySql(){
    }
   public MySql(String username, String password, String sqlName){
       this(username,password,sqlName,"127.0.0.1","3306");
    }

    public static Connection getConnection() {
        try {
            if(CONN.isClosed()){
                new MySql().connectToMysql(USER_NAME,PASSWORD,SQLNAME);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CONN;

    }

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
        String url = "jdbc:mysql://"+this.HOST+":"+this.PORT+"/" +sqlName+
                "?useSSL=true&dbnameuseUnicode=true&characterEncoding=utf-8";    //JDBC的URL
        try {
            CONN= DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
    * 执行select操作
    * @param conn 数据库连接
    * @param sql 要执行的数据库语句
    * @return 执行的结果*/
    public static ResultSet select(String sql) throws SQLException {
        Statement stat=CONN.createStatement();
        ResultSet result=stat.executeQuery(sql);
        stat.close();
        return result;
    }
    /*
    * 执行增加操作
    *  * @param conn 数据库连接
    * @param sql 要执行的数据库语句ii
    * @return 执行的结果，0表示成功执行
    * */
    public static int insert(String sql) throws SQLException {
        Statement stat=CONN.createStatement();
        stat.execute(sql);
        stat.close();
        return 0;
    }


    /**
     * 执行update操作*/
    public static int update(String sql) throws SQLException{
        return insert(sql);
    }
    public static boolean close(){
        try {
            MySql.CONN.close();
            return true;
        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
}
