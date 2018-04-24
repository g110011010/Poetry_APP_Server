package com.leesanghyk.DBUtils;

import com.gqf.db.MySql;
import org.json.JSONException;
import org.json.JSONObject;
import saber.lucene.POJO.PoetInfo;

import java.sql.*;
import java.util.List;

public class RemoteTransfer {
    /**
     * 对于指定的查找语句，以json字符串的形式返回查询结果
     * @param sql 要执行的查询的sql语句
     * @param column_name 要从查询结果中获取信息的列的列名数组
     * @return 返回查询结果的json的字符串形式
     * 返回的json形式为：
     * {
     *     “total”:12
     *     "0":{"列名1":“内容”,"列名2":"内容"。。。}
     *     "2"：{"列名1":“内容”,"列名2":"内容"。。。}
     *     。。。
     *     ”11“：{"列名1":“内容”,"列名2":"内容"。。。}
     * }
     * 对于*/
    public static String getPoetyInfo(String sql,String...column_name){
        Connection connection= MySql.getConnection();
        Statement statement= null;
        ResultSet resultSet=null;
        JSONObject jsonObject=null;
        JSONObject result=new JSONObject();
        try {
            statement = connection.createStatement();
            statement.executeQuery(sql);
            resultSet=statement.getResultSet();
            if (resultSet.next()){
                jsonObject=new JSONObject();
                for(int j=0;j<column_name.length;j++){
                    jsonObject.put(column_name[j],resultSet.getString(column_name[j]));
                }
                result.put(String.valueOf(0),jsonObject);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
    public static String getComment(String sql,String...column_name){
        Connection connection=DBConnection.getConnection();
        Statement statement= null;
        ResultSet resultSet=null;
        JSONObject jsonObject=null;
        JSONObject result=new JSONObject();
        try {
            statement = connection.createStatement();
            statement.executeQuery(sql);
            resultSet=statement.getResultSet();
            int i=0;
            while (resultSet.next()) {
                jsonObject=new JSONObject();
                for(int j=0;j<column_name.length;j++){
                    jsonObject.put(column_name[j],resultSet.getString(column_name[j]));
                }
                result.put(String.valueOf(i),jsonObject);
                i+=1;
            }
            result.put("total",i);
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error:sql语句：\""+sql+"执行失败！");
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("数组越界，执行失败！");
        }
        return result.toString();
    }

    public static String getSearcher(List<PoetInfo> poetInfos) {
        JSONObject jsonObject = null;
        JSONObject result = new JSONObject();
        int i = 0;
        while (i < poetInfos.size()) {
            PoetInfo poetInfo = poetInfos.get(i);
            jsonObject = new JSONObject();
            try {
                jsonObject.put("id", poetInfo.getId());
                jsonObject.put("poet_id", poetInfo.getPoet_id());
                jsonObject.put("title", poetInfo.getTitle());
                jsonObject.put("content", poetInfo.getContent());
                result.put(String.valueOf(i), jsonObject);
                i += 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            result.put("total", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result.toString();
    }


    public static void insertComment(String sql){
        Connection connection=DBConnection.getConnection();
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertStory(String sql){
        Connection connection=DBConnection.getConnection();
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
