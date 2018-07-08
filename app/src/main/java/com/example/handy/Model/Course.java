package com.example.handy.Model;

/**
 * Created by t520win764zy on 2017/12/9.
 */

public class Course {
    private int no;
    private String name;
    private String teacher;
    private String build;
    private String room;
    private String week;
    private String jieci;

    public Course(int no, String name, String teacher, String build, String room,String week,String jieci){
        this.no=no;
        this.name=name;
        this.teacher=teacher;
        this.build=build;
        this.room=room;
        this.week=week;
        this.jieci=jieci;
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getName() {return name;}
    public void setName(String name) {
        this.name = name;
    }
    public String getTeacher() {return teacher;}
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public String getBuild() {return build;}
    public void setBuild(String build) {
        this.build = build;
    }
    public String getRooom() {return room;}
    public void setRooom(String room) {
        this.room = room;
    }
    public String getWeek(){ return week;}
    public void setWeek(String week) {
        this.week = week;
    }
    public String getJieci() {return jieci;}
    public void setJieci(String jieci) {
        this.jieci = jieci;
    }
}
