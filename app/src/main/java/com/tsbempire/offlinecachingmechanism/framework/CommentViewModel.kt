package com.tsbempire.offlinecachingmechanism.framework

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tsbempire.core.data.Comment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val saved = MutableLiveData<Boolean>()
    val currentComment = MutableLiveData<Comment?>()

    fun saveComment(comment: Comment) {
        coroutineScope.launch {
            useCases.addComment(comment)
            saved.postValue(true)
        }
    }

    fun getComment(id: Long) {
        coroutineScope.launch {
            val comment = useCases.getComment(id)
            currentComment.postValue(comment)
        }
    }

    fun deleteComment(comment: Comment) {
        coroutineScope.launch {
            useCases.removeComment(comment)
            saved.postValue(true)
        }
    }
}