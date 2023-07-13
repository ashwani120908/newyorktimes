package com.ashwani.nytimes.model

import com.ashwani.nytimes.utils.Constants

data class PostsResponse(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String,
    var postType: Constants.PostType
)