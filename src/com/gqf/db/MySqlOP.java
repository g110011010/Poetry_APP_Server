package com.gqf.db;
import com.gqf.User.Userinfo;
import com.gqf.constants.CONSTANTS;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.swing.text.html.HTMLDocument;
import java.lang.reflect.Field;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Iterator;

/*对MySQL数据库进行操作的类*/
public class MySqlOP {
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



    public static String select(String sql,String...column_name) {
        Connection connection= MySql.getConnection();
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
       /*         jsonObject.put(CONSTANTS.TABLE_POETRIES.ID,resultSet.getInt(CONSTANTS.TABLE_POETRIES.ID));
                System.out.println(resultSet.getString(CONSTANTS.TABLE_POETRIES.CONTENT));
                jsonObject.put(CONSTANTS.TABLE_POETRIES.CONTENT,resultSet.getString(CONSTANTS.TABLE_POETRIES.CONTENT));
                jsonObject.put(CONSTANTS.TABLE_POETRIES.TITLE,resultSet.getString(CONSTANTS.TABLE_POETRIES.TITLE));
                jsonObject.put(CONSTANTS.TABLE_POETRIES.POET_ID,resultSet.getInt(CONSTANTS.TABLE_POETRIES.POET_ID));*/
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
    public static boolean save(Userinfo user) {
        Connection connection = MySql.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        JSONObject jsonObject = null;
        JSONObject result = new JSONObject();
        try {
            statement = connection.createStatement();
            Class userclass=user.getClass();
            Field[] f=userclass.getDeclaredFields();
            statement.executeQuery("select * from user where username="+"'"+user.getUsername()+"'"+";");
            ResultSet resultset=statement.getResultSet();
            if(!resultset.next()){
                return false;
            }
            String updatesql="";
            for(Field field:f ){
                updatesql="update user set "+field.getName()+"='"+field.get(user)+"'"+" where username ="+"'"+user.getUsername()+"'"+";";
                statement.execute(updatesql);
            }
            resultSet.close();

        }catch(SQLException e){
            e.printStackTrace();
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }
        return true;
    }

    public static String collect(String info){
        JSONObject resultset=new JSONObject();
        try {
            String result = MySqlOP.select("select * from collected where user_id=" + "'"+info+"';", "poetries_id");
            JSONObject json = new JSONObject(new JSONTokener(result));
            Iterator iter=json.getJSONObject("0").keys();
            int i=0;
            while(iter.hasNext()){
                String poetid=(String)json.getJSONObject("0").get((String)iter.next());
                String poetryinfo=select("select * from poetries_l where id ="+poetid,"id","title");
                JSONObject jsoninfo =new JSONObject(new JSONTokener(poetryinfo));
                resultset.put(i+++"",jsoninfo.get("0"));
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
        return resultset.toString();
    }

    public static boolean insert(String info){//趁没人发现偷偷修改
        Connection connection=MySql.getConnection();
        Statement stat= null;
        try {
            stat = connection.createStatement();
            JSONObject json=new JSONObject(new JSONTokener(info));
            Iterator keys=json.keys();
            String sqlkey="",sqlvalue="";
            while(keys.hasNext()){
                String key=(String)keys.next();
                sqlkey+=key+",";
                sqlvalue+="'"+json.get(key)+"'"+",";
            }
            sqlkey=sqlkey.substring(0,sqlkey.length()-1);
            sqlvalue=sqlvalue.substring(0,sqlvalue.length()-1);
            String sql="Insert into user("+sqlkey+ ") values("+sqlvalue+");";
            stat.execute(sql);
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }catch (JSONException e){
            e.printStackTrace();
        }

        return true;
    }
}
