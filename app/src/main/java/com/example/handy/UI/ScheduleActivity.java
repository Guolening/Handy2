package com.example.handy.UI;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.handy.Control.DataProcess;
import com.example.handy.Model.Schedule;
import com.example.handy.Model.ScheduleList;
import com.example.handy.R;

public class ScheduleActivity extends Activity {//动态生成课表
    LinearLayout weekPanels;//周一到周日是  7个竖直线性布局文件 七天的课表 Panel面板
    List courseData;
    int itemHeight;
    int marTop, marLeft;
    private Button btn_update;
    private Button btn_back;
    private int jieci_start = 0;//初始化及在前面声明为private很重要
    private int jieci_end = 0;
    private String course_name = null;
    private String build = null;
    private String room = null;
    private String week = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.schedule_layout);

        itemHeight = getResources().getDimensionPixelSize(R.dimen.weekItemHeight);
        marTop = getResources().getDimensionPixelSize(R.dimen.weekItemMarTop);
        marLeft = getResources().getDimensionPixelSize(R.dimen.weekItemMarLeft);

        btn_update = (Button) findViewById(R.id.schedule_btn_update);
        btn_back = (Button) findViewById(R.id.schedule_btn_back);
        btn_update.setOnClickListener(new View.OnClickListener() {//修改 按钮的点击事件，使得页面跳转
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ScheduleActivity.this, ScheduleUpdateActivity.class);
                startActivity(intent);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE); // 画框
        drawable.setStroke(1, Color.BLUE); // 边框粗细及颜色
        drawable.setColor(0x22FFFFFF); // 边框内部颜色

        btn_update.setBackgroundDrawable(drawable); // 设置背景（效果就是有边框及底色）
        btn_back.setBackgroundDrawable(drawable);

        DataProcess dp1=new DataProcess();//实例化DataProcess的对象
        ScheduleList scheduleList=dp1.search();//每次进这个界面都把存的schedulelist数组里的数据取出来，然后进行课表的布局
        getData(scheduleList);//有或无修改时都要获得数据并通过调用getData函数进行课表的生成
    }

    public void getData(List<Schedule> scheduleList) {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();//先查看传过来的bundle里面有没有数据，如果有则解包拿值
        if (bundle != null) {
            course_name = bundle.getString("course_name");//注意字符型是getString
            build = bundle.getString("build");
            room = bundle.getString("room");
            week = bundle.getString("week");

            try {
                jieci_start = bundle.getInt("jieci_start");//整型是getInt  这个需要有异常 不过对于我们这个来说肯定没有异常，因为是spinner直接选择没有输入
            } catch (NumberFormatException e) {//输入格式错误异常
                e.printStackTrace();
            }
            try {
                jieci_end = bundle.getInt("jieci_end");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            Schedule schedule = new Schedule(course_name, build, room, jieci_start, jieci_end, week);//利用拿到的数据创建一条schedule数据
            scheduleList.add(schedule);//然后插入到schedulelist数组里面
        }
        int i;
        for (i = 0; i <scheduleList.size() ; i++) {//进行循环遍历
            Schedule schedule=scheduleList.get(i);//对于数组中的每一条数据
            List<Schedule> list = new ArrayList<Schedule>();
            list.add(schedule);//都放进一个String类型的数组里面
            week=schedule.getWeek();//暂时把week单独拿出来一下，因为下面要根据这个进行不同情况的判断
            try {
                if (week.equals("一") ) {//判断字符串相等与否，用equals
                    weekPanels = (LinearLayout) findViewById(R.id.weekPanel_1);//对应不同的week字符，我们把weekPanels定义为不同列的面板，从而插入到不同的列
                }else if(week.equals("二")) {
                    weekPanels = (LinearLayout) findViewById(R.id.weekPanel_2);
                }else if(week.equals("三")) {
                    weekPanels = (LinearLayout) findViewById(R.id.weekPanel_3);
                }else if(week.equals("四")) {
                    weekPanels = (LinearLayout) findViewById(R.id.weekPanel_4);
                }else if(week.equals("五")) {
                    weekPanels = (LinearLayout) findViewById(R.id.weekPanel_5);
                }else if(week.equals("六")) {
                    weekPanels = (LinearLayout) findViewById(R.id.weekPanel_6);
                }else if(week.equals("日")) {
                    weekPanels = (LinearLayout) findViewById(R.id.weekPanel_7);
                }else{
//                    Toast t = Toast.makeText(getApplicationContext(), "没有输入准确的*星期*数据！", Toast.LENGTH_SHORT);
//                    t.show();
                    break;
                }
                initWeekPanel(weekPanels, list);//有列 有数组 则开始将数组插入到layout ，调用initWeekPanel函数
            } catch (NullPointerException e) {//空指针异常
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        getLayoutInflater();
        super.onResume();
    }

    public void initWeekPanel(LinearLayout ll, List<Schedule> list) {//传入的参数有列和相应的list数组
        Schedule c = list.get(0);//把list数组中的第一条语句拿出来，其实就一条  这个是为循环插入准备的
        TextView tv = new TextView(this); //声明一个新的textview
        Integer step = c.getJieciend() - c.getJiecistart() + 1; //通过start和end计算跨度多大
        //声明LayoutParams的对象layout参数lp  width和height  而height是用itemHeight(每个item高度)和marTop(每个与上方item的距离，这个应该是step-1个)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, itemHeight * step + marTop * (step - 1));
        //四个参数分别是：左上右下 间距(已经知道那一列的情况下，设置每个item都是这样  右下都设为0即可)
        lp.setMargins(marLeft, (c.getJiecistart() - 1) * (itemHeight + marTop) + marTop, 0, 0);

        //为textview设置参数
        tv.setLayoutParams(lp);//lp间距问题
        tv.setGravity(Gravity.CENTER);//位置问题
        tv.setTextSize(12);//字体大小
        tv.setTextColor(getResources().getColor(R.color.colorPrimary));//字体颜色
        tv.setText(c.getName() + "\n" + c.getBuild() + "\n" + c.getRoom());//文字
        tv.setBackgroundColor(getResources().getColor(R.color.classIndex));//textview的背景颜色
        tv.setBackground(getResources().getDrawable(R.drawable.scheduleitem));//textview的背景图片

        ll.addView(tv);//最后为这个layout set上这个特制的textview
    }
//
//    protected void delete(){
//        get(positon);
//    }

    //以下涉及到menu，目前咱们的app没有用到
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {//填充选项菜单（读取XML文件、解析、加载到Menu组件上）
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override//重写OptionsItemSelected(MenuItem item)来响应菜单项(MenuItem)的点击事件（根据id来区分是哪个item）
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.start:
//                Toast.makeText(this, "开始游戏", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.over:
//                Toast.makeText(this, "结束游戏", Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}