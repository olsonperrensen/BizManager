package com.helvetica.bizmanager

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.helvetica.bizmanager.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val appSetingPrefs: SharedPreferences = getSharedPreferences("AppSettingPrefs", 0)
        val isLight: Boolean = appSetingPrefs.getBoolean("LightMode", true)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about)
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
        val img = binding.ivAbout
        if (!isLight) {
            Glide.with(this).load(
                "https://u.cubeupload.com/olsonperrensen2/GitHubMarkLight64px.png"
            ).fitCenter().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.ic_github_mark_light_120px_plus)
                .into(img)
        }
    }
}