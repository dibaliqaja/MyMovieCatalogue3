package com.iqbal.moviecatalogue3.ui.tvshow;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iqbal.moviecatalogue3.model.TVShow;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TVShowViewModel extends ViewModel {
    //private static final String API_Key = "14ce33fe830d64779347edb08303bb8b";
    private MutableLiveData<ArrayList<TVShow>> listTV = new MutableLiveData<>();

    public void setTVShow(final String tvShow) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        final ArrayList<TVShow> list = new ArrayList<>();

        String link = "https://api.themoviedb.org/3/discover/tv?api_key=14ce33fe830d64779347edb08303bb8b&language=en-US";
        asyncHttpClient.get(link, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        TVShow tvShow1 = new TVShow(jsonObject1);
                        list.add(tvShow1);
                    }
                    listTV.postValue(list);
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

    public LiveData<ArrayList<TVShow>> getTVShow() {
        return listTV;
    }
}
