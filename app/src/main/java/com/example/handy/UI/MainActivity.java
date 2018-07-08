package com.example.handy.UI;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.handy.DataBase.DataBaseHelper;
import com.example.handy.R;

public class MainActivity extends AppCompatActivity {
    private DataBaseHelper emptyDataBaseHelper,courseDataBaseHelper;
    private SQLiteDatabase db1,db2;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏方法2
        setContentView(R.layout.main_layout);

        btn1 = (Button) findViewById(R.id.main_btn_empty);
        btn2 = (Button) findViewById(R.id.main_btn_course);
        btn3 = (Button) findViewById(R.id.main_btn_schedule);
        btn4 = (Button) findViewById(R.id.main_btn_tel);

        myclick m = new myclick();
        btn1.setOnClickListener(m);
        btn2.setOnClickListener(m);
        btn3.setOnClickListener(m);
        btn4.setOnClickListener(m);

        emptyDataBaseHelper=new DataBaseHelper(this,"Handy.db",null,10);//创建DataBaseHelper的实例，注意version变量，和后面emptyresult courseresult相关联，要改动必须都改，不然出错
        db1=emptyDataBaseHelper.getWritableDatabase();//帮助类的实例调用这个函数就可以去检查是否存在这个数据库，如果没有则调用oncreate进行创建
        courseDataBaseHelper=new DataBaseHelper(this,"Handy.db",null,10);
        db2=courseDataBaseHelper.getWritableDatabase();

        db1.execSQL("delete from Empty");//每次进入该APP就先清空数据再插入 免得插入重复
        db2.execSQL("delete from Course");

        emptyDataBaseHelper.insert_empty(db1);//插入数据
        courseDataBaseHelper.insert_course(db2);//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    }

    class myclick implements View.OnClickListener {//封装函数，包含点击事件，使结构清晰
        @Override
        public void onClick(View v) {
            Integer id = v.getId();
            Intent intent = new Intent();
            switch (id) {
                case R.id.main_btn_empty:
                    intent.setClass(MainActivity.this,EmptyActivity.class);
                    startActivity(intent);
                    break;
                case R.id.main_btn_course:
                    intent.setClass(MainActivity.this, CourseActivity.class);
                    startActivity(intent);
                    break;
                case R.id.main_btn_schedule:
                    intent.setClass(MainActivity.this, ScheduleActivity.class);
                    startActivity(intent);
                    break;
                case R.id.main_btn_tel:
                    intent.setClass(MainActivity.this,TelActivity.class);
                    startActivity(intent);
                    break;

            }
        }
    }
}
