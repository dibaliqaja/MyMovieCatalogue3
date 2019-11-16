package com.iqbal.moviecatalogue3.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.iqbal.moviecatalogue3.R;
import com.iqbal.moviecatalogue3.model.Movies;

public class MoviesDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_detail);

        final ImageView imgPhoto;
        final TextView tvTitle, tvRating, tvPopularity, tvCount, tvLang, tvOverview;
        final ProgressBar progressBar;

        tvTitle = findViewById(R.id.tv_title);
        tvRating = findViewById(R.id.tv_rating);
        tvPopularity = findViewById(R.id.tv_popularity);
        tvCount = findViewById(R.id.tv_vote);
        tvLang = findViewById(R.id.tv_language);
        tvOverview = findViewById(R.id.tv_overview);
        imgPhoto = findViewById(R.id.img_photo);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Movies movies = getIntent().getParcelableExtra(EXTRA_MOVIE);
                        String rat = Double.toString(movies.getRating());
                        String pop = Double.toString(movies.getPopularity());
                        String link = "https://image.tmdb.org/t/p/w185" + movies.getPhoto();
                        tvTitle.setText(movies.getTitle());
                        tvRating.setText(rat);
                        tvPopularity.setText(pop);
                        tvCount.setText(movies.getVote_count());
                        tvLang.setText(movies.getLanguage());
                        tvOverview.setText(movies.getOverview());
                        Glide.with(MoviesDetailActivity.this)
                                .load(link)
                                .dontAnimate()
                                .into(imgPhoto);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();
    }
}
