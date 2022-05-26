package com.tsbempire.offlinecachingmechanism.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tsbempire.core.data.Comment

@Entity(tableName = "comment")
data class CommentEntity(
    val comment: String,
    @ColumnInfo(name = "creation_date")
    val creationTime: Long,
    @ColumnInfo(name = "update_time")
    val updateTime: Long,
    /// 0 not synced, 1 synced, 2 failed while syncing
    val isSync: Int = 0,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
) {
    companion object {
        fun fromComment(comment: Comment) = CommentEntity(
            comment.comment,
            comment.creationTime,
            comment.updateTime,
            comment.isSync,
            comment.id
        )

        fun copyComment(
            isSync: Int,
            updateTime: Long,
            creationTime: Long,
            comment: String,
            id: Long
        ) = CommentEntity(
            comment, creationTime, updateTime, isSync, id
        )
    }

    fun toComment() = Comment(comment, creationTime, updateTime, isSync, id)

    fun toMap() = hashMapOf<String, Any>(
        "id" to id,
        "comment" to comment,
        "creationTime" to creationTime,
        "updateTime" to updateTime,
        // isSync not required on cloud
        //"isSync" to isSync
    )
}