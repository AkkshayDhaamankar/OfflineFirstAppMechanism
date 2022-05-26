package com.tsbempire.core.usecase

import com.tsbempire.core.data.Comment
import com.tsbempire.core.repository.CommentRepository

class UpdateComment(private val commentRepository: CommentRepository) {
    suspend operator fun invoke(comment: Comment) = commentRepository.updateComment(comment)
}