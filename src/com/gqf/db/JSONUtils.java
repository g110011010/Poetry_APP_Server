package com.gqf.db;

import com.gqf.User.Userinfo;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {

    public static Userinfo parseJson(JSONObject json) throws JSONException{
        Userinfo user=new Userinfo();
        user.setUsername(json.getString("username"));
        user.setNickname(json.getString("nickname"));
        user.setPassword(json.getString("password"));
        return user;
    }
}
