package com.meeting.command;

import com.meeting.controller.AgendaController;
import com.meeting.controller.UserController;
import com.meeting.pojo.Agenda;
import com.meeting.pojo.User;
import org.apache.commons.cli.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * User: FlowingFire
 * Date: 2017/9/21 0021
 * Time: 22:28
 * Description:
 */
public class AgendaCommand {
    UserController userController=new UserController();
    AgendaController agendaController=new AgendaController();
    DateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm");
    String[] participators={};
    public  void createAgenda(String[] args) {
        Agenda agenda = new Agenda();


        Options options = new Options();
        Option opt = new Option("h", "help", false, "Print help");
        opt.setRequired(false);
        options.addOption(opt);
        opt = new Option("t", "title", true, "title");
        opt.setRequired(true);
        options.addOption(opt);


        opt = new Option("s", "startTime", true, "yyyy-MM-dd HH:mm");
        opt.setRequired(true);
        opt.setArgs(2);
        options.addOption(opt);

        opt = new Option("e", "endTime", true, "yyyy-MM-dd HH:mm");
        opt.setRequired(true);
        opt.setArgs(2);
        options.addOption(opt);

        opt = new Option("p", "participator", true, "participator最多接受10个人");
        opt.setRequired(true);
        opt.setArgs(10);
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
                    if("title".equalsIgnoreCase(opt1.getLongOpt())){
                       agenda.setTitle(commandLine.getOptionValue(opt1.getLongOpt()));
                    }
                    if("startTime".equalsIgnoreCase(opt1.getLongOpt())){
                        //user.setPassword(commandLine.getOptionValue(opt1.getLongOpt()));
                        String date=opt1.getValue(0)+" "+opt1.getValue(1);
                        Date parse = df.parse(date);
                        agenda.setStarttime(parse);
                    }
                    if("endTime".equalsIgnoreCase(opt1.getLongOpt())){
                        //user.setEmail(commandLine.getOptionValue(opt1.getLongOpt()));
                        String date=opt1.getValue(0)+" "+opt1.getValue(1);
                        Date parse = df.parse(date);
                        agenda.setEndtime(parse);
                    }
                    if("participator".equalsIgnoreCase(opt1.getLongOpt())){
                        List<String> usernames = Arrays.asList(opt1.getValues());
                        agenda.setParticipators(usernames);
                        // user.setPhone(commandLine.getOptionValue(opt1.getLongOpt()));
                    }
                }
            }
            //将获得的参数传到UserContorllor去注册。
            agendaController.createAgenda(agenda);

        }
        catch (ParseException e) {
            hf.printHelp("testApp", options, true);
        } catch (java.text.ParseException e) {
            hf.printHelp("testApp", options, true);
        }
    }




    public void invite(String[] args) {
        Agenda agenda = new Agenda();


        Options options = new Options();
        Option opt = new Option("h", "help", false, "Print help");
        opt.setRequired(false);
        options.addOption(opt);




        opt = new Option("p", "participators", true, "participators最多接受10个人");
        opt.setRequired(true);
        opt.setArgs(10);
        options.addOption(opt);

        opt = new Option("t", "title", true, "指定邀请参加的会议");
        opt.setRequired(true);
        opt.setArgs(10);
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
                    if("title".equalsIgnoreCase(opt1.getLongOpt())){
                        agenda.setTitle(commandLine.getOptionValue(opt1.getLongOpt()));
                    }

                    if("participators".equalsIgnoreCase(opt1.getLongOpt())){
                        participators=opt1.getValues();

                        // user.setPhone(commandLine.getOptionValue(opt1.getLongOpt()));
                    }
                }
            }
            //将获得的参数传到UserContorllor去注册。
            agendaController.inviteUser(participators,agenda);

        }
        catch (ParseException e) {
            hf.printHelp("testApp", options, true);
        }

    }

    public void deletePar(String[] args) {
        Agenda agenda = new Agenda();


        Options options = new Options();
        Option opt = new Option("h", "help", false, "Print help");
        opt.setRequired(false);
        options.addOption(opt);




        opt = new Option("p", "participators", true, "participators最多接受10个人");
        opt.setRequired(true);
        opt.setArgs(10);
        options.addOption(opt);

        opt = new Option("t", "title", true, "指定一个会议");
        opt.setRequired(true);
        opt.setArgs(10);
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
                    if("title".equalsIgnoreCase(opt1.getLongOpt())){
                        agenda.setTitle(commandLine.getOptionValue(opt1.getLongOpt()));
                    }

                    if("participators".equalsIgnoreCase(opt1.getLongOpt())){
                        participators=opt1.getValues();

                        // user.setPhone(commandLine.getOptionValue(opt1.getLongOpt()));
                    }
                }
            }
            //将获得的参数传到UserContorllor去注册。
            agendaController.deleteUser(participators,agenda);

        }
        catch (ParseException e) {
            hf.printHelp("testApp", options, true);
        }

    }

    public void query(String[] args) {
        Agenda agenda = new Agenda();


        Options options = new Options();
        Option opt = new Option("h", "help", false, "Print help");
        opt.setRequired(false);
        options.addOption(opt);



        opt = new Option("s", "startTime", true, "yyyy-MM-dd HH:mm");
        opt.setRequired(true);
        opt.setArgs(2);
        options.addOption(opt);

        opt = new Option("e", "endTime", true, "yyyy-MM-dd HH:mm");
        opt.setRequired(true);
        opt.setArgs(2);
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

                    if("startTime".equalsIgnoreCase(opt1.getLongOpt())){
                        //user.setPassword(commandLine.getOptionValue(opt1.getLongOpt()));
                        String date=opt1.getValue(0)+" "+opt1.getValue(1);
                        Date parse = df.parse(date);
                        agenda.setStarttime(parse);
                    }
                    if("endTime".equalsIgnoreCase(opt1.getLongOpt())){
                        //user.setEmail(commandLine.getOptionValue(opt1.getLongOpt()));
                        String date=opt1.getValue(0)+" "+opt1.getValue(1);
                        Date parse = df.parse(date);
                        agenda.setEndtime(parse);
                    }

                }
            }
            //将获得的参数传到UserContorllor去注册。
            agendaController.queryAll(agenda.getStarttime(),agenda.getEndtime());

        }
        catch (ParseException e) {
            hf.printHelp("testApp", options, true);
        } catch (java.text.ParseException e) {
            hf.printHelp("testApp", options, true);
        }
    }

    public void cancelOne(String[] args) {
        Agenda agenda = new Agenda();

        Options options = new Options();
        Option opt = new Option("h", "help", false, "Print help");
        opt.setRequired(false);
        options.addOption(opt);
        opt = new Option("t", "title", true, "title");
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
                    if("title".equalsIgnoreCase(opt1.getLongOpt())){
                        agenda.setTitle(commandLine.getOptionValue(opt1.getLongOpt()));
                    }



                }
            }
            //将获得的参数传到UserContorllor去注册。
            agendaController.cancelAgendaByInitor(agenda);

        }
        catch (ParseException e) {
            hf.printHelp("testApp", options, true);
        }

    }

    public void rejectOne(String[] args) {
        Agenda agenda = new Agenda();

        Options options = new Options();
        Option opt = new Option("h", "help", false, "Print help");
        opt.setRequired(false);
        options.addOption(opt);
        opt = new Option("t", "title", true, "title");
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
                    if("title".equalsIgnoreCase(opt1.getLongOpt())){
                        agenda.setTitle(commandLine.getOptionValue(opt1.getLongOpt()));
                    }
                }
            }
            //将获得的参数传到UserContorllor去注册。
            agendaController.rejectAgenda(agenda);
        }
        catch (ParseException e) {
            hf.printHelp("testApp", options, true);
        }
    }

    public void clearAllAgenda(String[] args) {
        agendaController.clearAllAgendaByInitor();
    }
}
