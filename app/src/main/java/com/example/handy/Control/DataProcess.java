package com.example.handy.Control;

import com.example.handy.Model.Schedule;
import com.example.handy.Model.ScheduleList;

/**
 * Created by t520win764zy on 2017/12/14.
 */

public class DataProcess {
    public ScheduleList search(){//查询数据
        ScheduleList scheduleList=ScheduleList.getInstance();
        return scheduleList;
    }
    public boolean insert(Schedule schedule){//插入数据
        ScheduleList scheduleList=ScheduleList.getInstance();
        int i;
        for (i = 0; i <scheduleList.size() ; i++) {
            Schedule c=scheduleList.get(i);
        }
        if (i == scheduleList.size()) {
            scheduleList.add(schedule);
            return true;
        }
        return  false;
    }
}
