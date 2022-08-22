package com.helvetica.bizmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class FifthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth)
        Toast.makeText(this,"Liked employees Activity works!",Toast.LENGTH_SHORT).show()
    }
}