package com.msa.cityfy.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;

import com.msa.cityfy.Adapters.NewsAdapter;
import com.msa.cityfy.R;
import com.msa.cityfy.Model.News.Utils;
import com.msa.cityfy.api.ApiClient;
import com.msa.cityfy.api.ApiInterface;
import com.msa.cityfy.Model.News.Article;
import com.msa.cityfy.Model.News.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsActivity extends AppCompatActivity {

    public static final String API_KEY = "bad2d9f349154876a31d4e6e80f8503c";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles = new ArrayList<>();
    private NewsAdapter newsAdapter;
    private String TAG= NewsActivity.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerView = findViewById(R.id.RecyclerView);
        layoutManager = new LinearLayoutManager(NewsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        LoadJson();

    }

    public void LoadJson(){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        String country = Utils.getCountry();

        Call<News> call;
        call = apiInterface.getNews(country, API_KEY);

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(response.isSuccessful() && response.body().getArticle()!=null){


                    if(!articles.isEmpty()){
                        articles.clear();

                    }

                    articles = response.body().getArticle();
                    newsAdapter = new NewsAdapter(articles , NewsActivity.this);
                    recyclerView.setAdapter(newsAdapter);
                    newsAdapter.notifyDataSetChanged();

                }
                else{
                    Toast.makeText(NewsActivity.this, "Empty",Toast.LENGTH_SHORT).show();




                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });


    }
}
