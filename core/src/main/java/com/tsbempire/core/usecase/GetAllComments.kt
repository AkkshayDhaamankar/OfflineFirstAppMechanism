package com.tsbempire.core.usecase

import com.tsbempire.core.repository.CommentRepository

class GetAllComments(private val commentRepository: CommentRepository) {
    suspend operator fun invoke() = commentRepository.getAllComments()
}