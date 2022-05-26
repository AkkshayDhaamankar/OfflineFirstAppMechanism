package com.tsbempire.offlinecachingmechanism.framework

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.tsbempire.core.data.Comment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val useCases: UseCases,
    application: Application
) : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val comments = MutableLiveData<List<Comment>>()

    /// to listen updates of work-manager on ListFragment
    private val workManager = WorkManager.getInstance(application)
    internal val workInfo: LiveData<List<WorkInfo>> =
        workManager.getWorkInfosByTagLiveData("SyncWorkManager")

    fun getAllComments() {
        coroutineScope.launch {
            val commentList = useCases.getAllComments()
            comments.postValue(commentList)
        }
    }
}