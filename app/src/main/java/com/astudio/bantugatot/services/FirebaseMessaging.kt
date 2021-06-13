package com.astudio.bantugatot.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class FirebaseMessaging: FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Timber.e("notif: ${remoteMessage.notification}")
        Timber.e("data: ${remoteMessage.data}")
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onDestroy() {
        super.onDestroy()
        restartService()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        restartService()
        return super.onUnbind(intent)
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        restartService()
    }

    private fun restartService() {
        // init pending intent of this service
        val pendingIntent = PendingIntent.getService(
            applicationContext,
            1,
            Intent(applicationContext, this::class.java).setPackage(packageName),
            PendingIntent.FLAG_ONE_SHOT
        )

        // set alarm manager to wake up service
        (applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager).apply {
            try {
                set(
                    AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime() + 1000,
                    pendingIntent
                )
            } catch (e: Exception) {}
        }
        onCreate()
    }
}