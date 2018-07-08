package com.example.handy.Adapter;

import android.content.Entity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.handy.Model.EmptyRoom;
import com.example.handy.R;
import com.example.handy.MapActivity;

import java.util.List;

/**
 * Created by t520win764zy on 2017/12/9.
 */

public class EmptyRoomAdapter extends RecyclerView.Adapter<EmptyRoomAdapter.ViewHolder> {
    private List<EmptyRoom> memptyroomList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View emptyroomView;
        String emptyBuild;
        TextView empty_no;
        TextView empty_build;
        TextView empty_room;
        TextView empty_week;
        TextView empty_jieci;
        TextView empty_nsit;

        public ViewHolder(View itemView) {
            super(itemView);
            emptyroomView = itemView;
            empty_no = (TextView) itemView.findViewById(R.id.empty_result_item_no);
            empty_build = (TextView) itemView.findViewById(R.id.empty_result_item_build);
            empty_room = (TextView) itemView.findViewById(R.id.empty_result_item_room);
            empty_week = (TextView) itemView.findViewById(R.id.empty_result_item_week);
            empty_jieci = (TextView) itemView.findViewById(R.id.empty_result_item_jieci);
            empty_nsit = (TextView) itemView.findViewById(R.id.empty_result_item_nsit);
            emptyBuild=empty_build.getText().toString();
        }
    }

    public EmptyRoomAdapter(List<EmptyRoom> emptyRoomList) {
        memptyroomList = emptyRoomList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_result_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.emptyroomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                EmptyRoom emptyRoom = memptyroomList.get(position);

                Intent intent = new Intent(view.getContext(), MapActivity.class);//注意MapActivity记得在manifest.xml中进行声明
                Bundle bundle = new Bundle();
                bundle.putString("build",emptyRoom.getBuild());//emptyRoom即为所点击item的那个数组，.getBuild()则是得出教学楼
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);//和成功之间仅差view.getContext().！！！！！！！
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EmptyRoom emptyRoom = memptyroomList.get(position);   //为每个子项赋值
        holder.empty_no.setText("" + emptyRoom.getNo());
        holder.empty_build.setText(emptyRoom.getBuild());
        holder.empty_room.setText("" + emptyRoom.getRoom());
        holder.empty_week.setText(emptyRoom.getWeek());
        holder.empty_jieci.setText(emptyRoom.getJieci());
        holder.empty_nsit.setText("" + emptyRoom.getNsit());
    }

    @Override
    public int getItemCount() {
        return memptyroomList.size();
    }

}
