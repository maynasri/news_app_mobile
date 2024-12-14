package com.example.news_app_api;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news_app_api.API.ApiClient;
import com.example.news_app_api.API.ApiInterface;
import com.example.news_app_api.Adapters.NewsAdapter;
import com.example.news_app_api.Models.Article;
import com.example.news_app_api.Models.NewsApiResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<Article> articles = new ArrayList<>();
    private ProgressBar progressBar;
    private String category;
    private String apiKey;

    public NewsFragment(String category, String apiKey) {
        this.category = category;
        this.apiKey = apiKey;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NewsAdapter(articles, getContext());
        recyclerView.setAdapter(adapter);

        loadNews();
        return view;
    }

    private void loadNews() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<NewsApiResponse> call = apiInterface.getNews(apiKey, category);

        call.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    articles = response.body().getArticles();


                    adapter.notifyDataSetChanged();
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                showError();
            }
        });
    }

    private void showError() {
        Toast.makeText(getContext(), "Erreur de connexion", Toast.LENGTH_SHORT).show();
    }
}
