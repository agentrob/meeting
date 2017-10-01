package com.meeting.service;

import com.meeting.pojo.Agenda;
import com.meeting.pojo.User;

import java.util.Date;
import java.util.List;

/**
 * User: FlowingFire
 * Date: 2017/9/30 0030
 * Time: 14:52
 * Description:
 */
public interface AgendaService {
    public boolean createAgenda(Agenda agenda);

    public  boolean inviteUser(String[] users, Agenda agenda);

    public boolean deleteUser(String[] users, Agenda agenda);

    void deleteAgenda(Agenda agenda);

    void deleteAgendaByInitor(Agenda agenda, User user);

    boolean cancelAgendaByInitor(Agenda agenda);

    boolean rejectAgenda(Agenda agenda,User user);

    boolean clearAllAgendaByInitor(User user);

    Agenda getAgenda(Agenda agenda);

    List<Agenda> queryAll(Date start, Date end, User user);
}
