package com.wold.retrofit2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceholderApi {

    @GET ("posts")
    Call<List<Post>> getposts();

    @GET ("posts/2/comments")
    Call<List<Comment>> getcomments();


    @POST("posts")
    Call<Post> Createposts(@Body Post post);

}
