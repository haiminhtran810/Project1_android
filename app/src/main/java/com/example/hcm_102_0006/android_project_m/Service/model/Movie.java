package com.example.hcm_102_0006.android_project_m.Service.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hcm-102-0006 on 21/11/2017.
 */
public class Movie {
    private String id;
    private float vote_average;
    private String title;
    private String overview;
    private String poster_path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
