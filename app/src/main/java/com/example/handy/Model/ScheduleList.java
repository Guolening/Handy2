package com.example.handy.Model;

import java.util.ArrayList;

/**
 * Created by t520win764zy on 2017/12/14.
 */

public class ScheduleList extends ArrayList<Schedule> {
    private static ScheduleList ourInstance = new ScheduleList();//对数组进行实例化

    public static ScheduleList getInstance() {
        return ourInstance;
    }//定义getInstance函数，得到Schedule数组类型的数组ourInstance
}
