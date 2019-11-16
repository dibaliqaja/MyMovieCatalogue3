package com.iqbal.moviecatalogue3.ui.movies;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iqbal.moviecatalogue3.model.Movies;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MoviesViewModel extends ViewModel {
    //private static final String API_Key = "14ce33fe830d64779347edb08303bb8b";
    private MutableLiveData<ArrayList<Movies>> listMov = new MutableLiveData<>();

    public void setMovies(final String movies) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        final ArrayList<Movies> list = new ArrayList<>();

        String link = "https://api.themoviedb.org/3/discover/movie?api_key=14ce33fe830d64779347edb08303bb8b&language=en-US";
        asyncHttpClient.get(link, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        Movies movies1 = new Movies(jsonObject1);
                        list.add(movies1);
                    }
                    listMov.postValue(list);
                } catch (Exception ex) {
                    Log.d("Exception", ex.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<Movies>> getMovies() {
        return listMov;
    }
}
