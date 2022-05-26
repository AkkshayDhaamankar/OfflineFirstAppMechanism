package com.tsbempire.core.usecase

import com.tsbempire.core.repository.CommentRepository

class GetComment(private val commentRepository: CommentRepository) {
    suspend operator fun invoke(id: Long) = commentRepository.getComment(id)
}