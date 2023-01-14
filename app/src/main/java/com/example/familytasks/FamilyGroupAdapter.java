package com.example.familytasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familytasks.model.FamilyGroup;

import java.util.List;

public class FamilyGroupAdapter extends RecyclerView.Adapter<FamilyGroupAdapter.FamilyGroupViewHolder> {
    private List<FamilyGroup> familyGroups;
    private OnItemClickListener listener;

    @SuppressLint("NotifyDataSetChanged")
    public void submit(List<FamilyGroup> list){
        familyGroups = list;
        notifyDataSetChanged();
    }

    public FamilyGroupAdapter(List<FamilyGroup> familyGroups, OnItemClickListener listener) {
        this.familyGroups = familyGroups;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FamilyGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.one_family_group,parent,false);
        return new FamilyGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FamilyGroupViewHolder holder, int position) {
        holder.bind(familyGroups.get(position));
    }

    @Override
    public int getItemCount() {
        return familyGroups.size();
    }

    public class FamilyGroupViewHolder extends RecyclerView.ViewHolder {
        private final TextView familyGroupName;
        private final View layout;

        public FamilyGroupViewHolder(@NonNull View itemView) {
            super(itemView);
            familyGroupName = itemView.findViewById(R.id.familyGroupName);
            layout = itemView.findViewById(R.id.container);

        }
        @SuppressLint("SetTextI18n")
        public void bind(FamilyGroup familyGroup){
            familyGroupName.setText(familyGroup.getFamilyGroupName());

            layout.setOnClickListener(view -> listener.onItemClick(familyGroup));
        }
    }
}
