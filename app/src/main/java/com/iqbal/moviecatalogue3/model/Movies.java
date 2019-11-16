package com.iqbal.moviecatalogue3.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Movies implements Parcelable {
    private String title;
    private String date;
    private String overview;
    private Double rating;
    private Double popularity;
    private String vote_count;
    private String language;
    private String photo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.date);
        dest.writeString(this.overview);
        dest.writeValue(this.rating);
        dest.writeValue(this.popularity);
        dest.writeString(this.vote_count);
        dest.writeString(this.language);
        dest.writeString(this.photo);
    }

    public Movies(JSONObject jsonObject) {
        try {
            String title = jsonObject.getString("title");
            String date = jsonObject.getString("release_date");
            String overview = jsonObject.getString("overview");
            Double rating = jsonObject.getDouble("vote_average");
            Double popularity = jsonObject.getDouble("popularity");
            String vote_count = jsonObject.getString("vote_count");
            String language = jsonObject.getString("original_language");
            String photo = jsonObject.getString("poster_path");

            this.title = title;
            this.date = date;
            this.rating = rating;
            this.overview = overview;
            this.popularity = popularity;
            this.vote_count = vote_count;
            this.language = language;
            this.photo = photo;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected Movies(Parcel in) {
        this.title = in.readString();
        this.date = in.readString();
        this.overview = in.readString();
        this.rating = (Double) in.readValue(Double.class.getClassLoader());
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.vote_count = in.readString();
        this.language = in.readString();
        this.photo = in.readString();
    }

    public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            return new Movies(source);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}
