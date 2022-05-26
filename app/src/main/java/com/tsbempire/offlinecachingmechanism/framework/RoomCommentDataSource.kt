package com.tsbempire.offlinecachingmechanism.framework

import android.content.Context
import android.util.Log
import com.tsbempire.core.data.Comment
import com.tsbempire.core.repository.CommentDataSource
import com.tsbempire.offlinecachingmechanism.framework.db.CommentEntity
import com.tsbempire.offlinecachingmechanism.framework.db.DatabaseService

class RoomCommentDataSource(context: Context) : CommentDataSource {
    private val commentDao = DatabaseService.getInstance(context).commentDao()
    override suspend fun add(comment: Comment) {
        Log.d("RoomCommentDataSource","Comment Added");
        commentDao.addCommentEntity(CommentEntity.fromComment(comment))
    }

    override suspend fun update(comment: Comment) =
        commentDao.updateCommentEntity(CommentEntity.fromComment(comment))

    override suspend fun get(id: Long): Comment? = commentDao.getCommentEntity(id)?.toComment()

    override suspend fun getAll(): List<Comment> =
        commentDao.getAllCommentEntities().map { it.toComment() }

    override suspend fun remove(comment: Comment) =
        commentDao.removeCommentEntity(CommentEntity.fromComment(comment))
}