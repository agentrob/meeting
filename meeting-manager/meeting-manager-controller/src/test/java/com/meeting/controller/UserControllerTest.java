package com.meeting.controller;


import com.meeting.pojo.User;
import org.junit.Test;

/**
 * User: FlowingFire
 * Date: 2017/9/30 0030
 * Time: 14:37
 * Description:
 */

public class UserControllerTest {

    UserController userController =new UserController();
    @Test
    public void login() throws Exception {
    }

    @Test
    public void register() throws Exception {
        for(int i=0; i<10; i++) {
            User user = new User();
            user.setUsername("jjjj"+i);
            user.setEmail("jkjkljl"+i);
            user.setPhone("1231321"+i);
            userController.register(user);
        }
    }

    @Test
    public void findAllUsers() throws Exception {
        userController.findAllUsers();
    }


}
