package com.gqf.map;

import com.gqf.User.Userinfo;
import com.gqf.constants.CONSTANTS;
import com.gqf.db.JSONUtils;
import com.gqf.db.MySqlOP;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.jar.JarException;

import static com.gqf.db.MySqlOP.save;

/*用于控制器可用性的测试类*/
@Controller
@RequestMapping("/maprelative")
public class MapRelative {
    @RequestMapping(value = "/poetList",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String poetList(@RequestBody String sql){
        String s= MySqlOP.select(sql, "name","id").toString();
        return s;
    }
    @RequestMapping(value = "/poetriesList",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String poetriesList(@RequestBody String sql){
        String s= MySqlOP.select(sql,"name","dynasty","age","city_of_ancient","city_of_current","county_of_ancient","county_of_current","id",
                CONSTANTS.TABLE_POETRIES_L.LONGTITUDE,
                CONSTANTS.TABLE_POETRIES_L.LATTITUDE,
                CONSTANTS.TABLE_POETRIES_L.TITLE).toString();
        return s;
    }

    @RequestMapping(value = "/validation",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String validation(@RequestBody String account) {
        String sql = "select * from user where username =" + "\""+account+"\";";
        return MySqlOP.select(sql, "id","username", "password","neckname").toString();
    }


    @RequestMapping(value = "/editinfo",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String saveinfo(@RequestBody String info) throws JSONException {
        Userinfo user= JSONUtils.parseJson(new JSONObject(new JSONTokener(info)));
        return (Boolean.toString(MySqlOP.save(user)));
    }

    @RequestMapping(value = "/insertinfo",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String insertinfo(@RequestBody String info) throws JSONException {
        return (Boolean.toString(MySqlOP.insert(info)));
    }

    @RequestMapping(value = "/collected",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String insert(@RequestBody String info) throws JSONException {
        return MySqlOP.collect(info).toString();
    }

}
