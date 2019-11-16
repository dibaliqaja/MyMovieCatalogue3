package com.iqbal.moviecatalogue3.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iqbal.moviecatalogue3.R;
import com.iqbal.moviecatalogue3.activity.MoviesDetailActivity;
import com.iqbal.moviecatalogue3.model.Movies;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewViewHolder> {

    private ArrayList<Movies> moviesD = new ArrayList<>();
    public void setMovies(ArrayList<Movies> i) {
        moviesD.clear();
        moviesD.addAll(i);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_movies, parent, false);
        return new MovieViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewViewHolder holder, int position) {
        holder.bind(moviesD.get(position));
    }

    @Override
    public int getItemCount() {
        return moviesD.size();
    }

    public class MovieViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgPhoto;
        TextView tvTitle, tvDate, tvOverview, tvRating;

        public MovieViewViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            tvOverview = itemView.findViewById(R.id.tv_item_overview);
            tvRating = itemView.findViewById(R.id.tv_item_rating);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            itemView.setOnClickListener(this);
        }

        void bind(Movies movies) {
            String rat = Double.toString(movies.getRating());
            String url_photo = "https://image.tmdb.org/t/p/w185" + movies.getPhoto();
            tvTitle.setText(movies.getTitle());
            tvDate.setText(movies.getDate());
            tvOverview.setText(movies.getOverview());
            tvRating.setText(rat);
            Glide.with(itemView.getContext())
                    .load(url_photo)
                    .dontAnimate()
                    .into(imgPhoto);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Movies movies = moviesD.get(pos);
            Intent intent = new Intent(itemView.getContext(), MoviesDetailActivity.class);
            intent.putExtra(MoviesDetailActivity.EXTRA_MOVIE, movies);
            itemView.getContext().startActivity(intent);
        }
    }
}
