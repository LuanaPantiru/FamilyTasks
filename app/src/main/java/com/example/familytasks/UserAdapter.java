package com.example.familytasks;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familytasks.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<User> users;
    private OnCheckedChangeListener listener;

    @SuppressLint("NotifyDataSetChanged")
    public void submit(List<User> list){
        users = list;
        notifyDataSetChanged();
    }

    public UserAdapter(List<User> users, OnCheckedChangeListener listener) {
        this.users = users;
        this.listener=listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.one_user,parent,false);
        return new UserAdapter.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private final TextView fullName;
        private final TextView username;
        private final TextView email;
        private final CheckBox checkBox;
        private final View layout;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            this.fullName = itemView.findViewById(R.id.userFullName);
            this.username = itemView.findViewById(R.id.username);
            this.email = itemView.findViewById(R.id.email);
            checkBox = itemView.findViewById(R.id.checkBox);
            layout = itemView.findViewById(R.id.container);
        }

        @SuppressLint("SetTextI18n")
        public void bind(User user){
            fullName.setText(user.getFirstName()+" "+user.getLastName());
            username.setText(user.getUserName());
            email.setText(user.getEmail());
            checkBox.setOnCheckedChangeListener((compoundButton, b) -> listener.onCheckedChangeListener(b,user));
        }
    }
}
