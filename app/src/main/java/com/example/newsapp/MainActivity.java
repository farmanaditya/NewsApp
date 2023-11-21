package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.newsapp.databinding.ActivityMainBinding;
import com.example.newsapp.model.NewsResponse;
import com.example.newsapp.network.ApiClient;
import com.example.newsapp.network.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ApiService newsApiClient = ApiClient.getInstance();
        Call<NewsResponse> newsResponseCall = newsApiClient.getTopHeadlines("us", "9768f48a22d5466994aad0ce84ac7688");

        newsResponseCall.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<String> newsTitles = new ArrayList<>();
                    for (int i = 0; i < response.body().getArticles().size(); i++) {
                        newsTitles.add(response.body().getArticles().get(i).getTitle());
                        Log.d("MainActivity", "onResponse: " + response.body().getArticles().get(i).getTitle());
                    }

                    NewsAdapter newsAdapter = new NewsAdapter(response.body());
                    binding.rvNews.setAdapter(newsAdapter);
                    binding.rvNews.setHasFixedSize(true);
                    binding.rvNews.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

                t.printStackTrace();
            }
        });
    }
}
