package com.tsbempire.offlinecachingmechanism.framework.workmanager

import android.content.Context
import androidx.work.*
import com.tsbempire.core.repository.ScheduleComment

class TaskScheduler constructor(private val context: Context) : ScheduleComment {
    private val uploadDataConstraints =
        Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

    override fun scheduleCommentTask(id: Long) {
        val inputData = Data.Builder()
            .putLong("id", id)
            .build()
        val uploadWorkRequest =
            OneTimeWorkRequestBuilder<SyncWorkManager>().addTag("SyncWorkManager")
                .setConstraints(uploadDataConstraints).setInputData(inputData)
                .build()
        WorkManager.getInstance(context).enqueue(uploadWorkRequest)
    }


}