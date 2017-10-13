package com.meeting.mapper;

import com.meeting.pojo.User;

import java.util.List;

/**
 * User: FlowingFire
 * Date: 2017/9/30 0030
 * Time: 10:40
 * Description:
 *
 *
 */
public interface UserMapper {
    void addUser(User user);


    User findUser(User user);

    User findUserByUserName(String username);

    List<User> findAll();

    void deleteUserByUserName(String username);

}
