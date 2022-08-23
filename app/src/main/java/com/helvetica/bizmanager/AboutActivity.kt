package com.helvetica.bizmanager

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.helvetica.bizmanager.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_about)
        super.onCreate(savedInstanceState)
        binding.tvSrc.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(binding.tvSrc.text.toString())
            startActivity(intent)
        }
        binding.tvSrc2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(binding.tvSrc2.text.toString())
            startActivity(intent)
        }
    }
}