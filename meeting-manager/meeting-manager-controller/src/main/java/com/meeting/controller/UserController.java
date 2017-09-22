package com.meeting.controller;

import com.meeting.pojo.User;
import com.meeting.service.UserService;
import com.meeting.serviceimpl.UserServiceImpl;

/**
 * User: FlowingFire
 * Date: 2017/9/21 0021
 * Time: 23:19
 * Description:
 */
public class UserController {
    UserService userService =new UserServiceImpl();
    public User  login(String username, String password){
        User login=null;
        login.setPassword(password);
        login.setUsername(username);
        //调用service去登陆
        login = userService.login(login);
        return login;
    }
    public boolean  register(String username, String password){
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        //调用service去登陆
        boolean register = userService.register(user);
        return register;
    }
}
