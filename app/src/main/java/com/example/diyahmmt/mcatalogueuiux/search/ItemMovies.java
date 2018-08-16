package com.example.diyahmmt.mcatalogueuiux.search;

import org.json.JSONObject;

/**
 * Created by ACER on 29-07-2018.
 */

public class ItemMovies {
    private String titleMovie;
    private String descriptionMovie;
    private String dateMovie;
    private String imageMovie;
    private String rate_countMovie;
    private String rateMovie;

    public ItemMovies(JSONObject object){
        try {

            String title = object.getString("title");
            String deskripsi = object.getString("overview");
            String rdate = object.getString("release_date");
            String image = object.getString("poster_path");
            String rate_count = object.getString("vote_count");
            String rate = object.getString("vote_average");

            this.titleMovie = title;
            this.descriptionMovie = deskripsi;
            this.dateMovie = rdate;
            this.imageMovie = image;
            this.rate_countMovie = rate_count;
            this.rateMovie = rate;

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getMovTitle() {
        return titleMovie;
    }

    public String getMovDeskripsi() {
        return descriptionMovie;
    }

    public String getMovDate() {
        return dateMovie;
    }

    public String getMovImage() {
        return imageMovie;
    }

    public String getMovRateCount() {
        return rate_countMovie;
    }

    public String getMovRate() {
        return rateMovie;
    }
}
