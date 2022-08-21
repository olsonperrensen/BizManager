package com.helvetica.bizmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.helvetica.bizmanager.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding : ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_third)
        val intent = intent
        val id = intent.getStringExtra("id")
        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")
        val naam = intent.getStringExtra("naam")
        val land = intent.getStringExtra("land")
        val sbu = intent.getStringExtra("sbu")
        val manager = intent.getStringExtra("manager")
        binding.tvDetails.text = "\tid:\t\t\t $id\n\n" +
                "\tusername:\t\t\t $username\n\n" +
                "\tpassword:\t\t\t $password\n\n" +
                "\tname:\t\t\t $naam\n\n" +
                "\tcountry:\t\t\t $land\n\n" +
                "\tsbu:\t\t\t $sbu\n\n" +
                "\tmanager:\t\t\t $manager\n\n"
    }
}