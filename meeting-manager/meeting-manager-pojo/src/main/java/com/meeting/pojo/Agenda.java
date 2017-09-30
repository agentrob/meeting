package com.meeting.pojo;

import java.util.Date;
import java.util.List;

/**
 * User: FlowingFire
 * Date: 2017/9/30 0030
 * Time: 11:47
 * Description:
 */
public class Agenda {

    private String title;
    //存储用户名
    private List<String> participators;
    private Date starttime;
    private Date endtime;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getParticipators() {
        return participators;
    }

    public void setParticipators(List<String> participators) {
        this.participators = participators;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
}
