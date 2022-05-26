package com.tsbempire.core.data

data class Comment(
    var comment: String,
    var creationTime: Long,
    var updateTime: Long,
    var isSync: Int = 0,
    var id: Long = 0L,
)