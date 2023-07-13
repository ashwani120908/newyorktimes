package com.ashwani.nytimes.network

import com.ashwani.nytimes.model.PostsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PostsApiService {

    @GET
    suspend fun getPosts(@Url url: String): Response<PostsResponse>
}