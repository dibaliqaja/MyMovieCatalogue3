package com.iqbal.moviecatalogue3.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iqbal.moviecatalogue3.R;
import com.iqbal.moviecatalogue3.model.TVShow;

public class TVShowDetailActivity extends AppCompatActivity {

    public static final String EXTRA_TV_SHOW = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow_detail);

        final ImageView imgPhoto;
        final TextView tvName, tvRating, tvPopularity, tvCount, tvOverview;
        final ProgressBar progressBar;

        tvName = findViewById(R.id.tv_title);
        tvRating = findViewById(R.id.tv_rating);
        tvPopularity = findViewById(R.id.tv_popularity);
        tvCount = findViewById(R.id.tv_votecount);
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
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        TVShow tvShow = getIntent().getParcelableExtra(EXTRA_TV_SHOW);
                        String rat = Double.toString(tvShow.getRating());
                        String pop = Double.toString(tvShow.getPopularity());
                        String link = "https://image.tmdb.org/t/p/w185" + tvShow.getPhoto();
                        tvName.setText(tvShow.getName());
                        tvRating.setText(rat);
                        tvPopularity.setText(pop);
                        tvCount.setText(tvShow.getVote_count());
                        tvOverview.setText(tvShow.getOverview());
                        Glide.with(TVShowDetailActivity.this)
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
