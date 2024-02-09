package com.example.insightcanvas;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.insightcanvas.databinding.FragmentPostsBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import Adapters.PostAdapter;
import Models.Comments;
import Models.Post;
import Models.SentimentRequest;
import Models.SentimentResponse;
import Models.User;
import WorkingWithApis.ApiService;
import WorkingWithApis.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PostsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PostsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostsFragment newInstance(String param1, String param2) {
        PostsFragment fragment = new PostsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentPostsBinding binding;
    PostAdapter postAdapter;
    List<User> users = new ArrayList<>();
    List<Post> posts = new ArrayList<>();
    String category="Education",sortby="New to Old";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostsBinding.inflate(inflater, container, false);
        postAdapter = new PostAdapter(posts, getContext());
        binding.postRec.setAdapter(postAdapter);
        binding.postRec.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.FilterCategory, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.filterCategorySpinner.setAdapter(adapter);
        //Filter the Post according to Category
        binding.filterCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category=adapterView.getItemAtPosition(i).toString();
                if (!category.equals("All"))
                {
                    firebaseFirestore.collection("Posts")
                            .whereEqualTo("category",category)
                            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                    posts.clear();
                                    for (DocumentSnapshot snapshot : value.getDocuments()) {
                                        Post post = snapshot.toObject(Post.class);
                                        posts.add(post);
                                    }
                                    postAdapter.notifyDataSetChanged();
                                }
                            });
                }
                else
                {
                    firebaseFirestore.collection("Posts")
                            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                    posts.clear();
                                    for (DocumentSnapshot snapshot : value.getDocuments()) {
                                        Post post = snapshot.toObject(Post.class);
                                        posts.add(post);
                                    }
                                    postAdapter.notifyDataSetChanged();
                                }
                            });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.sortBytime, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.sortByTimeSpinner.setAdapter(adapter2);

        binding.sortByTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sortby=adapterView.getItemAtPosition(i).toString();
                if (sortby.equals("New to Old"))
                {
                    if (!category.equals("All"))
                    {
                        Query query=firebaseFirestore.collection("Posts")
                                .whereEqualTo("category",category)
                                .orderBy("timestamp", Query.Direction.ASCENDING);
                        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                posts.clear();
                                for (DocumentSnapshot snapshot:value.getDocuments())
                                {
                                    Post post=snapshot.toObject(Post.class);
                                    posts.add(post);
                                }
                                postAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                    else
                    {
                        Query query=firebaseFirestore.collection("Posts")
                                .orderBy("timestamp", Query.Direction.ASCENDING);
                        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                posts.clear();
                                for (DocumentSnapshot snapshot:value.getDocuments())
                                {
                                    Post post=snapshot.toObject(Post.class);
                                    posts.add(post);
                                }
                                postAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }
                else
                {
                    if (!category.equals("All"))
                    {
                        Query query=firebaseFirestore.collection("Posts")
                                .whereEqualTo("category",category)
                                .orderBy("timestamp", Query.Direction.DESCENDING);
                        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                posts.clear();
                                for (DocumentSnapshot snapshot:value.getDocuments())
                                {
                                    Post post=snapshot.toObject(Post.class);
                                    posts.add(post);
                                }
                                postAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                    else
                    {
                        Query query=firebaseFirestore.collection("Posts")
                                .orderBy("timestamp", Query.Direction.DESCENDING);
                        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                posts.clear();
                                for (DocumentSnapshot snapshot:value.getDocuments())
                                {
                                    Post post=snapshot.toObject(Post.class);
                                    posts.add(post);
                                }
                                postAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Sort the Posts according to time


        //Post Click Events
        postAdapter.setOnclickListener(new PostAdapter.SurveyOnclickListener() {
            @Override
            public void seeDetailsClick(Post post) {
                Intent intent=new Intent(getContext(),PostDetailsActivity.class);
                intent.putExtra("uid",post.getUid());
                intent.putExtra("pid",post.getPid());
                intent.putExtra("postCategory",post.getCategory());
                intent.putExtra("postTitle",post.getTitle());
                intent.putExtra("postDescription",post.getDescription());
                intent.putExtra("pCnt",post.getCntPositive());
                intent.putExtra("nCnt",post.getCntNegative());
                intent.putExtra("nuCnt",post.getCntNeutral());
                intent.putExtra("postImage",post.getImage());
                startActivity(intent);
            }

            @Override
            public void feedBackClick(Post post, String feedback) {
                if (!feedback.isEmpty())
                {
                    DocumentReference documentReference= FirebaseFirestore.getInstance().collection("Comments").document();
                    Comments comments=new Comments(post.getPid(),feedback);
                    documentReference.set(comments).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getContext(),"Thanks for your Opinion",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                String baseUrl = "http://antorsarker.pythonanywhere.com/";
                ApiService apiService = RetrofitClient.getClient(baseUrl).create(ApiService.class);
                Call<SentimentResponse> call = apiService.analyzeSentiment(new SentimentRequest(feedback));
                call.enqueue(new Callback<SentimentResponse>() {
                    @Override
                    public void onResponse(Call<SentimentResponse> call, Response<SentimentResponse> response) {
                        SentimentResponse sentimentResponse = response.body();
                        Toast.makeText(getContext(),sentimentResponse.getSentiment(),Toast.LENGTH_SHORT).show();
                        if (sentimentResponse.getSentiment().equals("positive"))
                        {
                            firebaseFirestore.collection("Posts")
                                    .document(post.getPid())
                                    .update("cntPositive", FieldValue.increment(1))
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                       else if (sentimentResponse.getSentiment().equals("negative")) {
                            firebaseFirestore.collection("Posts")
                                    .document(post.getPid())
                                    .update("cntNegative", FieldValue.increment(1))
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                       else
                        {
                            firebaseFirestore.collection("Posts")
                                    .document(post.getPid())
                                    .update("cntNeutral", FieldValue.increment(1))
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                    @Override
                    public void onFailure(Call<SentimentResponse> call, Throwable t) {
                        Toast.makeText(getContext(), t.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void seeSentimentAnalysisOfOpinions(Post post) {
                Intent intent=new Intent(getContext(),PostAnalysisActivity.class);
                intent.putExtra("positive",post.getCntPositive());
                intent.putExtra("negative",post.getCntNegative());
                intent.putExtra("neutral",post.getCntNeutral());
                startActivity(intent);
            }
        });
        return binding.getRoot();
    }
}