package com.iqbal.moviecatalogue3.ui.movies;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.iqbal.moviecatalogue3.R;
import com.iqbal.moviecatalogue3.adapter.MoviesAdapter;
import com.iqbal.moviecatalogue3.model.Movies;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {

    private MoviesAdapter moviesAdapter;
    private MoviesViewModel moviesViewModel;
    private ProgressBar progressBar;


    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        moviesAdapter = new MoviesAdapter();
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(moviesAdapter);
        progressBar = view.findViewById(R.id.progressBar);
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.getMovies().observe(this, getMovie);
        moviesViewModel.setMovies("EXTRA_MOVIE");
        showProgress(true);
        return view;
    }

    private Observer<ArrayList<Movies>> getMovie = new Observer<ArrayList<Movies>>() {
        @Override
        public void onChanged(ArrayList<Movies> movies) {
            if (movies != null) {
                moviesAdapter.setMovies(movies);
            }
            showProgress(false);
        }
    };

    private void showProgress(Boolean b) {
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
