package com.ashwani.nytimes.model

import com.ashwani.nytimes.utils.Constants

data class SectionedList(
    var viewType: ListViewType?,
    var headerType: Constants.PostType?,
    var result: Result?
)

enum class ListViewType {
    HEADER,
    ITEM
}
