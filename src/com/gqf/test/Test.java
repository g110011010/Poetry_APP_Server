package com.gqf.test;

import com.gqf.constants.CONSTANTS;
import com.gqf.db.MySql;
import com.gqf.db.MySqlOP;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.sql.SQLException;


/*用于控制器可用性的测试类*/
@Controller
@RequestMapping("/Test")
public class Test {
    @RequestMapping(value = "/testmode",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody String printTest(@RequestBody String sql){




        return MySqlOP.select(sql,"title","poet_id","content").toString();
    }
}
