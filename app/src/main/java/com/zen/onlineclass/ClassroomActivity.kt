package com.zen.onlineclass

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_classroom.*
import kotlinx.android.synthetic.main.activity_textbook.*

class ClassroomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classroom)

        btnPDCAClass.setOnClickListener {
            goToUrl("https://drive.google.com/file/d/1YeIQf5MxGn4-hs0l4AHE5R4-cShfT7US/view?usp=sharing")
        }

        btnSSDClass.setOnClickListener {
            goToUrl("https://drive.google.com/drive/folders/1y4IQ6PwaiNo1SdUPBTUH_ARDkKTn55pt?usp=sharing")
        }

        btnLCDClass.setOnClickListener {
            goToUrl("https://classroom.google.com/u/1/c/MTIyOTg2MDM3ODI2")
        }

        btnNTClass.setOnClickListener {
            goToUrl("https://drive.google.com/drive/folders/1y4IQ6PwaiNo1SdUPBTUH_ARDkKTn55pt?usp=sharing")
        }

        btnDEClass.setOnClickListener {
            goToUrl("https://drive.google.com/drive/folders/1y4IQ6PwaiNo1SdUPBTUH_ARDkKTn55pt?usp=sharing")
        }

        btnSEClass.setOnClickListener {
            goToUrl("https://drive.google.com/drive/folders/1y4IQ6PwaiNo1SdUPBTUH_ARDkKTn55pt?usp=sharing")
        }



    }

    private fun goToUrl(url: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}