package com.tsbempire.offlinecachingmechanism.framework.workmanager

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BroadcastHelper @Inject constructor(@ApplicationContext context: Context) {
    private var localBroadcastManager = LocalBroadcastManager.getInstance(context)
    operator fun invoke() {
        Log.d("BroadcastHelper","Trigger")
        val localIntent = Intent("WORK_MANAGER")
        localBroadcastManager.sendBroadcast(localIntent)
    }
}