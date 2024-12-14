package com.example.news_app_api;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.news_app_api.Adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String API_KEY = "e402dd41967b43b6b90f9421cf5db6cd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.include);
        viewPager = findViewById(R.id.fragmentcontainer);

        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new NewsFragment("general", API_KEY), "All");
        adapter.addFragment(new NewsFragment("sports", API_KEY), "Sports");
        adapter.addFragment(new NewsFragment("health", API_KEY), "Health");
        adapter.addFragment(new NewsFragment("science", API_KEY), "Science");
        adapter.addFragment(new NewsFragment("entertainment", API_KEY), "Entertainment");
        adapter.addFragment(new NewsFragment("technology", API_KEY), "Technology");
        viewPager.setAdapter(adapter);
    }



}