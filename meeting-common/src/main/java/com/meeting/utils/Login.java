package com.meeting.utils;

import com.meeting.pojo.User;

/**
 * User: FlowingFire
 * Date: 2017/9/30 0030
 * Time: 12:14
 * Description:存储当前登陆用户
 */
public class Login {
   private static User user=new User();

    public static boolean isLogin(){
        if(user==null){
            return false;
        }
            return true;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Login.user = user;
    }
}
