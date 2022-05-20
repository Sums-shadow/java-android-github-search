package com.example.adysis.model;

import com.google.gson.annotations.SerializedName;

public class gitUser {

    public int id;
    public String login;

    @SerializedName("avatar_url")
    public String avatarUrl;
    public int score;


}
