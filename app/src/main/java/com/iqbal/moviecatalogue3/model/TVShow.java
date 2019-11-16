package com.iqbal.moviecatalogue3.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class TVShow implements Parcelable {
    private String name;
    private String first_air_date;
    private String overview;
    private Double rating;
    private String vote_count;
    private Double popularity;
    private String photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
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

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
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
        dest.writeString(this.name);
        dest.writeString(this.first_air_date);
        dest.writeString(this.overview);
        dest.writeValue(this.rating);
        dest.writeString(this.vote_count);
        dest.writeValue(this.popularity);
        dest.writeString(this.photo);
    }

    public TVShow(JSONObject jsonObject) {
        try {
            String name = jsonObject.getString("original_name");
            String date = jsonObject.getString("first_air_date");
            String overview = jsonObject.getString("overview");
            Double rating = jsonObject.getDouble("vote_average");
            String vote = jsonObject.getString("vote_count");
            Double popularity = jsonObject.getDouble("popularity");
            String photo = jsonObject.getString("poster_path");

            this.name = name;
            this.first_air_date = date;
            this.overview = overview;
            this.rating = rating;
            this.vote_count = vote;
            this.popularity = popularity;
            this.photo = photo;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected TVShow(Parcel in) {
        this.name = in.readString();
        this.first_air_date = in.readString();
        this.overview = in.readString();
        this.rating = (Double) in.readValue(Double.class.getClassLoader());
        this.vote_count = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.photo = in.readString();
    }

    public static final Parcelable.Creator<TVShow> CREATOR = new Parcelable.Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel source) {
            return new TVShow(source);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };
}
