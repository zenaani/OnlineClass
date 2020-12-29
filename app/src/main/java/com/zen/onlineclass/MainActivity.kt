package com.zen.onlineclass

import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.zen.onlineclass.Notify.Companion.NOTIFICATION_ID
import com.zen.onlineclass.Notify.Companion.NOTIFICATION_WORK
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.System.currentTimeMillis
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTextbooks.setOnClickListener {
            Intent(this, TextbookActivity::class.java).also {
                startActivity(it)
            }
        }

        //Joins the meeting when clicked
        btnMonday.setOnClickListener {
            Intent(this, MondayActivity::class.java).also {
                startActivity(it)
            }
        }

        btnTuesday.setOnClickListener {
            Intent(this, TuesdayActivity::class.java).also {
                startActivity(it)
            }
        }

        btnWednesday.setOnClickListener {
            Intent(this, WednesdayActivity::class.java).also {
                startActivity(it)
            }
        }

        btnThursday.setOnClickListener {
            Intent(this, ThursdayActivity::class.java).also {
                startActivity(it)
            }
        }

        btnFriday.setOnClickListener {
            Intent(this, FridayActivity::class.java).also {
                startActivity(it)
            }
        }

        btnSaturday.setOnClickListener {
            Intent(this, SaturdayActivity::class.java).also {
                startActivity(it)
            }
        }

        tvNT.setOnClickListener {
            Toast.makeText(this, "No Permanent Link Assigned", Toast.LENGTH_SHORT).show()
        }

        tvSSD.setOnClickListener {
            goToUrl("https://meet.google.com/uzx-aobd-ouc?authuser=0")
        }

        tvLCD.setOnClickListener {
            goToUrl("https://meet.google.com/vsx-bnsv-qze")
        }

        tvPDCA.setOnClickListener {
            Toast.makeText(this, "No Permanent Link Assigned", Toast.LENGTH_SHORT).show()
        }

        tvDE.setOnClickListener {
            goToUrl("https://meet.google.com/dwb-cins-aco")
        }

        tvML.setOnClickListener {
            goToUrl("https://meet.google.com/coa-dwhw-wvd")
        }

        tvSE.setOnClickListener {
            Toast.makeText(this, "No Permanent Link Assigned", Toast.LENGTH_SHORT).show()
        }


        //Notification Timings
        val networkTheoryTimings = Calendar.getInstance()

        networkTheoryTimings.apply {
            set(2020, 11, 24, 20, 59, 0)

            /*set(Calendar.DAY_OF_WEEK, 2 )
            set(Calendar.SECOND, 0)
            set(Calendar.MINUTE, 6)
            set(Calendar.HOUR, 19)
            set(Calendar.YEAR, 2020)
            set(Calendar.MONTH, 11)*/


            /*set(Calendar.SECOND, 0)
            set(Calendar.MINUTE, 10)
            set(Calendar.HOUR, 3)
            set(Calendar.AM_PM, Calendar.PM)
            set(Calendar.YEAR, 2020)
            set(Calendar.MONTH, 12)
            set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY)*/
        }

        val networkTheoryNotifyTime = networkTheoryTimings.timeInMillis
        val currentTime = currentTimeMillis()
        if (networkTheoryNotifyTime > currentTime) {
            val data = Data.Builder().putInt(NOTIFICATION_ID, 0).build()
            val delay = networkTheoryNotifyTime - currentTime
            scheduleNotification(delay, data)
        }
    }

    //Schedule Notifications
    private fun scheduleNotification(delay: Long, data: Data) {

        val notificationWork = OneTimeWorkRequest.Builder(Notify::class.java)
                .setInitialDelay(delay, TimeUnit.MILLISECONDS).setInputData(data).build()

        val instanceWorkManager = WorkManager.getInstance(this)
        instanceWorkManager.beginUniqueWork(NOTIFICATION_WORK, ExistingWorkPolicy.REPLACE, notificationWork).enqueue()

    }

    //Goes to the URL
    private fun goToUrl(url: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}

