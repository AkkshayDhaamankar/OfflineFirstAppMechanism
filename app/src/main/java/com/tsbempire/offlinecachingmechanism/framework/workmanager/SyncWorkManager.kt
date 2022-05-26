package com.tsbempire.offlinecachingmechanism.framework.workmanager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.tsbempire.network.FirestoreHelper
import com.tsbempire.offlinecachingmechanism.framework.db.CommentEntity
import com.tsbempire.offlinecachingmechanism.framework.db.DatabaseService
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class SyncWorkManager @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    var firestoreHelper: FirestoreHelper,
    var broadcastHelper: BroadcastHelper
) : CoroutineWorker(appContext, workerParams) {
    private val commentDao = DatabaseService.getInstance(appContext).commentDao()

    override suspend fun doWork(): Result {
        return try {
            val id = inputData.getLong("id", 0L)
            if (id != 0L) {
                withContext(Dispatchers.IO) {
                    val comment = commentDao.getCommentEntity(id)
                    syncComment(comment!!)
                }
            }
            broadcastHelper()
            Result.success()
        } catch (e: Exception) {
            Log.d("SyncWorkManager", "$e")
            Result.failure()
        }
    }

    private suspend fun syncComment(comment: CommentEntity) {
        val isSynced = firestoreHelper.pushToCloud(comment.toMap())
        if (isSynced) {
            commentDao.addCommentEntity(
                CommentEntity.copyComment(
                    1,
                    System.currentTimeMillis(),
                    comment.creationTime,
                    comment.comment,
                    comment.id
                )
            )
        } else {
            /// delete comment if it fails to sync
            // OR
            // change color of failed to sync comments to red color

            // DELETE OPERATION
            //commentDao.removeCommentEntity(comment)

            // UPDATE WITH NEW SYNC STATUS
            commentDao.addCommentEntity(
                CommentEntity.copyComment(
                    2,
                    System.currentTimeMillis(),
                    comment.creationTime,
                    comment.comment,
                    comment.id
                )
            )
        }
    }
}