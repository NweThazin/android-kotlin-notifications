package com.example.android.eggtimernotifications

import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.android.eggtimernotifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Log.d("Token", token ?: "")
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        Log.d("Services:", "From ${remoteMessage?.from}")

        remoteMessage?.data?.let {
            Log.d("Services:", "Message data payload: " + remoteMessage.data)
            val notificationManager =
                applicationContext.getSystemService(NotificationManager::class.java)
            notificationManager.sendNotification(
                remoteMessage.data?.toString() ?: "",
                applicationContext
            )
        }

        remoteMessage?.notification?.let {
            Log.d("Services:Notification:", "Message data payload: " + it.body)
            val notificationManager =
                applicationContext.getSystemService(NotificationManager::class.java)
            notificationManager.sendNotification(
                it.body!!,
                applicationContext
            )
        }
    }
}