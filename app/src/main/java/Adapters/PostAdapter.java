package Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.insightcanvas.R;
import com.github.marlonlom.utilities.timeago.TimeAgo;

import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Models.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.viewholder> {
    List<Post>list=new ArrayList<>();
    Context context;
    boolean flag=true;

    public PostAdapter(List<Post> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public SurveyOnclickListener surveyOnclickListener;
    public interface SurveyOnclickListener{
        void seeDetailsClick(Post post);
        void feedBackClick(Post post,String feedback);
        void seeSentimentAnalysisOfOpinions(Post post);
    }
    public void setOnclickListener(SurveyOnclickListener surveyOnclickListener)
    {
        this.surveyOnclickListener=surveyOnclickListener;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_for_post,parent,false);
        return new viewholder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Post posts=list.get(position);
        holder.title.setText(posts.getTitle());
        if (posts.getTimestamp()!=null)
        {
            String timeAgo=(String) DateUtils.getRelativeTimeSpanString(posts.getTimestamp().getSeconds()*1000);
            holder.date.setText(timeAgo);
        }
        holder.category.setText(posts.getCategory());
        Glide.with(context).load(posts.getImage()).into(holder.postImg);

        holder.description.setText(posts.getDescription());
        holder.description.setMovementMethod(new ScrollingMovementMethod());
        holder.seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surveyOnclickListener.seeDetailsClick(posts);
            }
        });
        holder.feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View customLayout = LayoutInflater.from(context).inflate(R.layout.feedbacklayout,null);
                EditText feedback=customLayout.findViewById(R.id.feedbacktext);
                Button submit=customLayout.findViewById(R.id.submitFeedbackbtn);
                Button cancle=customLayout.findViewById(R.id.feedbackCanclebtn);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(customLayout);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        surveyOnclickListener.feedBackClick(posts,feedback.getText().toString());
                        alertDialog.dismiss();
                    }
                });
                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }
        });
        holder.sentiment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surveyOnclickListener.seeSentimentAnalysisOfOpinions(posts);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewholder extends RecyclerView.ViewHolder {
        TextView title,description,date,category;
        Button seeAll,feedback,sentiment;
        ImageView postImg;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.postTitle);
            description=itemView.findViewById(R.id.postDescription);
            date=itemView.findViewById(R.id.timeAgo);
            category=itemView.findViewById(R.id.postCategory);
            seeAll=itemView.findViewById(R.id.seeAll);
            postImg=itemView.findViewById(R.id.postImage);
            feedback=itemView.findViewById(R.id.feedback);
            sentiment=itemView.findViewById(R.id.sentimentanalysisBtn);
        }
    }
}
