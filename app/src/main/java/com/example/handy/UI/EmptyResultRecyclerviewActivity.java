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
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.handy.Adapter.EmptyRoomAdapter;
import com.example.handy.DataBase.DataBaseHelper;
import com.example.handy.Model.EmptyRoom;
import com.example.handy.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.string.no;

/**
 * Created by t520win764zy on 2017/12/9.
 */

public class EmptyResultRecyclerviewActivity extends AppCompatActivity {
    private List<EmptyRoom> emptyroomList=new ArrayList<>();
    private DataBaseHelper emptyDataBaseHelper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.empty_result);

        ArrayList<String> empty_tiaojian= new ArrayList<String>();//从empty_activity传过来的筛选条件，解包，拿值
        Bundle bundle =getIntent().getExtras();
        empty_tiaojian = bundle.getStringArrayList("empty");

        String week = empty_tiaojian.get(0);//将拿到的筛选条件分别赋值给不同的变量
        String jieci = empty_tiaojian.get(1);
        String build = empty_tiaojian.get(2);
        String room = empty_tiaojian.get(3);

        emptyDataBaseHelper=new DataBaseHelper(this,"Handy.db",null,10);//通过这种方式拿到一开始创建的数据库
        db=emptyDataBaseHelper.getWritableDatabase();//因为之前创建了，所以这次就算调用了getWritableDatabase也不会再建新的

        int inum=0;
        Cursor cursor = db.rawQuery("select * from Empty", null);//cursor索引，在数据库的数据中进行顺序查找

        //利用查询条件查找满足条件的cursor，注意：教学楼和教室存在关联！！！！
        if(!week.equals("全部")&&!jieci.equals("全部")&&!build.equals("全部")&& !room.equals("全部")) {
            cursor = db.rawQuery("select * from Empty where build=? and room = ? and week = ? and jieci = ?", new String[]{build, room, week, jieci});
        }
        else if(week.equals("全部")&&!jieci.equals("全部")&&!build.equals("全部")&& !room.equals("全部")) {
            cursor = db.rawQuery("select * from Empty where build=? and room = ?and jieci = ?", new String[]{build, room, jieci});
        }
        else if(!week.equals("全部")&&jieci.equals("全部")&&!build.equals("全部")&& !room.equals("全部")) {
            cursor = db.rawQuery("select * from Empty where build=? and room = ? and week = ? ", new String[]{build, room, week});
        }
        else if(!week.equals("全部")&&!jieci.equals("全部")&&!build.equals("全部")&& room.equals("全部")) {
            cursor = db.rawQuery("select * from Empty where build=? and week = ? and jieci = ?", new String[]{build,week, jieci});
        }
        else if(!week.equals("全部")&&!jieci.equals("全部")&&build.equals("全部")) {//没有教学楼肯定不能选指定教室了！！！！！！！！！！！！！！！！
            cursor = db.rawQuery("select * from Empty where week = ? and jieci = ?", new String[]{week, jieci});
        }
        else if(week.equals("全部")&&jieci.equals("全部")&&!build.equals("全部")&& !room.equals("全部")) {
            cursor = db.rawQuery("select * from Empty where build=? and room = ? ", new String[]{build, room,});
        }
        else if(week.equals("全部")&&!jieci.equals("全部")&&!build.equals("全部")&& room.equals("全部")) {
            cursor = db.rawQuery("select * from Empty where build=? and jieci = ?", new String[]{build,jieci});
        }
        else if(!week.equals("全部")&&jieci.equals("全部")&&!build.equals("全部")&& room.equals("全部")) {
            cursor = db.rawQuery("select * from Empty where build  = ? and week = ?", new String[]{build,week});
        }
        else if(week.equals("全部")&&!jieci.equals("全部")&&build.equals("全部")) {
            cursor = db.rawQuery("select * from Empty where jieci = ?", new String[]{jieci});
        }
        else if(week.equals("全部")&&jieci.equals("全部")&&!build.equals("全部")&& room.equals("全部")) {
            cursor = db.rawQuery("select * from Empty where build=?", new String[]{build});
        }
        else if(!week.equals("全部")&&jieci.equals("全部")&&build.equals("全部")) {
            cursor = db.rawQuery("select * from Empty where week = ?", new String[]{week});
        }
        else if(week.equals("全部")&&jieci.equals("全部")&&build.equals("全部")) {
            cursor = db.rawQuery("select * from Empty", null);
        }
        if(cursor.moveToFirst()){//调用moveToFirst使得cursor的指针移到第一行的位置
            do{//进入循环，从而遍历每一行数据，然后把他们都放进数组中，从而在recycleview中显示出来
                build = cursor.getString(cursor.getColumnIndex("build"));//getColumnIndex是将该列在表中的位置索引通过getString传到相应的值中
                room = cursor.getString(cursor.getColumnIndex("room"));
                week = cursor.getString(cursor.getColumnIndex("week"));
                jieci = cursor.getString(cursor.getColumnIndex("jieci"));
                String nsit = cursor.getString(cursor.getColumnIndex("nsit"));
                inum++;
                EmptyRoom er = new EmptyRoom(inum,build,room,week,jieci,nsit);//将得到的数组组成一条数据，放进数组
                emptyroomList.add(er);
            }while(cursor.moveToNext());
        }
        else{
            //对话框  倘若没有得到数据，给出提醒，当用户点击“确认”时调回输入查询条件画面
            AlertDialog.Builder dia = new AlertDialog.Builder(this);
            dia.setTitle("Sorry");//title
            dia.setMessage("没有数据满足所选条件！\n请重新选择！");//内容
            dia.setPositiveButton("确定", new DialogInterface.OnClickListener() {//包含的按钮内容及其点击事件
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.setClass(EmptyResultRecyclerviewActivity.this, EmptyActivity.class);
                    startActivity(intent);
                }
            });
            AlertDialog a = dia.create();
            a.show();
        }
        cursor.close();

        //recycleview的显示
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.empty_result_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        EmptyRoomAdapter adapter = new EmptyRoomAdapter(emptyroomList);
        recyclerView.setAdapter(adapter);
    }
}
