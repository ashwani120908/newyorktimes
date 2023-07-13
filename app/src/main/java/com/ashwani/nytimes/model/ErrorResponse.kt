package com.ashwani.nytimes.model

import com.ashwani.nytimes.model.Detail

data class ErrorResponse(
    val fault: Fault

)
data class Fault(
    val detail: Detail,
    val faultstring: String
)

