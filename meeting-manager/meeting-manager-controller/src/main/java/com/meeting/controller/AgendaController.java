package com.meeting.controller;
// 导入slf4j类

import com.meeting.pojo.Agenda;
import com.meeting.pojo.User;
import com.meeting.service.AgendaService;
import com.meeting.service.AgendaServiceImpl;
import com.meeting.service.UserService;
import com.meeting.serviceimpl.UserServiceImpl;
import com.meeting.utils.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * User: FlowingFire
 * Date: 2017/9/21 0021
 * Time: 23:19
 * Description:
 */
public class AgendaController {
    private final static Logger logger = LoggerFactory.getLogger(AgendaController.class);

    UserService userService =new UserServiceImpl();
    AgendaService agendaService =new AgendaServiceImpl();

DateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public void createAgenda(Agenda agenda){

        if(Login.isLogin()) {
            boolean flag=agendaService.createAgenda(agenda);
            if(flag){
                System.out.println("创建会议成功");
            }else {
                System.out.println("创建会议失败");
            }
        }else {
            System.out.println("请登录！");
        }
    }

    public void inviteUser(String[] users,Agenda agenda){
        agenda=agendaService.getAgenda(agenda);
            if(Login.isLogin()) {
                if(agenda==null) System.out.println("没这个会议");
                if(agenda.getInitiatorName().equals(Login.getUser().getUsername())) {
                    boolean flag = agendaService.inviteUser(users, agenda);
                    if (flag) {
                        System.out.println("邀请全部成功");
                    } else {
                        System.out.println("邀请没全部成功");
                    }
                }else {
                    System.out.println("这不是您发起的会议！不能添加用户");
                }
            }else {
                System.out.println("请登录！");
            }
    }
    public void deleteUser(String[] users,Agenda agenda){
        agenda=agendaService.getAgenda(agenda);

        if(Login.isLogin()) {
            if(agenda==null) {
                System.out.println("没这个会议");
                return;
            }
            if(agenda.getInitiatorName().equals(Login.getUser().getUsername())) {
                    boolean flag = agendaService.deleteUser(users, agenda);
                    if (flag) {
                        System.out.println("全部成功");
                    } else {
                        System.out.println("没全部成功");
                    }
            }else {
                System.out.println("这不是您发起的会议！不能删除用户");
            }
        }else {
            System.out.println("请登录！");
        }
    }

    /**
     * @description 取消发起的一个会议
     * @method  cancelAgendaByInitor
     * @param agenda
     * @return
     * @date: 2017年10月01日 14:32:06
     * @author:刘烨
     */
    public void cancelAgendaByInitor(Agenda agenda){
        agenda=agendaService.getAgenda(agenda);
        if(Login.isLogin()) {
            if(agenda==null) {
                System.out.println("没这个会议");
                return;
            }
            if(agenda.getInitiatorName().equals(Login.getUser().getUsername())) {
                boolean flag = agendaService.cancelAgendaByInitor(agenda);
                if (flag) {
                    System.out.println("全部成功");
                } else {
                    System.out.println("没全部成功");
                }
            }else {
                System.out.println("这不是您发起的会议！不能取消");
            }
        }else {
            System.out.println("请登录！");
        }
    }
    /**
     * @description 拒绝参加一个会议
     * @method  rejectAgenda
     * @param agenda
     * @return
     * @date: 2017年10月01日 14:33:36
     * @author:刘烨
     */
    public void rejectAgenda(Agenda agenda){
        agenda=agendaService.getAgenda(agenda);
        if(Login.isLogin()) {
            if(agenda.getParticipators().contains(Login.getUser().getUsername())) {
                boolean flag = agendaService.rejectAgenda(agenda,Login.getUser());
                if (flag) {
                    System.out.println("全部成功");
                } else {
                    System.out.println("没全部成功");
                }
            }else {
                System.out.println("这不是您参加的会议！不用拒绝了");
            }
        }else {
            System.out.println("请登录！");
        }
    }
    /**
     * @description 清除登陆用户创建的所有会议
     * @method
     * @return
     * @date: 2017年10月01日 14:45:40
     * @author:刘烨
     */
    public void clearAllAgendaByInitor(){

        if(Login.isLogin()) {
           User user=userService.findUserByUserName(Login.getUser().getUsername());
            if (user.getCreatedgendas()!=null) {
                boolean flag = agendaService.clearAllAgendaByInitor(user);
                if (flag) {
                    System.out.println("全部成功");
                } else {
                    System.out.println("没全部成功");
                }
            }else {
                System.out.println("您没有创建的会议！");
            }
        }else {
            System.out.println("请登录！");
        }
    }



    /**
     * @description 查询用户所有的会议包括参与与发起
     * @method  queryAll
     * @param start 开始时间
     * @param end   结束时间
     * @return
     * @date: 2017年10月01日 17:17:00
     * @author:刘烨
     */
    public void queryAll(Date start,Date end){
        if(Login.isLogin()) {
            User user=userService.updateUserByUserName(Login.getUser().getUsername());

            List<Agenda> list=agendaService.queryAll(start,end,user);
            System.out.println("------会议列表---------");


            System.out.println();
            System.out.printf("|%-20s|","起始时间");
            System.out.printf("|%-20s|","终止时间");
            System.out.printf("|%-12s|","主题");
            System.out.printf("|%-12s|","发起者");
            System.out.printf("|%-12s|","参与者");
            System.out.println("");
            for (Agenda agenda:
                    list) {
                String st =String.format("%1$tY-%1$tm-%1$td %1$tT", agenda.getStarttime());
                String et =String.format("%1$tY-%1$tm-%1$td %1$tT",agenda.getEndtime());
                System.out.printf("|%-23s|",st);
                System.out.printf("|%-24s|",et);
                System.out.printf("|%-12s|",agenda.getTitle());
                System.out.printf("|%-15s|",agenda.getInitiatorName());
                for (String username:
                     agenda.getParticipators()) {
                    System.out.printf("|%-8s",username);
                }

                System.out.println();
            }
        }else {
            System.out.println("请登录！");
        }
    }
}
