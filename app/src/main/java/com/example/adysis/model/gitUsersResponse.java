package com.example.adysis.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class gitUsersResponse {
    @SerializedName("total_count")
    public int totalCount;
    @SerializedName("items")
    public List<gitUser> users;

}
