package com.example.handy.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.handy.Model.Schedule;
import com.example.handy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by t520win764zy on 2017/12/10.
 */

public class ScheduleUpdateActivity extends AppCompatActivity {
    private Button btn_save;
    private Button btn_back;
    private Spinner spinner_week;
    private Spinner spinner_jieci_start;
    private Spinner spinner_jieci_end;
    private EditText edt_build;
    private EditText edt_room;
    private EditText edt_course_name;
    private int jieci_start=0;
    private int jieci_end=0;
    private String build=null;
    private String room=null;
    private String course_name=null;
    private String week=null;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.schedule_update_layout);

        spinner_week = (Spinner) findViewById(R.id.schedule_update_spn_week);
        spinner_jieci_start = (Spinner) findViewById(R.id.schedule_update_spn_jieci_start);
        spinner_jieci_end = (Spinner) findViewById(R.id.schedule_update_spn_jieci_end);
        edt_build=(EditText)findViewById(R.id.schedule_update_edt_build);
        edt_room=(EditText)findViewById(R.id.schedule_update_edt_room);
        edt_course_name=(EditText)findViewById(R.id.schedule_update_edt_name);
        btn_save = (Button) findViewById(R.id.schedule_update_btn_save);
        btn_back = (Button) findViewById(R.id.schedule_update_btn_back);

        spinner_week.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                week = spinner_week.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        spinner_jieci_start.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                jieci_start = getjiecistartend(id);
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        spinner_jieci_end.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                jieci_end = getjiecistartend(id);
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btn_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                build = edt_build.getText().toString();//!!!!!!!!!!!!!!!!!位置问题！！！！！！！！！！从编辑框里取值必须放在btn的点击事件中不然来不及取值
                room = edt_room.getText().toString();
                course_name = edt_course_name.getText().toString();

                if(course_name==null||build==null||room==null||jieci_start==0||jieci_end==0||week==null) {
                    Toast t = Toast.makeText(getApplicationContext(), "请输入全部信息！", Toast.LENGTH_SHORT);
                    t.show();
                }else if(week=="全部") {
                    Toast t = Toast.makeText(getApplicationContext(), "请输入准确的*星期*信息！", Toast.LENGTH_SHORT);
                    t.show();
                }else{
                    Intent intent = new Intent();
                    Bundle bundle=new Bundle();

                    bundle.putString("course_name",course_name);//!!!!!!!!!!!!
                    bundle.putString("build",build);
                    bundle.putString("room",room);
                    bundle.putString("week",week);
                    bundle.putInt("jieci_start",jieci_start);
                    bundle.putInt("jieci_end",jieci_end);

                    intent.putExtras(bundle);
                    intent.setClass(ScheduleUpdateActivity.this,ScheduleActivity.class);
                    startActivity(intent);
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE); // 画框
        drawable.setStroke(1, Color.BLUE); // 边框粗细及颜色
        drawable.setColor(0x22FFFFFF); // 边框内部颜色
        btn_save.setBackgroundDrawable(drawable); // 设置背景（效果就是有边框及底色）
        btn_back.setBackgroundDrawable(drawable);
    }

    private int getjiecistartend(long id){
        if (id==0)  return 1;
        else if (id==1)  return 2;
        else if (id==2)  return 3;
        else if (id==3)  return 4;
        else if (id==4)  return 5;
        else if (id==5)  return 6;
        else if (id==6)  return 7;
        else if (id==7)  return 8;
        else if (id==8)  return 9;
        else if (id==9)  return 10;
        else if (id==10)  return 11;
        else if (id==11)  return 12;
        else return 0;
    }
}
