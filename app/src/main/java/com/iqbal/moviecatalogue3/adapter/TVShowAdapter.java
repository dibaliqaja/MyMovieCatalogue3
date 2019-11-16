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
import com.iqbal.moviecatalogue3.activity.TVShowDetailActivity;
import com.iqbal.moviecatalogue3.model.TVShow;

import java.util.ArrayList;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowViewViewHolder> {

    private ArrayList<TVShow> tvShows = new ArrayList<>();
    public void setTvShows(ArrayList<TVShow> i) {
        tvShows.clear();
        tvShows.addAll(i);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TVShowViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_tv, parent, false);
        return new TVShowViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowViewViewHolder holder, int position) {
        holder.bind(tvShows.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    public class TVShowViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvName, tvDate, tvOverview, tvRating;
        ImageView imgPhoto;

        public TVShowViewViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_title);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            tvOverview = itemView.findViewById(R.id.tv_item_overview);
            tvRating = itemView.findViewById(R.id.tv_item_rating);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            itemView.setOnClickListener(this);
        }

        void bind(TVShow tvShow) {
            String link = "https://image.tmdb.org/t/p/w185" + tvShow.getPhoto();
            tvName.setText(tvShow.getName());
            tvDate.setText(tvShow.getFirst_air_date());
            tvOverview.setText(tvShow.getOverview());
            tvRating.setText(Double.toString(tvShow.getRating()));
            Glide.with(itemView.getContext())
                    .load(link)
                    .dontAnimate()
                    .into(imgPhoto);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            TVShow tvShow = tvShows.get(pos);
            Intent intent = new Intent(itemView.getContext(), TVShowDetailActivity.class);
            intent.putExtra(TVShowDetailActivity.EXTRA_TV_SHOW, tvShow);
            itemView.getContext().startActivity(intent);
        }
    }
}
