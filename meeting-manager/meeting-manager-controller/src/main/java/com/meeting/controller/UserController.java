package com.meeting.controller;
// 导入slf4j类
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.meeting.pojo.User;
import com.meeting.service.UserService;
import com.meeting.serviceimpl.UserServiceImpl;
import com.meeting.utils.Login;

import java.util.List;

/**
 * User: FlowingFire
 * Date: 2017/9/21 0021
 * Time: 23:19
 * Description:
 */
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    UserService userService =new UserServiceImpl();
    public User  login(String username, String password){
        User login=new User();
        login.setPassword(password);
        login.setUsername(username);
        //调用service去登陆
        logger.debug("去登陆！");
        login = userService.login(login);
        Login.setUser(login);
        logger.debug("登陆成功了！");
        return login;
    }
    public boolean  register(User user){


        //调用service去登陆
        logger.debug("userservice注册！");
        boolean register = userService.register(user);
        return register;
    }

    public void findAllUsers() {

       if(Login.isLogin()){

           List<User> users= userService.findAllUsers();
           System.out.println("---------------");
           System.out.println("用户名  邮箱  电话号码 ");

            for (User user:
                 users) {
                System.out.println(user.getUsername()+"  "+user.getEmail()+"   "+user.getPhone());

            }
       }else {
           System.out.println("请登陆再查询！");
       }
    }
}
