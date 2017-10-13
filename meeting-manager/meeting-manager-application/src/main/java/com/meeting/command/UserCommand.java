package com.meeting.command;
import com.meeting.controller.UserController;
import com.meeting.pojo.User;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * User: FlowingFire
 * Date: 2017/9/21 0021
 * Time: 22:28
 * Description:
 */
public class UserCommand {
    UserController userController=new UserController();

    public  User loginOptions(String[] args) {
        String username = null;
        String password = null;
        User user=null;
        Options options = new Options();
        Option opt = new Option("h", "help", false, "Print help");
        opt.setRequired(false);
        options.addOption(opt);
        opt = new Option("u", "username", true, "username");
        opt.setRequired(true);
        options.addOption(opt);


        opt = new Option("p", "password", true, "password");
        opt.setRequired(true);
        options.addOption(opt);


        HelpFormatter hf = new HelpFormatter();
        hf.setWidth(110);
        CommandLine commandLine = null;
        CommandLineParser parser = new PosixParser();

        try {
            commandLine = parser.parse(options, args);
            if (commandLine.hasOption('h')) {
                // 打印使用帮助
                hf.printHelp("testApp", options, true);
            }

            // 打印opts的名称和值
            System.out.println("--------------------------------------");
            Option[] opts = commandLine.getOptions();
            if (opts != null) {
                for (Option opt1 : opts) {
                   /* String name = opt1.getLongOpt();
                    String value = commandLine.getOptionValue(name);
                    System.out.println(name + "=>" + value);*/
                    if ("username".equalsIgnoreCase(opt1.getLongOpt())) {
                        username = commandLine.getOptionValue(opt1.getLongOpt());
                    }
                    if ("password".equalsIgnoreCase(opt1.getLongOpt())) {
                        password = commandLine.getOptionValue(opt1.getLongOpt());
                    }
                }
            }
            //调用UserController的方法去登陆。
            user=userController.login(username,password);
            if(user!=null){
                System.out.println("登陆成功！");
            }else{
                System.out.println("用户名或密码错误！");
            }
        } catch (ParseException e) {
            hf.printHelp("testApp", options, true);
        }
        return user;
    }


    public  void registerOptions(String[] args) {
        User user = new User();


        Options options = new Options();
        Option opt = new Option("h", "help", false, "Print help");
        opt.setRequired(false);
        options.addOption(opt);
        opt = new Option("u", "username", true, "username");
        opt.setRequired(true);
        options.addOption(opt);


        opt = new Option("p", "password", true, "password");
        opt.setRequired(true);
        options.addOption(opt);

        opt = new Option("e", "email", true, "email");
        opt.setRequired(true);
        options.addOption(opt);

        opt = new Option("pn", "phone", true, "phone");
        opt.setRequired(true);
        options.addOption(opt);





        HelpFormatter hf = new HelpFormatter();
        hf.setWidth(110);
        CommandLine commandLine = null;
        CommandLineParser parser = new PosixParser();
        try {
            commandLine = parser.parse(options, args);
            if (commandLine.hasOption('h')) {
                // 打印使用帮助
                hf.printHelp("testApp", options, true);
            }

            // 打印opts的名称和值
            System.out.println("--------------------------------------");
            Option[] opts = commandLine.getOptions();
            if (opts != null) {
                for (Option opt1 : opts) {
                   /* String name = opt1.getLongOpt();
                    String value = commandLine.getOptionValue(name);
                    System.out.println(name + "=>" + value);*/
                    if("username".equalsIgnoreCase(opt1.getLongOpt())){
                        user.setUsername(commandLine.getOptionValue(opt1.getLongOpt()));
                    }
                    if("password".equalsIgnoreCase(opt1.getLongOpt())){
                        user.setPassword(commandLine.getOptionValue(opt1.getLongOpt()));
                    }
                    if("email".equalsIgnoreCase(opt1.getLongOpt())){
                        user.setEmail(commandLine.getOptionValue(opt1.getLongOpt()));
                    }
                    if("phone".equalsIgnoreCase(opt1.getLongOpt())){
                        user.setPhone(commandLine.getOptionValue(opt1.getLongOpt()));
                    }
                }
            }
            //将获得的参数传到UserContorllor去注册。
            boolean register = userController.register(user);

            if(register){
                System.out.println("注册成功请去登陆");
            }else {
                System.out.println("用户名重复");
            }
        }
        catch (ParseException e) {
            hf.printHelp("testApp", options, true);
        }
    }

    public void findAllUsersOptions(String[] arg) {
        userController.findAllUsers();
    }

    public void lgout(String[] arg) {
        userController.logout();
    }

    public void deleteMe(String[] arg) {
        userController.deleteUser();
    }
}
