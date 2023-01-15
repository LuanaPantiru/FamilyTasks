import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.familytasks.R;
import com.example.familytasks.model.Task;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<Task> mTasks;
    private Context mContext;

    public TaskAdapter(Context context, List<Task> tasks) {
        mTasks = tasks;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View taskView = inflater.inflate(R.layout.my_tasks, parent, false);
        ViewHolder viewHolder = new ViewHolder(taskView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = mTasks.get(position);
        TextView textView = holder.taskTextView;
        textView.setText(task.getTitle());
        Button editButton = holder.editButton;
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // code to edit task
            }
        });
        Button deleteButton = holder.deleteButton;
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // code to delete task
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView taskTextView;
        public Button editButton;
        public Button deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
//            taskTextView = itemView.findViewById(R.id.task_title);
//            editButton = itemView.findViewById(R.id.edit_button);
//            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
}
