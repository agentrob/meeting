package com.meeting.mapperimpl;

import com.meeting.mapper.UserMapper;
import com.meeting.pojo.User;
import com.meeting.utils.JSonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: FlowingFire
 * Date: 2017/9/30 0030
 * Time: 10:40
 * Description:
 */
public class UserMapperImpl implements UserMapper {
    @Override
    public void addUser(User user) {
        JSonUtil.saveUser(user);
    }

    @Override
    public User findUser(User user) {
        return null;
    }

    @Override
    public User findUserByUserName(String username) {
        User user = (User) JSonUtil.loadUser(username);

        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        File file = new File("user");
        File[] tempList = file.listFiles();
        System.out.println("该目录下对象个数：" + tempList.length);
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                String name = tempList[i].getName();
                String[] split = name.split("\\.");
                String s= split[0];
                System.out.println(s);
                User user = JSonUtil.loadUser(s);
                users.add(user);
            }
        }
        return users;
    }



    @Override
    public void deleteUserByUserName(String username) {
        JSonUtil.deleteUser(username);
    }
}