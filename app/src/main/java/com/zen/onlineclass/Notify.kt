package com.zen.onlineclass

import android.app.*
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.app.PendingIntent.getActivity
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.media.AudioAttributes
import android.media.AudioAttributes.USAGE_NOTIFICATION_RINGTONE
import android.media.RingtoneManager.TYPE_NOTIFICATION
import android.media.RingtoneManager.getDefaultUri
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.DEFAULT_ALL
import androidx.core.app.NotificationManagerCompat.IMPORTANCE_HIGH
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.security.AccessController.getContext

class Notify(context: Context, workerParams: WorkerParameters): Worker(context, workerParams) {

    override fun doWork(): Result {
        val id = inputData.getLong(NOTIFICATION_ID, 0).toInt()
        sendNotifications(id)

    }

    private fun sendNotifications(id: Int) {

        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra(NOTIFICATION_ID, id)

        val notificationManager = applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager


        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notification = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL)
                .setContentTitle("Join Network Theory")
                .setContentTitle("Time's up")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(DEFAULT_ALL)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification.setChannelId(NOTIFICATION_CHANNEL)

            val ringtoneManager = getDefaultUri(TYPE_NOTIFICATION)
            val audioAttributes = AudioAttributes.Builder()
                    .setUsage(USAGE_NOTIFICATION_RINGTONE)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()

            val channel = NotificationChannel(NOTIFICATION_CHANNEL, NOTIFICATION_NAME, NotificationManager.IMPORTANCE_HIGH).apply {
                enableLights(true)
                lightColor = R.color.design_default_color_on_primary
                enableVibration(true)
                setSound(ringtoneManager, audioAttributes)
            }

            notificationManager.notify(id, notification.build())

        }

    }

    companion object {
        const val NOTIFICATION_ID = "notificationID"
        const val NOTIFICATION_NAME = "notificationName"
        const val NOTIFICATION_CHANNEL = "notificationChannel"
        const val NOTIFICATION_WORK = "notificationWork"
    }

}

