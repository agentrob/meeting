package com.meeting.service;

import com.meeting.pojo.User;



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
}
