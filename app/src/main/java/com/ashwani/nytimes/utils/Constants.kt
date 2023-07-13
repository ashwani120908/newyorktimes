package com.ashwani.nytimes.utils

object Constants {

    const val TAG = "NyTimes"
    const val API_KEY = "MeaumYt2Nop1FPjoiDAGUCqXAYZoooLp"

    const val BASE_URL = "https://api.nytimes.com/svc/mostpopular/v2/"
    const val MOST_EMAILED_URL = "emailed/1.json?api-key=$API_KEY"
    const val MOST_SHARED_URL = "shared/1/facebook.json?api-key=$API_KEY"
    const val MOST_VIEWED_URL = "viewed/7.json?api-key=$API_KEY"
    const val LIST_TYPE_HEADER = 1
    const val LIST_TYPE_ITEM = 2


    enum class PostType {
        MOST_EMAILED,
        MOST_VIEWED,
        MOST_SHARED
    }
}