package com.tsbempire.core.repository

import com.tsbempire.core.data.Comment

interface CommentDataSource {
    suspend fun add(comment : Comment)
    suspend fun update(comment: Comment)
    suspend fun get(id:Long) : Comment?
    suspend fun getAll() : List<Comment>
    suspend fun remove(comment: Comment)
}