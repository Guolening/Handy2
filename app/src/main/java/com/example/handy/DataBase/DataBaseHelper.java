package com.example.handy.DataBase;

/**
 * Created by t520win764zy on 2017/12/12.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DataBaseHelper extends SQLiteOpenHelper {//继承自一个现有的封装好的帮助类，帮助建立数据库
    public static final String CREATE_EMPTY = "create table Empty("
            + "no integer primary key autoincrement,"//autoincrement代表自增
            + "build text,"
            + "room text,"
            + "week text,"
            + "jieci text,"
            + "nsit text)";

    //两条创建表的SQL语句

    public static final String CREATE_COURSE = "create table Course("
            + "no integer primary key autoincrement,"
            + "name text,"
            + "teacher text,"
            + "build text,"
            + "week text,"
            + "room text,"
            + "jieci text)";

    private Context mContext;//全局变量，上下文

    public DataBaseHelper(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version) {
        //构造函数，声明类的对象之后可以调用这个函数帮助创建数据库
        super(context, dbname, factory, version);
        mContext = context;
    }
    @Override//如果需要建库会自动调用oncreate，还可以顺便建表
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EMPTY);
        db.execSQL(CREATE_COURSE);
    }
    @Override//更新表 若存在则删除，然后再创建新的以升级
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Empty");
        db.execSQL("drop table if exists Course");
        onCreate(db);
    }

    //插入空教室数据  注意前后属性数量类型保持一致
    public void insert_empty(SQLiteDatabase db) {
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "218", "一", "01-02", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "218", "一", "03-04", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "218", "一", "05-06", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "218", "一", "07-08", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "218", "一", "09-10", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "218", "一", "11-12", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "218", "二", "05-06", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "218", "二", "07-08", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "218", "三", "05-06", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "218", "三", "07-08", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "218", "三", "09-10", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "218", "三", "11-12", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "218", "四", "01-02", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "218", "四", "03-04", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "218", "五", "05-06", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "218", "五", "07-08", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "228", "一", "01-02", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "228", "一", "03-04", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "228", "二", "07-08", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "228", "三", "09-10", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "228", "三", "11-12", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "228", "四", "03-04", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "401", "一", "01-02", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "401", "二", "07-08", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "401", "三", "09-10", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "401", "三", "11-12", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "401", "四", "01-02", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "401", "五", "05-06", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"一教", "401", "五", "07-08", "40"});

        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"三教", "101", "一", "01-02", "80"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"三教", "101", "三", "09-10", "80"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"三教", "101", "三", "11-12", "80"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"三教", "101", "五", "05-06", "80"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"三教", "101", "五", "07-08", "80"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"三教", "109", "五", "09-10", "200"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"三教", "109", "五", "11-12", "200"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"三教", "109", "六", "01-02", "200"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"三教", "109", "六", "03-04", "200"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"三教", "109", "六", "05-06", "200"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"三教", "109", "六", "07-08", "200"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"三教", "109", "六", "09-10", "200"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"三教", "109", "六", "11-12", "200"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"三教", "530", "一", "01-02", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"三教", "530", "二", "07-08", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"三教", "530", "三", "09-10", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"三教", "530", "三", "11-12", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"三教", "530", "四", "01-02", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"三教", "530", "四", "03-04", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"三教", "530", "五", "05-06", "40"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"三教", "530", "五", "07-08", "40"});

        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"材料楼", "313", "一", "01-02", "100"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"材料楼", "313", "一", "03-04", "100"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"材料楼", "313", "二", "05-06", "100"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"材料楼", "313", "二", "07-08", "100"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"材料楼", "313", "三", "09-10", "100"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"材料楼", "313", "三", "11-12", "100"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"材料楼", "313", "四", "01-02", "100"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"材料楼", "313", "四", "03-04", "100"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"材料楼", "313", "五", "05-06", "100"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"材料楼", "313", "五", "07-08", "100"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"材料楼", "415", "一", "01-02", "100"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"材料楼", "415", "一", "03-04", "100"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"材料楼", "415", "二", "05-06", "100"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"材料楼", "415", "二", "07-08", "100"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"材料楼", "415", "三", "09-10", "100"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"材料楼", "415", "三", "11-12", "100"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"材料楼", "415", "四", "01-02", "100"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"材料楼", "415", "四", "03-04", "100"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"材料楼", "415", "五", "05-06", "100"});
        db.execSQL("insert into Empty(build,room,week,jieci,nsit)values(?,?,?,?,?)", new String[]{"材料楼", "415", "五", "07-08", "100"});
    }

    //插入课程数据
    public void insert_course(SQLiteDatabase db){
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{"Android应用开发","鲁鹏程","信息楼","505","一","01-02"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{"软件工程导论","程颖","三教","104","一","03-04"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{"软件工程导论","程颖","三教","104","三","03-04"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{"计算机网络","竹翠","三教","302","一","05-06"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{"计算机网络","竹翠","三教","302","四","07-08"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{"高级英语综合","陈浩","三教","208","一","07-08"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{"营销沟通技巧","赵爱琴","一教","203","一","09-10"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{"操作系统","陈洪丽","一教","426","二","01-02"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{"操作系统","陈洪丽","一教","426","四","03-04"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{"Java程序设计","常鹏","知行楼","102","二","03-04"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{"Java程序设计","孙健","一教","514","三","03-04"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{"Java程序设计","孙健","一教","514","五","01-02"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{".NET项目实训开发","蒋海华","知行楼","206","三","09-10"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{".NET项目实训开发","王全民","知行楼","207","一","09-10"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{".NET项目实训开发","郑爽","知行楼","207","二","09-10"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{".NET项目实训开发","郑爽","知行楼","205","三","09-10"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{"web开发基础","王卓峥","知行楼","102","五","03-04"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{"国际金融","王涛","三教","105","五","09-10"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{"国际金融","王涛","三教","105","六","01-02"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{"国际金融","王涛","三教","105","六","05-06"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{"统计学","李鹏","一教","328","一","01-02"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{"统计学","王雪丽","三教","128","一","03-04"});
        db.execSQL("insert into Course(name,teacher,build,room,week,jieci)values(?,?,?,?,?,?)",new String[]{"形式语言","蒋原礼","三教","101","四","09-10"});
    }
}
