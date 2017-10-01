package com.meeting.serviceimpl;

import com.meeting.mapper.AgendaMapper;
import com.meeting.mapper.AgendaMapperImpl;
import com.meeting.mapper.UserMapper;
import com.meeting.mapperimpl.UserMapperImpl;
import com.meeting.pojo.Agenda;
import com.meeting.pojo.User;
import com.meeting.service.AgendaService;
import com.meeting.service.AgendaServiceImpl;
import com.meeting.service.UserService;
import com.meeting.utils.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * User: FlowingFire
 * Date: 2017/9/21 0021
 * Time: 23:45
 * Description:
 */
public class UserServiceImpl implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    UserMapper userMapper =new UserMapperImpl();
    AgendaMapper agendaMapper =new AgendaMapperImpl();

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

    @Override
    public void deleteUser(User user) {
        logger.debug("删除该用户文件");
        userMapper.deleteUserByUserName(user.getUsername());
        logger.debug("更新会议信息表");
            logger.debug("删除该用户发起的会议");
        for (Agenda agenda: user.getCreatedgendas()) {
            logger.debug("删除"+agenda.getTitle());
            agendaMapper.delete(agenda.getTitle());
            logger.debug("更新用户信息，参与："+agenda.getTitle());
            for (String username:
                 agenda.getParticipators()) {
                User user2 =userMapper.findUserByUserName(username);
                user2.getAttendgendas().remove(agenda);
                userMapper.addUser(user2);
            }

        }
            logger.debug("更新该用户参与的会议");
        for (Agenda agenda:
             user.getAttendgendas()) {
            agenda.getParticipators().remove(user.getUsername());
           List<String> usernames= agenda.getParticipators();
            if(usernames.isEmpty()){
                logger.debug("删除创建的会议");
                agendaMapper.delete(agenda.getTitle());
                logger.debug("创建人中删除该会议");
                User user2=userMapper.findUserByUserName(agenda.getInitiatorName());
                user.getCreatedgendas().remove(agenda);
                userMapper.addUser(user2);
            }else {
                agendaMapper.add(agenda);
            }
        }
    }

    @Override
    public User findUserByUserName(String username) {
        return userMapper.findUserByUserName(username);
    }

    @Override
    /**
     * @description 用于查询最新的数据
     * @method  updateUserByUserName
     * @param username
     * @return
     * @date: 2017年10月01日 17:36:08
     * @author:刘烨
     */
    public User updateUserByUserName(String username) {
        User userByUserName = userMapper.findUserByUserName(username);
        logger.debug("更新集合里面的集合");
        List<Agenda> attendgendas = userByUserName.getAttendgendas();
        List<Agenda> temp1=new ArrayList<>();
        List<Agenda> temp2=new ArrayList<>();
        for (Agenda agenda:
        attendgendas) {
            Agenda agendaByTittle = agendaMapper.findAgendaByTittle(agenda.getTitle());
            temp1.add(agendaByTittle);
        }
        userByUserName.setAttendgendas(temp1);

        List<Agenda> createdgendas = userByUserName.getCreatedgendas();
         for (Agenda agenda:
                    createdgendas) {
                Agenda agendaByTittle = agendaMapper.findAgendaByTittle(agenda.getTitle());
                temp2.add(agendaByTittle);
         }
        userByUserName.setCreatedgendas(temp2);
        return userByUserName;
    }


}
