package com.example.news_app_api;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

public class NewDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView titleTextView, descriptionTextView, moreDetailsTextView;
    private ProgressBar progressBar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);

        imageView = findViewById(R.id.detailImage);
        titleTextView = findViewById(R.id.detailTitle);
        descriptionTextView = findViewById(R.id.detailDescription);
        progressBar = findViewById(R.id.progressBar);
        moreDetailsTextView = findViewById(R.id.moreDetails);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        String title = getIntent().getStringExtra("title");
        String imageUrl = getIntent().getStringExtra("img");
        String description = getIntent().getStringExtra("description");
        final String url = getIntent().getStringExtra("url");

        titleTextView.setText(title);
        descriptionTextView.setText(description);
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(this)
                    .load(imageUrl)
                    .into(imageView);
        }

        if (url != null && !url.isEmpty()) {
            moreDetailsTextView.setText("More details");
            moreDetailsTextView.setClickable(true);
            moreDetailsTextView.setOnClickListener(v -> openUrl(url));
        } else {
            moreDetailsTextView.setVisibility(View.GONE);
        }

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_share) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, title + "\n" + url);
                startActivity(Intent.createChooser(shareIntent, "Partager l'article via"));
                return true;
            }
            return false;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return true;
    }

    private void openUrl(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}
