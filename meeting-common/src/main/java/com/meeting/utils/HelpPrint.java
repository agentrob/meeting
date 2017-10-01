package com.meeting.utils;

/**
 * User: FlowingFire
 * Date: 2017/10/1 0001
 * Time: 19:50
 * Description:
 */
public class HelpPrint {
    public static void print(){
        System.out.printf("%-20s","命令");
        System.out.printf("%-20s","说明");
        System.out.println();
        System.out.printf("%-20s","login");
        System.out.printf("%-20s","用户必须登陆才能操作");
        System.out.println();
        System.out.printf("%-20s","register");
        System.out.printf("%-20s","注册用户");
        System.out.println();
        System.out.printf("%-20s","findAllUsers");
        System.out.printf("%-20s","查询所有用户信息");
        System.out.println();
        System.out.printf("%-20s","lgout");
        System.out.printf("%-20s","注销当前用户");
        System.out.println();
        System.out.printf("%-20s","deleteMe");
        System.out.printf("%-20s","删除当前登陆的用户，包括其参与与创建的会议");
        System.out.println();
        System.out.printf("%-20s","createAgenda");
        System.out.printf("%-20s","创建一个会议");
        System.out.println();
        System.out.printf("%-20s","invite");
        System.out.printf("%-20s","邀请几个人参加会议，每次邀请不超过10人");
        System.out.println();
        System.out.printf("%-20s","deletePar");
        System.out.printf("%-20s","赶走一个参会人员");
        System.out.println();
        System.out.printf("%-20s","query");
        System.out.printf("%-20s","查询一个时间段的所有会议");
        System.out.println();
        System.out.printf("%-20s","cancelOne");
        System.out.printf("%-20s","当前登陆用户，删除自己创建的一个会议");
        System.out.println();
        System.out.printf("%-20s","rejectOne");
        System.out.printf("%-20s","当前用户取消掉一个参与的会议");
        System.out.println();
        System.out.printf("%-20s","clearAllAgenda");
        System.out.printf("%-20s","登陆的用户删除自己创建的所有会议");
        System.out.println();
    }
}
