package com.example.handy.UI;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.handy.DataBase.DataBaseHelper;
import com.example.handy.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by t520win764zy on 2017/12/10.
 */

public class EmptyActivity extends AppCompatActivity {
    Button btn1;
    Button btn2;
    private List<String> list_kaishi;
    private List<String> list_yijiao;
    private List<String> list_erjiao;
    private List<String> list_sanjiao;
    private List<String> list_cailiao;
    private List<String> list_shuli;
    private List<String> list_room;
    private Spinner spinner_week;
    private Spinner spinner_jieci;
    private Spinner spinner_build;
    private Spinner spinner_room;
    private String week;
    private String jieci;
    private String build;
    private String room;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.empty_layout);

        context=this;

//        long time=System.currentTimeMillis();//获取系统当前时间 星期weekNow
//        Date date=new Date(time);
//        SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 EEEE");
//        format=new SimpleDateFormat("E");
//        String weekNow=format.format(date);
//        weekNow=weekNow.substring(1);
//        Log.i("======>weekNow","weekNow="+weekNow);
//
//        int xiabiao=initWeek(weekNow);
//        spinner_week.setSelection(xiabiao,true);

        btn1 = (Button) findViewById(R.id.empty_btn_search);
        btn2 = (Button) findViewById(R.id.empty_btn_back);
        spinner_week = (Spinner) findViewById(R.id.empty_spn_week);
        spinner_jieci = (Spinner) findViewById(R.id.empty_spn_jieci);
        spinner_build = (Spinner) findViewById(R.id.empty_spn_build);
        spinner_room= (Spinner) findViewById(R.id.empty_spn_room);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE); // 画框 外形是矩形rectangle
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
                //build和room两个spinner的关联：不同的教学楼对应的教室不同
                list_room = room_init(id);//onItemSelected中第四个参数id是下拉框中选中的第几行，一般由上到下从0开始
                // 对应不同的id即不同的build调用函数room_init得到不同的list_room，从而为room 的spinner创建具有不同数组的adapter
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

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ArrayList<String> empty_tiaojian = new ArrayList<String>();//activity之间需要传的数值，创建一个数组
                empty_tiaojian.add(week);
                empty_tiaojian.add(jieci);
                empty_tiaojian.add(build);
                empty_tiaojian.add(room);

                Bundle bundle=new Bundle();//创建一个bundle数据包，将数组进行打包
                bundle.putStringArrayList("empty",empty_tiaojian);

                Intent intent = new Intent();
                intent.putExtras(bundle);//将数据包绑定到intent事件上，一边跳转界面一边传值
                intent.setClass(EmptyActivity.this,EmptyResultRecyclerviewActivity.class);
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

    private List<String> room_init(long id){//主要是if语句 返回值是字符串类型的数组
        //一教的教室选项
        list_yijiao = new ArrayList<String>();
        list_yijiao.add("全部");
        list_yijiao.add("218");
        list_yijiao.add("228");
        list_yijiao.add("401");

        //三教的教室选项
        list_sanjiao = new ArrayList<String>();
        list_sanjiao.add("全部");
        list_sanjiao.add("101");
        list_sanjiao.add("109");
        list_sanjiao.add("530");

        //材料楼的教室选项
        list_cailiao = new ArrayList<String>();
        list_cailiao.add("全部");
        list_cailiao.add("313");
        list_cailiao.add("415");

        //空的教室选项
        list_kaishi = new ArrayList<String>();
        list_kaishi.add("000");
        list_kaishi.add("000");
        list_kaishi.add("000");
        list_kaishi.add("000");

        if (id==0)  return list_kaishi;
        else if (id==1)  return list_yijiao;
        else if (id==2)  return list_sanjiao;
        else if (id==3)  return list_cailiao;
        else return list_kaishi;
    }

}

