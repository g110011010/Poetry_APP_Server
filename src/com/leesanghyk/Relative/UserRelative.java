package com.leesanghyk.Relative;

import com.leesanghyk.DBUtils.RemoteTransfer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/*用于控制器可用性的测试类*/
@Controller
@RequestMapping("/userrelative")
public class UserRelative {
    @RequestMapping(value = "/set_fav",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String setFav(@RequestBody String sql){
        return RemoteTransfer.getComment(sql,"user_id","id","time","comment");
    }

    @RequestMapping(value = "/del_fav",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String DelFav(@RequestBody String sql){
        return RemoteTransfer.getComment(sql,"user_id","id","time","comment");
    }
}
