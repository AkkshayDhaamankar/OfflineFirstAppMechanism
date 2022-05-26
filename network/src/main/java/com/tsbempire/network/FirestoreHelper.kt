package com.tsbempire.network

import android.util.Log
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class FirestoreHelper {
    private val db = Firebase.firestore
    suspend fun pushToCloud(commentMap: HashMap<String, Any>): Boolean {
        return try {
            db.collection("comments").document("${commentMap["id"]}")
                .set(commentMap, SetOptions.merge()).await()
            Log.d(Constants.TAG, "Successfully synced data")
            return true
        } catch (e: Exception) {
            Log.d(Constants.TAG, "Error Occurred while syncing data $e")
            return false
        }
    }
}