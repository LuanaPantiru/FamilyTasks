package com.example.familytasks;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familytasks.model.User;
import com.example.familytasks.model.enums.FamilyMemberNickname;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RequiresApi(api = Build.VERSION_CODES.N)
public class SetNicknameAdapter extends RecyclerView.Adapter<SetNicknameAdapter.SetNicknameViewHolder>{

    private List<User> users;
    private OnItemSelectedListener listener;

    @SuppressLint("NotifyDataSetChanged")
    public void submit(List<User> list){
        users = list;
        notifyDataSetChanged();
    }

    public SetNicknameAdapter(List<User> users, OnItemSelectedListener listener) {
        this.users = users;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SetNicknameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.one_user_set_nickname,parent,false);
        return new SetNicknameViewHolder(view, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull SetNicknameViewHolder holder, int position) {
        holder.bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class SetNicknameViewHolder extends RecyclerView.ViewHolder {
        private final TextView fullName;
        private final TextView username;
        private final TextView email;
        private final Spinner nickname;
        private List<String> nicknames;

        public SetNicknameViewHolder(@NonNull View itemView, @NonNull ViewGroup parent) {
            super(itemView);
            this.fullName = itemView.findViewById(R.id.userFullName);
            this.username = itemView.findViewById(R.id.username);
            this.email = itemView.findViewById(R.id.email);
            this.nickname = itemView.findViewById(R.id.nickname);
            nicknames = new ArrayList<>();
            nicknames.add("None");
            nicknames.addAll(Arrays.stream(FamilyMemberNickname.values()).map(Enum::toString).collect(Collectors.toList()));
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(parent.getContext(), android.R.layout.simple_spinner_item, nicknames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            nickname.setAdapter(adapter);
        }

        @SuppressLint("SetTextI18n")
        public void bind(User user){
            fullName.setText(user.getFirstName()+" "+user.getLastName());
            username.setText(user.getUserName());
            email.setText(user.getEmail());
            nickname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    listener.onItemSelected(nicknames.get(i), user);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }


    }
}
