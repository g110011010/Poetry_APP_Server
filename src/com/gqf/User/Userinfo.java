package com.gqf.User;

import java.util.ArrayList;
import java.util.List;

public class Userinfo{
    public String nickname;
    public String username;
    public String password;
   /* private boolean sex;
    private List<String> collect;
    private List<String> friends;*/

    public Userinfo(){
       // collect=new ArrayList<String>();
        //friends=new ArrayList<String>();

    }

   /* public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }*/

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

   /* public List<String> getCollect() {
        return collect;
    }

    public void setCollect(List<String> collect) {
        this.collect = collect;
    }*/

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   /* public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }*/





}
