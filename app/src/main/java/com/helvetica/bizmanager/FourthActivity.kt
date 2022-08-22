package com.helvetica.bizmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class FourthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)
        Toast.makeText(this,"My PO's activity works!",Toast.LENGTH_SHORT).show()
    }
}