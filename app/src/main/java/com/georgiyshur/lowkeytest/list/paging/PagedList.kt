package com.georgiyshur.lowkeytest.list.paging

internal data class PagedList<T>(
    val data: List<T>,
    val nextPage: String?,
)
