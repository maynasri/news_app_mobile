package com.example.news_app_api;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.graphics.drawable.Drawable;  // Pour Drawable
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.Target;  // Pour Target
import com.bumptech.glide.Glide;  // Pour Glide
import com.bumptech.glide.load.DataSource;  // Pour DataSource
import com.bumptech.glide.load.engine.GlideException;  // Pour GlideException
import com.bumptech.glide.request.RequestListener;  // Pour RequestListener

public class NewDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView titleTextView, descriptionTextView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);

        // Initialiser les vues
        imageView = findViewById(R.id.detailImage);
        titleTextView = findViewById(R.id.detailTitle);
        descriptionTextView = findViewById(R.id.detailDescription);
        progressBar = findViewById(R.id.progressBar);

        // Récupérer les informations envoyées par l'Intent
        String title = getIntent().getStringExtra("title");
        String imageUrl = getIntent().getStringExtra("img");
        String description = getIntent().getStringExtra("description");

        // Afficher le titre et la description
        titleTextView.setText(title);
        descriptionTextView.setText(description);

        // Charger l'image avec Glide et afficher la ProgressBar jusqu'à ce qu'elle soit chargée
        if (imageUrl != null && !imageUrl.isEmpty()) {
            progressBar.setVisibility(ProgressBar.VISIBLE); // Afficher la ProgressBar

            Glide.with(this)
                    .load(imageUrl)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            progressBar.setVisibility(ProgressBar.GONE); // Cacher la ProgressBar si l'image échoue à se charger
                            return false; // Retirer le gestionnaire d'erreur
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            progressBar.setVisibility(ProgressBar.GONE); // Cacher la ProgressBar une fois l'image chargée
                            return false; // Laisser Glide gérer le chargement
                        }
                    })
                    .into(imageView); // Charger l'image dans ImageView
        } else {
            progressBar.setVisibility(ProgressBar.GONE); // Cacher la ProgressBar si aucune image n'est fournie
        }
    }
}