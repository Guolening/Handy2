package com.example.handy.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.example.handy.MapActivity;
import com.example.handy.Model.Course;
import com.example.handy.Model.EmptyRoom;
import com.example.handy.R;

import java.util.List;

/**
 * Created by t520win764zy on 2017/12/9.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>{
    private List<Course> mcourseList;


    static class ViewHolder extends RecyclerView.ViewHolder {
        View courseView;
        TextView course_no;
        TextView course_name;
        TextView course_teacher;
        TextView course_build;
        TextView course_room;
        TextView course_week;
        TextView course_jieci;

        public ViewHolder(View itemView) {
            super(itemView);
            courseView=itemView;
            course_no=(TextView)itemView.findViewById(R.id.course_result_item_no);
            course_name=(TextView)itemView.findViewById(R.id.course_result_item_cname);
            course_teacher=(TextView)itemView.findViewById(R.id.course_result_item_tname);
            course_build=(TextView)itemView.findViewById(R.id.course_result_item_build);
            course_room=(TextView)itemView.findViewById(R.id.course_result_item_room);
            course_week= (TextView)itemView.findViewById(R.id.course_result_item_week);
            course_jieci=(TextView)itemView.findViewById(R.id.course_result_item_jieci);
        }
    }

    public CourseAdapter(List<Course> courseList) {
        mcourseList=courseList;
    }
    public CourseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.course_result_item,parent,false);
        final CourseAdapter.ViewHolder holder=new CourseAdapter.ViewHolder(view);
        holder.courseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Course course = mcourseList.get(position);

                Intent intent = new Intent(view.getContext(), MapActivity.class);//注意MapActivity记得在manifest.xml中进行声明
                Bundle bundle = new Bundle();
                bundle.putString("build",course.getBuild());//emptyRoom即为所点击item的那个数组，.getBuild()则是得出教学楼
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);//和成功之间仅差view.getContext().！！！！！！！
            }
        });
        return holder;
    }

    public void onBindViewHolder(CourseAdapter.ViewHolder holder, int position) {
        Course course=mcourseList.get(position);   //为每个子项赋值
        holder.course_no.setText(""+course.getNo());
        holder.course_name.setText(""+course.getName());
        holder.course_teacher.setText(""+course.getTeacher());
        holder.course_build.setText(""+course.getBuild());
        holder.course_room.setText(""+course.getRooom());
        holder.course_week.setText(""+course.getWeek());
        holder. course_jieci.setText(""+course.getJieci());
    }
    public int getItemCount() {
        return mcourseList.size();
    }
}
