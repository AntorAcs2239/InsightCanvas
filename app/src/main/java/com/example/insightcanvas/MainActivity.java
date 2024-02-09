package com.example.insightcanvas;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import com.example.insightcanvas.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import me.ibrahimsn.lib.OnItemSelectedListener;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, new PostsFragment());
        transaction.commit();
        binding.smoothBottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (i) {
                    case 0:
                        transaction.replace(R.id.frameLayout, new HomeFragment());
                        break;
                    case 1:
                        transaction.replace(R.id.frameLayout, new PostsFragment());
                        break;
                    case 2:
                        transaction.replace(R.id.frameLayout, new CreatePostFragment());
                        break;
                    case 3:
                        transaction.replace(R.id.frameLayout, new ProfileFragment());
                        break;
                }
                transaction.commit();
                return true;
            }
        });
//        List<Student>list=new ArrayList<>();
//        list.add(new Student("Student 1","CSE"));
//        list.add(new Student("Student 2","EEE"));
//        list.add(new Student("Student 1","CSE"));
//        list.add(new Student("Student 2","EEE"));
//        list.add(new Student("Student 1","CSE"));
//        list.add(new Student("Student 2","EEE"));
//        list.add(new Student("Student 1","CSE"));
//        list.add(new Student("Student 2","EEE"));
//        list.add(new Student("Student 1","CSE"));
//        list.add(new Student("Student 2","EEE"));
//        adapter=new StudentAdapter(list,MainActivity.this);
//        binding.stuRec.setAdapter(adapter);
//        binding.stuRec.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
//        String baseUrl ="http://antorsarker.pythonanywhere.com/";
//        ApiService apiService = RetrofitClient.getClient(baseUrl).create(ApiService.class);
//        Call<SentimentResponse>call = apiService.analyzeSentiment(new SentimentRequest("Hello"));
//        call.enqueue(new Callback<SentimentResponse>() {
//            @Override
//            public void onResponse(Call<SentimentResponse> call, Response<SentimentResponse> response) {
//                SentimentResponse sentimentResponse=response.body();
//                Toast.makeText(MainActivity.this,sentimentResponse.getSentiment()+sentimentResponse.getPositive_percentage(),Toast.LENGTH_LONG).show();
//            }
//            @Override
//            public void onFailure(Call<SentimentResponse> call, Throwable t) {
//                Toast.makeText(MainActivity.this,t.toString(),Toast.LENGTH_LONG).show();
//            }
//        });
    }
}