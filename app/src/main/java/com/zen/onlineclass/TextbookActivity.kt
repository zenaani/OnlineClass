package com.zen.onlineclass

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_textbook.*

class TextbookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_textbook)

        btnPDCAText.setOnClickListener {
            goToUrl("https://drive.google.com/file/d/1YeIQf5MxGn4-hs0l4AHE5R4-cShfT7US/view?usp=sharing")
        }

        btnSSDText.setOnClickListener {
            goToUrl("https://drive.google.com/drive/folders/1y4IQ6PwaiNo1SdUPBTUH_ARDkKTn55pt?usp=sharing")
        }

        btnLCDText.setOnClickListener {
            goToUrl("https://drive.google.com/drive/folders/1y4IQ6PwaiNo1SdUPBTUH_ARDkKTn55pt?usp=sharing")
        }

        btnNTText.setOnClickListener {
            goToUrl("https://drive.google.com/drive/folders/1y4IQ6PwaiNo1SdUPBTUH_ARDkKTn55pt?usp=sharing")
        }

        btnDEText.setOnClickListener {
            goToUrl("https://drive.google.com/drive/folders/1y4IQ6PwaiNo1SdUPBTUH_ARDkKTn55pt?usp=sharing")
        }

        btnSEText.setOnClickListener {
            goToUrl("https://drive.google.com/drive/folders/1y4IQ6PwaiNo1SdUPBTUH_ARDkKTn55pt?usp=sharing")
        }

    }

    private fun goToUrl(url: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}