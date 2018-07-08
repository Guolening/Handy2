package com.example.handy.Model;

/**
 * Created by t520win764zy on 2017/12/14.
 */

public class Schedule {
    //private int no;
    private String name,build,room,week;
    private int jiecistart,jieciend;

    public Schedule(String name, String build,String room, int jiecistart,int jieciend,String week) {
        super();
        //this.no=no;
        this.name = name;
        this.build = build;
        this.room = room;
        this.jiecistart = jiecistart;
        this.jieciend = jieciend;
        this.week = week;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBuild() {
        return build;
    }
    public void setBuild(String build) {
        this.build = build;
    }

    public String getRoom() {
        return room;
    }
    public void setRoom(String room) {
        this.room = room;
    }

    public int getJiecistart() {
        return jiecistart;
    }

    public void setJiecistart(int jiecistart) {
        this.jiecistart = jiecistart;
    }

    public int getJieciend() {
        return jieciend;
    }

    public void setJieciend(int jieciend) {
        this.jieciend = jieciend;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }


}
