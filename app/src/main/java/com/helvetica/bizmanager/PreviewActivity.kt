package com.helvetica.bizmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class PreviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        supportActionBar?.hide()
        Handler().postDelayed({
            val intent = Intent(this@PreviewActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}