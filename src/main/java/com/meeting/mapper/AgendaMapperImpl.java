package com.meeting.mapper;

import com.meeting.pojo.Agenda;
import com.meeting.utils.JSonUtil;

/**
 * User: FlowingFire
 * Date: 2017/9/30 0030
 * Time: 14:59
 * Description:
 *
 *test
 */
public class AgendaMapperImpl implements AgendaMapper {
    @Override
    public boolean add(Agenda agenda) {
       boolean flag= JSonUtil.saveAgenda(agenda);
       return flag;
    }

    @Override
    public Agenda findAgendaByTittle(String title) {
        Agenda agenda=JSonUtil.loadAgenda(title);
        return agenda;
    }

    @Override
    public void delete(String title) {
        JSonUtil.deleteAgenda(title);
    }
}
