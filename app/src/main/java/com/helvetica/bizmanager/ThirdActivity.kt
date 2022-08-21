package com.helvetica.bizmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.helvetica.bizmanager.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_third)
        val intent = intent
        val img = intent.getStringExtra("img")
        val id = intent.getStringExtra("id")
        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")
        val naam = intent.getStringExtra("naam")
        val land = intent.getStringExtra("land")
        val sbu = intent.getStringExtra("sbu")
        val manager = intent.getStringExtra("manager")
        Glide.with(this).load(
            img
        ).fitCenter().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.user)
            .into(binding.ivDetails)
        binding.tvDetails.text = "\tid:\t\t\t $id\n\n" +
                "\tusername:\t\t\t $username\n\n" +
                "\tpassword:\t\t\t $password\n\n" +
                "\tname:\t\t\t $naam\n\n" +
                "\tcountry:\t\t\t $land\n\n" +
                "\tsbu:\t\t\t $sbu\n\n" +
                "\tmanager:\t\t\t $manager\n\n"
        binding.fab.setOnClickListener {
            Toast.makeText(this,"Employee liked!",Toast.LENGTH_LONG).show()
            finish()
        }
    }
}