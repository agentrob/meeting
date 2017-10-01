package com.meeting.service;

import com.meeting.mapper.AgendaMapper;
import com.meeting.mapper.AgendaMapperImpl;
import com.meeting.mapper.UserMapper;
import com.meeting.mapperimpl.UserMapperImpl;
import com.meeting.pojo.Agenda;
import com.meeting.pojo.User;
import com.meeting.utils.JSonUtil;
import com.meeting.utils.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: FlowingFire
 * Date: 2017/9/30 0030
 * Time: 14:52
 * Description:
 */
public class AgendaServiceImpl implements AgendaService {
    private final static Logger logger = LoggerFactory.getLogger(JSonUtil.class);
    UserMapper userMapper=new UserMapperImpl();
    AgendaMapper agendaMapper =new AgendaMapperImpl();
    List<User> userList=new ArrayList<>();
    @Override
    public boolean createAgenda(Agenda agenda) {

        agenda.setInitiatorName(Login.getUser().getUsername());
        logger.debug("判断会议人能否参加");
        for (String username :
                agenda.getParticipators()){
            User user=userMapper.findUserByUserName(username);
            if(user!=null) {
                userList.add(user);
                if (!isAttend(user, agenda)) {
                    System.out.println(username + "这个用户不能参加");
                    return false;
                }

            }else {
                System.out.println("没有"+username+"这个用户");
            }
        }
        logger.debug("更新参与的用户信息");
        if(userList.isEmpty()) {
            logger.debug(" 没有这些用户");
            return false;
        }
        for (User user :
                userList){
            if(user.getAttendgendas()!=null) {
                user.getAttendgendas().add(agenda);
            }else {
                List<Agenda> agendaList =new ArrayList<>();
                agendaList.add(agenda);
                user.setAttendgendas(agendaList);

            }
            userMapper.addUser(user);
        }


        logger.debug("更新创建人信息");
        String username = Login.getUser().getUsername();
        User userByUserName = userMapper.findUserByUserName(username);
        List<Agenda> createdgendas = userByUserName.getCreatedgendas();
        if(createdgendas==null){
            createdgendas=new ArrayList<>();
            userByUserName.setCreatedgendas(createdgendas);
        }
        createdgendas.add(agenda);
        userMapper.addUser(userByUserName);



        logger.debug("生成会议");
        boolean flag= agendaMapper.add(agenda);
       return flag;
    }

    @Override
    public boolean inviteUser(String[] users, Agenda agenda) {
        boolean flag=true;

            List<String> usernames= agenda.getParticipators();
            logger.debug("获取用户信息");
            for (String username:
                 users) {

                User user =userMapper.findUserByUserName(username);
                if (user!=null){
                    userList.add(user);
                }

            }

            logger.debug("判断是否能参加");
            for (User user:
                    userList) {
              if(!isAttend(user,agenda)){
                  System.out.println(user.getUsername()+": 不能参加会议");
                  flag=false;
              }else {
                  logger.debug("更新会议对象信息");
                  usernames.add(user.getUsername());
                  logger.debug("更新用户信息到磁盘");
                  List<Agenda> agendaList =user.getAttendgendas();
                  if(agendaList==null){
                      agendaList=new ArrayList<>();
                      user.setAttendgendas(agendaList);
                  }
                  agendaList.add(agenda);
                  user.setAttendgendas(agendaList);
                  userMapper.addUser(user);
                  System.out.println(user.getUsername()+": 成功邀请");
                  usernames.add(user.getUsername());
              }
            }


            logger.debug("更新会议对象信息");
            agenda.setParticipators(usernames);
            logger.debug("更新会议信息到磁盘");
            agendaMapper.add(agenda);

            logger.debug("更新创建人信息");
            String username = Login.getUser().getUsername();
            User userByUserName = userMapper.findUserByUserName(username);
            List<Agenda> createdgendas = userByUserName.getCreatedgendas();
            if(createdgendas==null){
                createdgendas=new ArrayList<>();
                userByUserName.setCreatedgendas(createdgendas);
            }
            createdgendas.add(agenda);
            userMapper.addUser(userByUserName);


            return flag;
    }

    @Override
    public boolean deleteUser(String[] users, Agenda agenda) {
        boolean flag=true;
        List<String> usernames= agenda.getParticipators();
        logger.debug("获取用户信息,更新用户信息");
        for (String username:
                users) {
            User user =userMapper.findUserByUserName(username);
            if(user!=null) {
                logger.debug("更新"+username+"用户信息");
                List<Agenda> agendaList = user.getAttendgendas();
                if (agendaList == null) agendaList = new ArrayList<>();
                boolean r=agendaList.remove(agenda);
                if(!r) continue;
                user.setAttendgendas(agendaList);
                userMapper.addUser(user);
                logger.debug("更新会议信息");
                usernames.remove(username);
            }
        }

        logger.debug("更新会议对象信息");
        agenda.setParticipators(usernames);
        logger.debug("更新会议信息到磁盘");
        if(usernames.isEmpty()) {
           logger.debug("删除会议");
           agendaMapper.delete(agenda.getTitle());
           logger.debug("创建人中删除该会议");
           User user=userMapper.findUserByUserName(agenda.getInitiatorName());
           user.getCreatedgendas().remove(agenda);
           userMapper.addUser(user);
        } else {
            agendaMapper.add(agenda);
        }





        return flag;
    }

    @Override
    public void deleteAgenda(Agenda agenda) {

    }

    @Override
    public void deleteAgendaByInitor(Agenda agenda, User user) {

    }

    @Override
    /**
     * @description 创建者取消一个会议
     * @method  cancelAgendaByInitor
     * @param agenda
     * @return
     * @date: 2017年10月01日 14:24:27
     * @author:刘烨
     */
    public boolean cancelAgendaByInitor(Agenda agenda) {

        logger.debug("删除"+agenda.getTitle());
        agendaMapper.delete(agenda.getTitle());
        logger.debug("更新用户信息，参与："+agenda.getTitle());
        for (String username:
                agenda.getParticipators()) {
            User user2 =userMapper.findUserByUserName(username);
            user2.getAttendgendas().remove(agenda);
            userMapper.addUser(user2);
        }
        logger.debug("更新创建用户信息");
        User user2 =userMapper.findUserByUserName(agenda.getInitiatorName());
        user2.getCreatedgendas().remove(agenda);
        userMapper.addUser(user2);
        return true;
    }

    @Override
    /**
     * @description 用户拒绝参加一个会议（取消这个参与的这个会议）
     * @method  rejectAgenda
     * @param agenda
     * @return 
     * @date: 2017年10月01日 14:36:35
     * @author:刘烨
     */
    public boolean rejectAgenda(Agenda agenda,User user) {

        agenda.getParticipators().remove(user.getUsername());
        List<String> usernames= agenda.getParticipators();
        if(usernames.isEmpty()){
            logger.debug("如果没人了就删除创建的会议");
            agendaMapper.delete(agenda.getTitle());
            logger.debug("会议创建,删除该会议");
            User user2=userMapper.findUserByUserName(agenda.getInitiatorName());
            user.getCreatedgendas().remove(agenda);
            userMapper.addUser(user2);
        }else {
            agendaMapper.add(agenda);
        }
        logger.debug("当前用户删除该会议");
        user= userMapper.findUserByUserName(user.getUsername());
        user.getAttendgendas().remove(agenda);
        userMapper.addUser(user);
        return true;
    }

    @Override
    public boolean clearAllAgendaByInitor(User user) {
        logger.debug("获取所有的会议");
        for (Agenda agenda :
                user.getCreatedgendas()) {
            this.cancelAgendaByInitor(agenda);
        }
        return true;
    }

    @Override
    public Agenda getAgenda(Agenda agenda) {
        return agendaMapper.findAgendaByTittle(agenda.getTitle());
    }

    @Override
    public List<Agenda> queryAll(Date start, Date end, User user) {
        List<Agenda> agendaList1=new ArrayList<>();
        List<Agenda> agendaList2=new ArrayList<>();
        List<Agenda> createdgendas = user.getCreatedgendas();
        List<Agenda> attendgendas = user.getAttendgendas();
        logger.debug("先找创建的");
        for (Agenda agenda:
             createdgendas) {
            if(isContent(start,end,agenda.getStarttime()) || isContent(start,end,agenda.getEndtime())){
                agendaList1.add(agenda);
            }
        }
        for (Agenda agenda:
                attendgendas) {
            if(isContent(start,end,agenda.getStarttime()) || isContent(start,end,agenda.getEndtime())){
                agendaList2.add(agenda);
            }
        }
        agendaList1.removeAll(agendaList2);
        agendaList1.addAll(agendaList2);
        return agendaList1;
    }
    /**
     * @description 判断是否在区间内
     * @method  isContent
     * @param start
     * @param end
     * @param starttime
     * @param endtime
     * @return
     * @date: 2017年10月01日 17:42:21
     * @author:刘烨
     */
    private boolean isContent(Date start, Date end, Date time) {

        if(time.getTime()>=start.getTime() && time.getTime()<=end.getTime()){
            return true;
        }
        return false;
    }


    /**
     * @description usernamen 能否参加Agenda
     * @method isAttend
     * @param user
     * @return true能参加，false不能参加
     * @date: 2017年09月30日 15:22:14
     * @author:刘烨
     */
    private boolean isAttend(User user,Agenda agenda) {

        if(user.getAttendgendas()==null) return true;
            for (Agenda agendal :
                    user.getAttendgendas()){

                if(isInner(agendal.getStarttime().getTime(),agenda.getStarttime().getTime(),agenda.getEndtime().getTime())){
                    return false;
                }
                if(isInner(agendal.getEndtime().getTime(),agenda.getStarttime().getTime(),agenda.getEndtime().getTime())){
                    return false;
                }
            }
        return true;
    }

    private boolean isInner(long time, long time1, long endtime) {

        if(time>=time1 && time<=endtime){
            return true;
        }else {
            return false;
        }
    }
}
