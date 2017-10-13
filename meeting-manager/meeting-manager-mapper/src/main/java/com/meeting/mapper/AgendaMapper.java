package com.meeting.mapper;

import com.meeting.pojo.Agenda; /**
 * User: FlowingFire
 * Date: 2017/9/30 0030
 * Time: 14:59
 * Description:
 */
public interface AgendaMapper {
    boolean add(Agenda agenda);

    Agenda findAgendaByTittle(String title);

    void delete(String title);
}
