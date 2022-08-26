package com.helvetica.bizmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.helvetica.bizmanager.data.User
import com.helvetica.bizmanager.data.UserViewModel
import com.helvetica.bizmanager.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    private lateinit var userViewModel: UserViewModel

    //    FIELDS FOR DB
    private lateinit var img: String
    private lateinit var id: String
    private lateinit var username: String
    private lateinit var password: String
    private lateinit var naam: String
    private lateinit var land: String
    private lateinit var sbu: String
    private lateinit var manager: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_third)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        val intent = intent
        userViewModel.amnesiaKiller(
            User(
                0,
                intent.getStringExtra("id").toString().toInt(),
                intent.getStringExtra("land").toString(),
                intent.getStringExtra("manager").toString(),
                intent.getStringExtra("naam").toString(),
                intent.getStringExtra("password").toString(),
                intent.getStringExtra("sbu").toString(),
                intent.getStringExtra("username").toString(),
                intent.getStringExtra("img").toString()
            )
        )
        img = userViewModel.tmpUser.imageSrc
        id = userViewModel.tmpUser.id.toString()
        username = userViewModel.tmpUser.username
        password = userViewModel.tmpUser.password
        naam = userViewModel.tmpUser.naam
        land = userViewModel.tmpUser.land
        sbu = userViewModel.tmpUser.sbu
        manager = userViewModel.tmpUser.manager
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
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        if (inputCheck(
                img,
                id,
                username,
                password,
                naam,
                land,
                sbu,
                manager
            )
        ) {
            val user = User(0, id.toInt(), land, manager, naam, password, sbu, username, img)
            userViewModel.addUser(user)
            Toast.makeText(this, "Employee liked!", Toast.LENGTH_LONG).show()
            finish()
        } else {
            Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(
        img: String,
        id: String,
        username: String,
        password: String,
        naam: String,
        land: String,
        sbu: String,
        manager: String
    ): Boolean {
        return !(TextUtils.isEmpty(img) && TextUtils.isEmpty(id)
                && TextUtils.isEmpty(username)
                && TextUtils.isEmpty(password)
                && TextUtils.isEmpty(naam)
                && TextUtils.isEmpty(land)
                && TextUtils.isEmpty(sbu)
                && TextUtils.isEmpty(manager))
    }

}