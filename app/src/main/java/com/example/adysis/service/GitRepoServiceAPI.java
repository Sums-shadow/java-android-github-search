package com.example.adysis.service;

import  com.example.adysis.model.gitUsersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitRepoServiceAPI {
    @GET("search/users")
    public Call<gitUsersResponse> searchUser(@Query("q") String query);

}
