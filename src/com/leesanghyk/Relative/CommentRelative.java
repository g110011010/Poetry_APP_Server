package com.leesanghyk.Relative;

import com.leesanghyk.DBUtils.RemoteTransfer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/*用于控制器可用性的测试类*/
@Controller
@RequestMapping("/commentrelative")
public class CommentRelative {

    @RequestMapping(value = "/get_id_send_comment",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String getIDsendComment(@RequestBody String sql){
        return RemoteTransfer.getComment(sql,"user_id","id","time","comment");
    }

    @RequestMapping(value = "/set_comment",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    void setComment(@RequestBody String sql){
        RemoteTransfer.insertComment(sql);
    }

    @RequestMapping(value = "/set_story",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    void setStory(@RequestBody String sql){
        RemoteTransfer.insertStory(sql);
    }

}
