package com.example.handy.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.handy.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by t520win764zy on 2017/12/10.
 */

public class CourseActivity extends AppCompatActivity {
    Button btn1;
    Button btn2;
    private List<String> list_kaishiroom;
    private List<String> list_yijiao;
    private List<String> list_sanjiao;
    private List<String> list_xinxilou;
    private List<String> list_zhixinglou;
    private List<String> list_room;

    private List<String> list_kaishiteacher;
    private List<String> list_jisuanjiwangluo;
    private List<String> list_java;
    private List<String> list_net;
    private List<String> list_tongjixue;
    private List<String> list_teacher;

    private Spinner spinner_week;
    private Spinner spinner_jieci;
    private Spinner spinner_build;
    private Spinner spinner_room;
    private Spinner spinner_name;
    private Spinner spinner_teacher;
    private String week;
    private String jieci;
    private String build;
    private String room;
    private String name;
    private String teacher;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.course_layout);

        context=this;

//        long time=System.currentTimeMillis();//获取系统当前时间 星期weekNow
//        Date date=new Date(time);
//        SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 EEEE");
//        format=new SimpleDateFormat("E");
//        String weekNow=format.format(date);
//        weekNow=weekNow.substring(1);
//        Log.i("======>weekNow","weekNow="+weekNow);

//        int xiabiao=initWeek(weekNow);
//        spinner_week.setSelection(xiabiao,true);

        btn1 = (Button) findViewById(R.id.course_btn_search);
        btn2 = (Button) findViewById(R.id.course_btn_back);
        spinner_week = (Spinner) findViewById(R.id.course_spn_week);
        spinner_jieci = (Spinner) findViewById(R.id.course_spn_jieci);
        spinner_build = (Spinner) findViewById(R.id.course_spn_build);
        spinner_room= (Spinner) findViewById(R.id.course_spn_room);
        spinner_name= (Spinner) findViewById(R.id.course_spn_name);
        spinner_teacher= (Spinner) findViewById(R.id.course_spn_teacher);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE); // 画框
        drawable.setStroke(1, Color.BLUE); // 边框粗细及颜色
        drawable.setColor(0x22FFFFFF); // 边框内部颜色

        btn1.setBackgroundDrawable(drawable); // 设置背景（效果就是有边框及底色）
        btn2.setBackgroundDrawable(drawable);

        spinner_week.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                week = spinner_week.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinner_jieci.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                jieci = spinner_jieci.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        spinner_build.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                build = spinner_build.getSelectedItem().toString();

                list_room = room_init(id);//onItemSelected中第四个参数id是下拉框中选中的第几行，一般由上到下从0开始
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,list_room);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_room.setAdapter(adapter);
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        spinner_room.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                room = spinner_room.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinner_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                name = spinner_name.getSelectedItem().toString();

                list_teacher = teacher_init(id);//onItemSelected中第四个参数id是下拉框中选中的第几行，一般由上到下从0开始
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,list_teacher);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_teacher.setAdapter(adapter);
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        spinner_teacher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                teacher = spinner_teacher.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ArrayList<String> course_tiaojian = new ArrayList<String>();
                course_tiaojian.add(week);
                course_tiaojian.add(jieci);
                course_tiaojian.add(build);
                course_tiaojian.add(room);
                course_tiaojian.add(name);
                course_tiaojian.add(teacher);

                Intent intent = new Intent();

                Bundle bundle=new Bundle();
                bundle.putStringArrayList("course",course_tiaojian);

                intent.putExtras(bundle);
                intent.setClass(CourseActivity.this,CourseResultRecyclerviewActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private List<String> room_init(long id){
        //一教的教室选项
        list_yijiao = new ArrayList<String>();
        list_yijiao.add("203");
        list_yijiao.add("328");
        list_yijiao.add("426");
        list_yijiao.add("514");

        //三教的教室选项
        list_sanjiao = new ArrayList<String>();
        list_sanjiao.add("101");
        list_sanjiao.add("104");
        list_sanjiao.add("105");
        list_sanjiao.add("128");
        list_sanjiao.add("208");
        list_sanjiao.add("302");

        //信息楼的教室选项
        list_xinxilou = new ArrayList<String>();
        list_xinxilou.add("505");

        //知行楼的教室选项
        list_zhixinglou = new ArrayList<String>();
        list_zhixinglou.add("102");
        list_zhixinglou.add("205");
        list_zhixinglou.add("206");
        list_zhixinglou.add("207");

        //空的教室选项
        list_kaishiroom = new ArrayList<String>();
        list_kaishiroom.add("000");
        list_kaishiroom.add("000");

        if(id==0) return list_kaishiroom;
        else if (id==1)  return list_yijiao;
        else if (id==2)  return list_sanjiao;
        else if (id==3)  return list_xinxilou;
        else if (id==4)  return list_zhixinglou;
        else return list_kaishiroom;
    }

    private List<String> teacher_init(long id){
        //计算机网络的教师选项
        list_jisuanjiwangluo = new ArrayList<String>();
        list_jisuanjiwangluo.add("竹翠");

        //Java的教师选项
        list_java = new ArrayList<String>();
        list_java.add("常鹏");
        list_java.add("孙健");

        //.NET的教师选项
        list_net = new ArrayList<String>();
        list_net.add("蒋海华");
        list_net.add("王全民");
        list_net.add("郑爽");

        //统计学的教师选项
        list_tongjixue = new ArrayList<String>();
        list_tongjixue.add("李鹏");
        list_tongjixue.add("王雪丽");

        //空的教师选项
        list_kaishiteacher = new ArrayList<String>();
        list_kaishiteacher.add("全部");
        list_kaishiteacher.add("竹翠");
        list_kaishiteacher.add("常鹏");
        list_kaishiteacher.add("孙健");
        list_kaishiteacher.add("蒋海华");
        list_kaishiteacher.add("王全民");
        list_kaishiteacher.add("郑爽");
        list_kaishiteacher.add("李鹏");
        list_kaishiteacher.add("王雪丽");

        if (id==2)  return list_jisuanjiwangluo;
        else if (id==4)  return list_java;
        else if (id==5)  return list_net;
        else if (id==6)  return list_tongjixue;
        else return list_kaishiteacher;
    }
}
