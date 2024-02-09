package com.example.insightcanvas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.insightcanvas.databinding.ActivityMainBinding;
import com.example.insightcanvas.databinding.ActivityPostDetailsBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import Adapters.CommentsAdapter;
import Models.Comments;

public class PostDetailsActivity extends AppCompatActivity {
    ActivityPostDetailsBinding binding;
    String pid,uid,postTitle,postDescription,postCategory,postImage;
    int pCnt=0,nCnt=0,nuCnt=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        binding= ActivityPostDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pid=getIntent().getStringExtra("pid");
        uid=getIntent().getStringExtra("uid");
        postCategory=getIntent().getStringExtra("postCategory");
        postTitle=getIntent().getStringExtra("postTitle");
        postImage=getIntent().getStringExtra("postImage");
        postDescription=getIntent().getStringExtra("postDescription");
        pCnt=getIntent().getIntExtra("pCnt",0);
        nCnt=getIntent().getIntExtra("nCnt",0);
        nuCnt=getIntent().getIntExtra("nuCnt",0);

        binding.surveyCategory.setText(postCategory);
        binding.surveyTitletxt.setText(postTitle);
        binding.surveyDetailsTxt.setText(postDescription);
        Glide.with(this).load(postImage).into(binding.surveyImageDetails);

        List<Comments>list=new ArrayList<>();
        FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
        CommentsAdapter adapter=new CommentsAdapter(this,list);
        binding.surveyOpinionsList.setAdapter(adapter);
        binding.surveyOpinionsList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false));


        firebaseFirestore.collection("Comments")
                .whereEqualTo("pid",pid)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        list.clear();
                        for (DocumentSnapshot snapshot:queryDocumentSnapshots.getDocuments())
                        {
                            Comments comments=snapshot.toObject(Comments.class);
                            list.add(comments);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PostDetailsActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

        binding.analysisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PostDetailsActivity.this,PostAnalysisActivity.class);
                intent.putExtra("positive",pCnt);
                intent.putExtra("negative",nCnt);
                intent.putExtra("neutral",nuCnt);
                startActivity(intent);
            }
        });
    }
}