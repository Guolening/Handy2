package com.example.handy.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.handy.Adapter.CourseAdapter;
import com.example.handy.DataBase.DataBaseHelper;
import com.example.handy.Model.Course;
import com.example.handy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by t520win764zy on 2017/12/9.
 */

public class CourseResultRecyclerviewActivity extends AppCompatActivity {

    private List<Course> courseList=new ArrayList<>();
    private DataBaseHelper courseDataBaseHelper;
    private SQLiteDatabase db2;

//    private String week;
//    private String jieci;
//    private String build;
//    private String room;
//    private String name;
//    private String teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.course_result);

        ArrayList<String> course_tiaojian= new ArrayList<String>();
        Bundle bundle =getIntent().getExtras();
        course_tiaojian = bundle.getStringArrayList("course");

        String week = course_tiaojian.get(0);
        String jieci = course_tiaojian.get(1);
        String build = course_tiaojian.get(2);
        String room = course_tiaojian.get(3);
        String name = course_tiaojian.get(4);
        String teacher = course_tiaojian.get(5);

        courseDataBaseHelper=new DataBaseHelper(this,"Handy.db",null,10);
        db2=courseDataBaseHelper.getWritableDatabase();

        int inum=0;
        Cursor cursor = db2.rawQuery("select * from Course", null);

        if(!week.equals("全部")&&!jieci.equals("全部")&&!build.equals("全部")&& !room.equals("全部")) {
            cursor = db2.rawQuery("select * from Course where build=? and room = ? and week = ? and jieci = ?", new String[]{build, room, week, jieci});
        }else if(week.equals("全部")&&!jieci.equals("全部")&&!build.equals("全部")&& !room.equals("全部")&&name.equals("全部")&&teacher.equals("全部")) {
            cursor = db2.rawQuery("select * from Course where build=? and room = ?and jieci = ?", new String[]{build, room, jieci});
        }else if(!week.equals("全部")&&jieci.equals("全部")&&!build.equals("全部")&& !room.equals("全部")&&name.equals("全部")&&teacher.equals("全部")) {
            cursor = db2.rawQuery("select * from Course where build=? and room = ? and week = ? ", new String[]{build, room, week});
        }else if(!week.equals("全部")&&!jieci.equals("全部")&&!build.equals("全部")&& room.equals("全部")&&name.equals("全部")&&teacher.equals("全部")) {
            cursor = db2.rawQuery("select * from Course where build=? and week = ? and jieci = ?", new String[]{build,week, jieci});
        }else if(!week.equals("全部")&&!jieci.equals("全部")&&build.equals("全部")&&name.equals("全部")&&teacher.equals("全部")) {//没有教学楼肯定不能选指定教室了！！！！！！！！！！！！！！！！
            cursor = db2.rawQuery("select * from Course where week = ? and jieci = ?", new String[]{week, jieci});
        }else if(week.equals("全部")&&jieci.equals("全部")&&!build.equals("全部")&& !room.equals("全部")&&name.equals("全部")&&teacher.equals("全部")) {
            cursor = db2.rawQuery("select * from Course where build=? and room = ? ", new String[]{build, room,});
        }else if(week.equals("全部")&&!jieci.equals("全部")&&!build.equals("全部")&& room.equals("全部")&&name.equals("全部")&&teacher.equals("全部")) {
            cursor = db2.rawQuery("select * from Course where build=? and jieci = ?", new String[]{build,jieci});
        }else if(!week.equals("全部")&&jieci.equals("全部")&&!build.equals("全部")&& room.equals("全部")&&name.equals("全部")&&teacher.equals("全部")) {
            cursor = db2.rawQuery("select * from Course where build  = ? and week = ?", new String[]{build,week});
        }else if(week.equals("全部")&&!jieci.equals("全部")&&build.equals("全部")&&name.equals("全部")&&teacher.equals("全部")) {
            cursor = db2.rawQuery("select * from Course where jieci = ?", new String[]{jieci});
        }else if(week.equals("全部")&&jieci.equals("全部")&&!build.equals("全部")&& room.equals("全部")&&name.equals("全部")&&teacher.equals("全部")) {
            cursor = db2.rawQuery("select * from Course where build=?", new String[]{build});
        }else if(!week.equals("全部")&&jieci.equals("全部")&&build.equals("全部")&&name.equals("全部")&&teacher.equals("全部")) {
            cursor = db2.rawQuery("select * from Course where week = ?", new String[]{week});
        }else if(week.equals("全部")&&jieci.equals("全部")&&build.equals("全部")&&room.equals("000")&&name.equals("全部")&&teacher.equals("全部")) {
            cursor = db2.rawQuery("select * from Course",null);
        }else if(week.equals("全部")&&jieci.equals("全部")&&build.equals("全部")&&!name.equals("全部")&&teacher.equals("全部")) {
            cursor = db2.rawQuery("select * from Course where name = ?", new String[]{name});//////////////只引入了部分功能！！！！！！！！！！
        }else if(week.equals("全部")&&jieci.equals("全部")&&build.equals("全部")&&name.equals("全部")&&!teacher.equals("全部")) {
            cursor = db2.rawQuery("select * from Course where teacher = ?", new String[]{teacher});
        }

        if(cursor.moveToFirst()){
            do{
                build = cursor.getString(cursor.getColumnIndex("build"));
                room = cursor.getString(cursor.getColumnIndex("room"));
                week = cursor.getString(cursor.getColumnIndex("week"));
                jieci = cursor.getString(cursor.getColumnIndex("jieci"));
                name = cursor.getString(cursor.getColumnIndex("name"));
                teacher = cursor.getString(cursor.getColumnIndex("teacher"));
                inum++;
                Course course = new Course(inum,name,teacher,build,room,week,jieci);
                courseList.add(course);
            }while(cursor.moveToNext());
        }else{
            //对话框
            AlertDialog.Builder dia = new AlertDialog.Builder(this);//同名问题
            dia.setTitle("Sorry");
            dia.setMessage("没有数据满足所选条件！\n请重新选择！");
            dia.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.setClass(CourseResultRecyclerviewActivity.this, CourseActivity.class);
                    startActivity(intent);
                }
            });
            AlertDialog a = dia.create();
            a.show();
        }
        cursor.close();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.course_result_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        CourseAdapter adapter = new CourseAdapter(courseList);
        recyclerView.setAdapter(adapter);
    }
}
