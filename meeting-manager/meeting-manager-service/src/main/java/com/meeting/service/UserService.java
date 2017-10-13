package com.meeting.service;

import com.meeting.pojo.User;

import java.util.List;


/**
 * User: FlowingFire
 * Date: 2017/9/21 0021
 * Time: 23:45
 * Description:
 */
public interface UserService {
    public boolean register (User user);
    public User login (User user);
    public boolean logout (User user);

    public  List<User> findAllUsers();

    void deleteUser(User user);

    User findUserByUserName(String username);
    User updateUserByUserName(String username);
}
