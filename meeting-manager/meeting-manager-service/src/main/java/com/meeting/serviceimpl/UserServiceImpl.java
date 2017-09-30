package com.meeting.serviceimpl;

import com.meeting.mapper.UserMapper;
import com.meeting.mapperimpl.UserMapperImpl;
import com.meeting.pojo.User;
import com.meeting.service.UserService;

import java.util.List;


/**
 * User: FlowingFire
 * Date: 2017/9/21 0021
 * Time: 23:45
 * Description:
 */
public class UserServiceImpl implements UserService {
    UserMapper userMapper =new UserMapperImpl();


    public boolean register(User user) {


        User user1=userMapper.findUser(user);
        if(user1==null){
            userMapper.addUser(user);
            return true;
        }

        return false;
    }


    public User login(User user) {
        return userMapper.findUserByUserName(user.getUsername());
    }


    public boolean logout(User user) {
        return false;
    }

    @Override
    public List<User> findAllUsers() {

        List<User> users =userMapper.findAll();

        return users;
    }


}
