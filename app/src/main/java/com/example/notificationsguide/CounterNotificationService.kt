package com.example.notificationsguide

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class CounterNotificationService(
    private val context : Context
){
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    fun showNotifications(count: Int){
        val activityIntent  = Intent(context , MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val incrementIntent = PendingIntent.getBroadcast(
            context,
            2,
            Intent(context, CounterNotificationReceiver::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(context, COUNTER_CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_10k_24)
            .setContentTitle("Increment Counter")
            .setContentText("The count is $count")
            .setContentIntent(activityPendingIntent)
            .addAction(
                R.drawable.baseline_10k_24,
                "Incremment",
                incrementIntent
                )
            .build()

        notificationManager.notify(1, notification)

    }

    companion object{
        const val COUNTER_CHANNEL_ID = "channel_id"
    }
}