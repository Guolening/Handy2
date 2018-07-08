package com.example.handy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.handy.Model.Telephone;
import com.example.handy.R;

import java.util.List;

/**
 * Created by t520win764zy on 2017/12/10.
 */

public class TelAdapter extends BaseAdapter {
    private List<Telephone> messageList;
    private LayoutInflater mInflater;

    public TelAdapter(Context c, List<Telephone> appList) {//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        messageList = appList;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private static class ViewHolder {
        TextView name; // 部门
        TextView tel;  //电话
        TextView mail;   //邮箱
    }
    public void clear(){
        if(messageList!=null){
            messageList.clear();
        }
    }
    public int getCount() {
        return messageList.size();
    }
    @Override
    public Object getItem(int position) {
        return messageList.get(position);
    }
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.tel_item, null);//inflate是加载一个布局文件，而findViewById则是从布局文件中查找一个控件
            holder = new ViewHolder();//viewholder是对控件的实例进行缓存  性能优化
            holder.name = (TextView) view.findViewById(R.id.Dept_name);
            holder.tel = (TextView) view.findViewById(R.id.Dept_tel);
            holder.mail = (TextView) view.findViewById(R.id.Dept_mail);
            view.setTag(holder);//将viewholder对象存储在View中
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Telephone dept = (Telephone) getItem(position); // 获取当前项的message实例
        holder.name.setText(dept.getName());
        holder.tel.setText(dept.getTel());
        holder.mail.setText(dept.getMail());

        return view;
    }
    public void remove(int position){
        messageList.remove(position);
        this.notifyDataSetChanged();
    }
}