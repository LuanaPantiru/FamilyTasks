package com.example.familytasks;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>{
    private List<String> activities;
    private OnItemClickListener listener;

    @SuppressLint("NotifyDataSetChanged")
    public void submit(List<String> list){
        activities = list;
        notifyDataSetChanged();
    }

    public ActivityAdapter(List<String> activities,OnItemClickListener listener) {
        this.activities = activities;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.one_activity,parent,false);
        return new ActivityAdapter.ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        holder.bind(activities.get(position));
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public class ActivityViewHolder extends RecyclerView.ViewHolder {
        private final TextView activity;
        private final View layout;

        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            this.activity = itemView.findViewById(R.id.activity);
            layout = itemView.findViewById(R.id.container);
        }

        @SuppressLint("SetTextI18n")
        public void bind(String activityName){
            activity.setText(activityName);

            layout.setOnClickListener(view -> listener.onItemClick(activityName));
        }

    }
}
