package com.example.familytasks;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familytasks.model.NormalMember;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {

    List<NormalMember> members;

    @SuppressLint("NotifyDataSetChanged")
    public void submit(List<NormalMember> list){
        members = list;
        notifyDataSetChanged();
    }

    public MemberAdapter(List<NormalMember> members) {
        this.members = members;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.one_member,parent,false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        holder.bind(members.get(position));
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public static class MemberViewHolder extends RecyclerView.ViewHolder {
        private final TextView memberName;
        private final TextView memberNickname;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            this.memberName = itemView.findViewById(R.id.memberName);
            this.memberNickname = itemView.findViewById(R.id.memberNickname);
        }

        @SuppressLint("SetTextI18n")
        public void bind(NormalMember member){
            memberName.setText(member.getUser().getFirstName() + " " + member.getUser().getLastName());
            memberNickname.setText(member.getUserNickname());
        }
    }
}
