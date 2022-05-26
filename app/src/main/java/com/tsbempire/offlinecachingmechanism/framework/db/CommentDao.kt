package com.tsbempire.offlinecachingmechanism.framework.db

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE


@Dao
interface CommentDao {
    @Insert(onConflict = REPLACE)
    suspend fun addCommentEntity(commentEntity: CommentEntity)

    @Update
    suspend fun updateCommentEntity(commentEntity: CommentEntity)

    @Query("Select * FROM comment WHERE id = :id")
    suspend fun getCommentEntity(id : Long) : CommentEntity?

    @Query("Select * FROM comment")
    suspend fun getAllCommentEntities(): List<CommentEntity>

    @Delete
    suspend fun removeCommentEntity(commentEntity: CommentEntity)
}