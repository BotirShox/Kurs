package com.byu.kurs.Adapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byu.kurs.CoursePage;
import com.byu.kurs.R;
import com.byu.kurs.model.Course;

import java.util.List;
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    Context context;
    List<Course> courses;
    public CourseAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }
    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseItems = LayoutInflater.from(context).inflate(R.layout.course_item, parent, false);
        return new CourseAdapter.CourseViewHolder(courseItems);
    }
    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.courseBg.setBackgroundColor(Color.parseColor(courses.get(position).getColor()));
        int imageId = context.getResources().getIdentifier("ic_" + courses.get(position).getImg(), "drawable", context.getPackageName());
        holder.courseImage.setImageResource(imageId);
        holder.courseName.setText(courses.get(position).getName());
        holder.courseTitle.setText(courses.get(position).getTitle());
        holder.courseDate.setText(courses.get(position).getDate());
        holder.courseLevel.setText(courses.get(position).getLevel());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, CoursePage.class);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, new Pair<View, String>(holder.courseImage, "courseImage"));
            intent.putExtra("courseBg", Color.parseColor(courses.get(position).getColor()));
            intent.putExtra("courseImage", imageId);
            intent.putExtra("courseName", courses.get(position).getName());
            intent.putExtra("courseTitle", courses.get(position).getTitle());
            intent.putExtra("courseDate", courses.get(position).getDate());
            intent.putExtra("courseLevel", courses.get(position).getLevel());
            intent.putExtra("courseUrl", courses.get(position).getUrl());
            context.startActivity(intent, options.toBundle());
        });
    }
    @Override
    public int getItemCount() {
        return courses.size();
    }
    public static final class CourseViewHolder extends RecyclerView.ViewHolder {
        LinearLayout courseBg;
        ImageView courseImage;
        TextView courseName, courseTitle, courseDate, courseLevel;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseBg = itemView.findViewById(R.id.courseBg);
            courseImage = itemView.findViewById(R.id.courseImage);
            courseName = itemView.findViewById(R.id.courseName);
            courseTitle = itemView.findViewById(R.id.courseTitle);
            courseDate = itemView.findViewById(R.id.courseDate);
            courseLevel = itemView.findViewById(R.id.courseLevel);
        }
    }
}
