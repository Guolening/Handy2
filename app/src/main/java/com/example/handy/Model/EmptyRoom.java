package com.example.handy.Model;

/**
 * Created by t520win764zy on 2017/12/9.
 */

public class EmptyRoom {
    private int no;
    private String build;
    private String room;
    private String week;
    private String jieci;
    private String nsit;

    public EmptyRoom(int no, String build, String room, String week, String jieci, String nsit)
    {
        this.no=no;
        this.build=build;
        this.room=room;
        this.week=week;
        this.jieci=jieci;
        this.nsit=nsit;
    }
    public int getNo() {
        return no;
    }
    public String getBuild() {return build;}
    public String getRoom() {return room;}
    public String getWeek() {return week;}
    public String getJieci() {return jieci;}
    public String getNsit() {return nsit;}
}