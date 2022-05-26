package com.tsbempire.offlinecachingmechanism.framework

import com.tsbempire.core.usecase.*

data class UseCases(
    val addComment: AddComment,
    val getAllComments: GetAllComments,
    val getComment: GetComment,
    val updateComment: UpdateComment,
    val removeComment: RemoveComment
)