package com.ashwani.nytimes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashwani.nytimes.repository.PostsRepository
import com.ashwani.nytimes.model.PostsResponse
import com.ashwani.nytimes.network.NetworkResult
import com.ashwani.nytimes.utils.Constants
import com.ashwani.nytimes.utils.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val postsRepository: PostsRepository) : ViewModel() {

    val postsResponseLiveData: LiveData<NetworkResult<PostsResponse>>
        get() = postsRepository.postsResponse

    fun fetchPost() {
        viewModelScope.launch {

            listOf(
                launch { postsRepository.fetchPosts(getUrl(Constants.PostType.MOST_EMAILED), Constants.PostType.MOST_EMAILED) },
                launch { postsRepository.fetchPosts(getUrl(Constants.PostType.MOST_VIEWED), Constants.PostType.MOST_VIEWED) },
                launch { postsRepository.fetchPosts(getUrl(Constants.PostType.MOST_SHARED), Constants.PostType.MOST_SHARED) }
            ).joinAll()
        }
    }

    fun getUrl(postType: Constants.PostType): String {
        return when (postType) {
            Constants.PostType.MOST_EMAILED -> Constants.BASE_URL + Constants.MOST_EMAILED_URL
            Constants.PostType.MOST_VIEWED -> Constants.BASE_URL + Constants.MOST_VIEWED_URL
            Constants.PostType.MOST_SHARED -> Constants.BASE_URL + Constants.MOST_SHARED_URL
        }
    }
}