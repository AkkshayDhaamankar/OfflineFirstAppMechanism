package com.tsbempire.core.repository

import com.tsbempire.core.data.Comment


class CommentRepository(
    private val dataSource: CommentDataSource, private val scheduleComment: ScheduleComment
) {
    suspend fun addComment(comment: Comment) {
        dataSource.add(comment)
        println("adding/updating comment ${comment.id}")
        scheduleComment.scheduleCommentTask(comment.id)
    }

    suspend fun getComment(id: Long) = dataSource.get(id)
    suspend fun getAllComments() = dataSource.getAll()
    suspend fun removeComment(comment: Comment) = dataSource.remove(comment)
    suspend fun updateComment(comment: Comment) = dataSource.update(comment)
}