package com.example.familytasks;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familytasks.model.NormalMember;
import com.example.familytasks.model.User;

import java.util.List;

public class RemoveMemberAdapter extends RecyclerView.Adapter<RemoveMemberAdapter.RemoveMemberViewHolder>{

    private List<NormalMember> members;
    private OnCheckedChangeListener listener;

    @SuppressLint("NotifyDataSetChanged")
    public void submit(List<NormalMember> list){
        members = list;
        notifyDataSetChanged();
    }

    public RemoveMemberAdapter(List<NormalMember> members, OnCheckedChangeListener listener) {
        this.members = members;
        this.listener=listener;
    }

    @NonNull
    @Override
    public RemoveMemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.one_user,parent,false);
        return new RemoveMemberAdapter.RemoveMemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RemoveMemberAdapter.RemoveMemberViewHolder holder, int position) {
        holder.bind(members.get(position));
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public class RemoveMemberViewHolder extends RecyclerView.ViewHolder {
        private final TextView fullName;
        private final TextView username;
        private final TextView email;
        private final CheckBox checkBox;
        private final View layout;

        public RemoveMemberViewHolder(@NonNull View itemView) {
            super(itemView);
            this.fullName = itemView.findViewById(R.id.userFullName);
            this.username = itemView.findViewById(R.id.username);
            this.email = itemView.findViewById(R.id.email);
            checkBox = itemView.findViewById(R.id.checkBox);
            layout = itemView.findViewById(R.id.container);
        }

        @SuppressLint("SetTextI18n")
        public void bind(NormalMember member){
            fullName.setText(member.getUser().getFirstName()+" "+member.getUser().getLastName());
            username.setText(member.getUser().getUserName());
            email.setText(member.getUser().getEmail());
            checkBox.setOnCheckedChangeListener((compoundButton, b) -> listener.onCheckedChangeListener(b,member));
        }
    }
}
