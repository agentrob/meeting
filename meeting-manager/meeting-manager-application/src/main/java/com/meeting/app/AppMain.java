package com.meeting.app;

import com.meeting.command.UserCommand;
import com.meeting.pojo.User;

import java.util.Scanner;

/**
 * User: FlowingFire
 * Date: 2017/9/21 0021
 * Time: 22:28
 * Description:
 */
public class AppMain {
    public static void main(String[] args) {
        //d
        UserCommand userCommand=new UserCommand();
        //用来表示是否登陆，及显示当前用户信息
        User user=null;
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
                        default:
                            System.out.println("打印所有指令帮助");
                            break;
                    }
                }






            }
        }
    }
}
