package com.zen.onlineclass

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.media.AudioAttributes
import android.media.AudioAttributes.USAGE_NOTIFICATION_RINGTONE
import android.media.RingtoneManager.TYPE_NOTIFICATION
import android.media.RingtoneManager.getDefaultUri
import android.os.Build
import android.os.Build.VERSION.*
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.DEFAULT_ALL
import androidx.work.ListenableWorker.Result.success
import androidx.work.Worker
import androidx.work.WorkerParameters

class Notify(val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val id = inputData.getLong(NOTIFICATION_ID, 0).toInt()
        sendNotifications(id)
        return success()
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun sendNotifications(id: Int) {

        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra(NOTIFICATION_ID, id)

        val notificationManager = applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)

        val notification = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Join Network Theory")
                .setContentTitle("Time's up")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(DEFAULT_ALL)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        notification.priority = NotificationCompat.PRIORITY_MAX

        if (SDK_INT >= Build.VERSION_CODES.O) {
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
                vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
                setSound(ringtoneManager, audioAttributes)
            }

            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(id, notification.build())
    }

    companion object {
        const val NOTIFICATION_ID = "notificationID"
        const val NOTIFICATION_NAME = "notificationName"
        const val NOTIFICATION_CHANNEL = "notificationChannel"
        const val NOTIFICATION_WORK = "notificationWork"
    }

}

