package com.meeting.controller;


import com.meeting.pojo.Agenda;
import com.meeting.pojo.User;
import com.meeting.utils.Login;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * User: FlowingFire
 * Date: 2017/9/30 0030
 * Time: 15:29
 * Description:
 */
public class AgendaControllerTest {


    AgendaController agendaController =new AgendaController();
    DateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Test
    public void createAgenda() throws Exception {

        Agenda agenda=new Agenda();
        agenda.setStarttime(df.parse("2004-10-01 23:21:31"));
        agenda.setEndtime(df.parse("2004-10-31 23:22:12"));
        agenda.setInitiatorName(Login.getUser().getUsername());
        agenda.setTitle("人大2");
        List<String> users =new ArrayList<>();
        users.add("jjjj1");
        users.add("jjjj2");
        agenda.setParticipators(users);
        agenda.setInitiatorName("jjjj0");
        agendaController.createAgenda(agenda);

    }

    @Test
    public void inviteUser() throws Exception {
        String[] users=new String[]{"jjjj3"};
        Agenda agenda = new Agenda();
        agenda.setTitle("毕业");
        agendaController.inviteUser(users,agenda);
    }

    @Test
    public void deleteUser() throws Exception {
        String[] users=new String[]{"jjjj1"};
        Agenda agenda = new Agenda();
        agenda.setTitle("开学典礼");
        agendaController.deleteUser(users,agenda);
    }

    @Test
    public void cancelAgendaByInitor() throws Exception {
        Agenda agenda = new Agenda();
        agenda.setTitle("开学典礼");
        agendaController.cancelAgendaByInitor(agenda);
    }

    @Test
    public void rejectAgenda() throws Exception {
        Agenda agenda = new Agenda();
        agenda.setTitle("人大1");
        agendaController.rejectAgenda(agenda);
    }

    @Test
    public void clearAllAgendaByInitor() throws Exception {
        agendaController.clearAllAgendaByInitor();
    }

    @Test
    public void queryAll() throws Exception {
        Date s = df.parse("1999-12-25 12:50");
        Date e = df.parse("2050-12-25 12:50");
        agendaController.queryAll(s,e);

    }




}
