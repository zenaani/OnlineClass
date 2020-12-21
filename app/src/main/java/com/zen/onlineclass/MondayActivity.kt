package com.zen.onlineclass

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_monday.*

class MondayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monday)

        tvPDCA.setOnClickListener {
            Toast.makeText(this, "No Permanent Link Assigned", Toast.LENGTH_SHORT).show()
        }

        tvDE.setOnClickListener {
            goToUrl("https://meet.google.com/dwb-cins-aco")
        }

        tvSSD.setOnClickListener {
            goToUrl("https://meet.google.com/uzx-aobd-ouc?authuser=0")
        }

        tvLCD.setOnClickListener {
            goToUrl("https://meet.google.com/vsx-bnsv-qze")
        }

        tvNT.setOnClickListener {
            Toast.makeText(this, "No Permanent Link Assigned", Toast.LENGTH_SHORT).show()
        }

    }

    private fun goToUrl(url : String) {

        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }



}