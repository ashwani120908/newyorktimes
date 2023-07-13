package com.ashwani.nytimes.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ashwani.nytimes.NyTimesApplication
import com.ashwani.nytimes.R
import com.ashwani.nytimes.model.ErrorResponse
import com.ashwani.nytimes.model.PostsResponse
import com.ashwani.nytimes.network.NetworkResult
import com.ashwani.nytimes.network.PostsApiService
import com.ashwani.nytimes.utils.Constants
import com.google.gson.Gson
import org.json.JSONObject
import javax.inject.Inject


open class PostsRepository @Inject constructor(private val postsApiService: PostsApiService) {

    private val _postsResponseLiveData = MutableLiveData<NetworkResult<PostsResponse>>()
    val postsResponse: LiveData<NetworkResult<PostsResponse>>
        get() = _postsResponseLiveData

    suspend fun fetchPosts(url: String, postType: Constants.PostType) {
        _postsResponseLiveData.postValue(NetworkResult.Loading())
        val response = postsApiService.getPosts(url)
        if (response.isSuccessful && response.body() != null) {
            response.body()!!.postType = postType
            _postsResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText()).toString()
            val errorResponse = Gson().fromJson(errorObj, ErrorResponse::class.java)
            _postsResponseLiveData.postValue(NetworkResult.Error(errorResponse.fault.faultstring))
        } else {
            _postsResponseLiveData.postValue(NetworkResult.Error(NyTimesApplication.appContext.getString(R.string.generic_error)))
        }
    }
}