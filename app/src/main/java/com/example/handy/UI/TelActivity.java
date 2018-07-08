package com.example.handy.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.handy.Adapter.TelAdapter;
import com.example.handy.Model.Telephone;
import com.example.handy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by t520win764zy on 2017/12/10.
 */

public class TelActivity extends AppCompatActivity {
    public List<Telephone> deptList = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.tel_layout);

        ListView list = (ListView) findViewById(R.id.tel_listview);

        deptList.clear();//先清理一下，因为之后会有重新插入，免得重复

        //数组的初始化
        Telephone dept1 = new Telephone("01教务处", "67391111", "123456@qq.com");
        Telephone dept2 = new Telephone("02财务处", "67392222", "789012@qq.com");
        Telephone dept3 = new Telephone("03教材科", "67393333", "345678@qq.com");
        Telephone dept4 = new Telephone("04公关部", "67394444", "901234@qq.com");
        Telephone dept5 = new Telephone("05保卫处", "67395555", "567890@qq.com");
        Telephone dept6 = new Telephone("06后勤部", "67396666", "123456@qq.com");
        Telephone dept7 = new Telephone("07学生处", "67397777", "789012@qq.com");
        Telephone dept8 = new Telephone("08教育部", "67398888", "345678@qq.com");
        Telephone dept9 = new Telephone("09团委部", "67399999", "901234@qq.com");
        Telephone dept10 = new Telephone("10党建处", "67390000", "567890@qq.com");
        Telephone dept11 = new Telephone("11教务处", "67391111", "123456@qq.com");
        Telephone dept12 = new Telephone("12财务处", "67392222", "789012@qq.com");
        Telephone dept13 = new Telephone("13教材科", "67393333", "345678@qq.com");
        Telephone dept14 = new Telephone("14公关部", "67394444", "901234@qq.com");
        Telephone dept15 = new Telephone("15保卫处", "67395555", "567890@qq.com");
        Telephone dept16 = new Telephone("16后勤部", "67396666", "123456@qq.com");
        Telephone dept17 = new Telephone("17学生处", "67397777", "789012@qq.com");
        Telephone dept18 = new Telephone("18教育部", "67398888", "345678@qq.com");
        Telephone dept19 = new Telephone("19团委部", "67399999", "901234@qq.com");
        Telephone dept20 = new Telephone("20党建处", "67390000", "567890@qq.com");
        deptList.add(dept1);
        deptList.add(dept2);
        deptList.add(dept3);
        deptList.add(dept4);
        deptList.add(dept5);
        deptList.add(dept6);
        deptList.add(dept7);
        deptList.add(dept8);
        deptList.add(dept9);
        deptList.add(dept10);
        deptList.add(dept11);
        deptList.add(dept12);
        deptList.add(dept13);
        deptList.add(dept14);
        deptList.add(dept15);
        deptList.add(dept16);
        deptList.add(dept17);
        deptList.add(dept18);
        deptList.add(dept19);
        deptList.add(dept20);
        TelAdapter baseteladapter = new TelAdapter(this, deptList);
        list.setAdapter(baseteladapter);//为数组设置adapter

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Telephone t1 = deptList.get(i);//根据点击位置获取被点击的条目
                String phone = t1.getTel();
                //Intent.ACTION_DIAL也是一个特定的字符串  ACTION_DIAL = "android.intent.action.DIAL"
                //告诉系统我要打开拨号界面，并把要拨的号显示在拨号界面上，由用户决定是否要拨打!!
                if (phone != null && phone.trim().length() > 0) {
                    //这里"tel:"+电话号码 是固定格式!! 系统一看是以"tel:"开头的，就知道后面应该是电话号码。
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone.trim()));
                    startActivity(intent);//调用上面这个intent实现拨号
                } else {
                    Toast.makeText(getApplicationContext(), "电话号码不能为空", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
