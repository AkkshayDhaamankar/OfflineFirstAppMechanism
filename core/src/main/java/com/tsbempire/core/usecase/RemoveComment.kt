package com.tsbempire.core.usecase

import com.tsbempire.core.data.Comment
import com.tsbempire.core.repository.CommentRepository

class RemoveComment(private val commentRepository: CommentRepository) {
    suspend operator fun invoke(comment: Comment) = commentRepository.removeComment(comment)
}