package com.zen.onlineclass

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

/**
 * Created By Sumit Sharma on 23-12-2020
 */

inline fun Context.createNotification(id: Int, channelId: String, notificationProperty: NotificationCompat.Builder.() -> Unit) {
    val builder = NotificationCompat.Builder(this, channelId)
    builder.notificationProperty()
    val notification = builder.build()
    val notificationManager = ContextCompat.getSystemService(this, NotificationManager::class.java)
    notificationManager?.notify(id, notification)
}