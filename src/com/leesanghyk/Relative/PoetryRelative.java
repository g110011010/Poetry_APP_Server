package com.leesanghyk.Relative;

import com.leesanghyk.DBUtils.RemoteTransfer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import saber.lucene.POJO.PoetInfo;
import saber.lucene.WordSearcher;

import java.io.IOException;
import java.util.List;

/*用于控制器可用性的测试类*/
@Controller
@RequestMapping("/poetryrelative")
public class PoetryRelative {

    @RequestMapping(value = "/get_poetry_info",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String getPoetyInfo(@RequestBody String sql){
        return RemoteTransfer.getPoetyInfo(sql,"title","poetries_content","name");
    }

    @RequestMapping(value = "/search_poetry_info",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String searchPoetyInfo(@RequestBody String word){
        WordSearcher wordSearcher=new WordSearcher();
        List<PoetInfo> poetInfos = null;
        try {
//这里一定要写绝对路径
            poetInfos=wordSearcher.search("/usr/share/tomcat/lib/word_index",word);
            System.out.println(poetInfos.get(1).getTitle());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  RemoteTransfer.getSearcher(poetInfos);
    }
}
