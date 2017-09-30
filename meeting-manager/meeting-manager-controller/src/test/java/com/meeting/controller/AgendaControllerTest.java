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
    DateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Test
    public void createAgenda() throws Exception {

        Agenda agenda=new Agenda();
        agenda.setStarttime(df.parse("2031-10-01 23:21:31"));
        agenda.setEndtime(df.parse("2031-10-31 23:22:12"));
        agenda.setInitiatorName(Login.getUser().getUsername());
        agenda.setTitle("开学典礼");
        List<String> users =new ArrayList<>();
        users.add("jjjj");
        users.add("jjjj0");
        users.add("jjjj1");
        agenda.setParticipators(users);
        agenda.setInitiatorName("jjjj");
        agendaController.createAgenda(agenda);

    }



}
