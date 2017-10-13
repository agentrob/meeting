package com.meeting.app;

import com.meeting.command.AgendaCommand;
import com.meeting.command.UserCommand;
import com.meeting.pojo.User;
import com.meeting.utils.HelpPrint;
import com.meeting.utils.JSonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * User: FlowingFire
 * Date: 2017/9/21 0021
 * Time: 22:28
 * Description:
 */
public class AppMain {
    private final static Logger logger = LoggerFactory.getLogger(AppMain.class);
    public static void main(String[] args) {
        //d25252
        UserCommand userCommand=new UserCommand();
        AgendaCommand agendaCommand=new AgendaCommand();
        //用来表示是否登陆，及显示当前用户信息1
        User user=null;
        logger.debug("程序开始执行！");
        Scanner scanner = new Scanner(System.in);
        while(true){
            String s = scanner.nextLine();
            if("end".equals(s)){
                break;
            }else{
                if(s!=null){
                    String[] arg = s.split(" ");
                    switch (arg[0]){
                        case "login":
                           user=userCommand.loginOptions(arg);
                           //System.out.println(user);
                           break;
                        case "register":
                            userCommand.registerOptions(arg);
                            break;
                        case "findAllUsers":
                            userCommand.findAllUsersOptions(arg);
                            break;

                        case "lgout":
                            userCommand.lgout(arg);
                            break;
                        case "deleteMe":
                            System.out.println("输入y确定");
                            String s1 = scanner.nextLine();
                            if ("y".equals(s1)) {
                                userCommand.deleteMe(arg);
                            }
                            break;
                        case "createAgenda":
                            agendaCommand.createAgenda(arg);
                            break;
                        case "invite":
                            agendaCommand.invite(arg);
                            break;
                        case "deletePar":
                            agendaCommand.deletePar(arg);
                            break;

                        case "query":
                            agendaCommand.query(arg);
                            break;
                        case "cancelOne":
                            agendaCommand.cancelOne(arg);
                            break;
                        case "rejectOne":
                            agendaCommand.rejectOne(arg);
                            break;
                        case "clearAllAgenda":
                            System.out.println("输入y确定");
                            String s2 = scanner.nextLine();
                            if ("y".equals(s2)){
                                agendaCommand.clearAllAgenda(arg);
                            }
                            break;
                        default:
                            HelpPrint.print();
                            break;
                    }
                }






            }
        }
    }
}
