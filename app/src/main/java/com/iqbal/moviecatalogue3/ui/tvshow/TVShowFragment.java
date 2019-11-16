package com.iqbal.moviecatalogue3.ui.tvshow;


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
import com.iqbal.moviecatalogue3.adapter.TVShowAdapter;
import com.iqbal.moviecatalogue3.model.TVShow;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowFragment extends Fragment {

    private TVShowAdapter tvShowAdapter;
    private TVShowViewModel tvShowViewModel;
    private ProgressBar progressBar;

    public TVShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        tvShowAdapter = new TVShowAdapter();
        View view = inflater.inflate(R.layout.fragment_tvshow, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_tvshow);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(tvShowAdapter);
        progressBar = view.findViewById(R.id.progressBar);
        tvShowViewModel = ViewModelProviders.of(this).get(TVShowViewModel.class);
        tvShowViewModel.getTVShow().observe(this, getTVShow);
        tvShowViewModel.setTVShow("EXTRA_TV_SHOW");
        showProgress(true);
        return view;
    }

    private Observer<ArrayList<TVShow>> getTVShow = new Observer<ArrayList<TVShow>>() {
        @Override
        public void onChanged(ArrayList<TVShow> tvShows) {
            if (tvShows != null) {
                tvShowAdapter.setTvShows(tvShows);
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
