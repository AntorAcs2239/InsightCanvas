package Adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insightcanvas.R;

import java.util.ArrayList;
import java.util.List;

import Models.Student;
import Models.User;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.viewholder>{
    List<Student>list=new ArrayList<>();
    Context context;

    public StudentAdapter(List<Student> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(LayoutInflater.from(context).inflate(R.layout.rec_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Student student=list.get(position);
        holder.name.setText(student.getName());
        holder.dept.setText(student.getDept());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    class viewholder extends RecyclerView.ViewHolder {
        TextView name,dept;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            dept=itemView.findViewById(R.id.dept);
        }
    }
}
