package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insightcanvas.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Models.Comments;


public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.viewholder> {
    Context context;
    List<Comments>list=new ArrayList<>();

    public CommentsAdapter(Context context, List<Comments> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.opinions_row,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Comments comments=list.get(position);
        holder.opinion.setText(comments.getDescription());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class  viewholder extends RecyclerView.ViewHolder {
        TextView opinion;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            opinion=itemView.findViewById(R.id.opinion);
        }
    }
}
