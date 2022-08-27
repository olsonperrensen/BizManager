package com.helvetica.bizmanager

import android.content.ContentProvider
import android.content.ContentProviderOperation
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.helvetica.bizmanager.data.User
import com.helvetica.bizmanager.data.UserViewModel
import com.helvetica.bizmanager.databinding.ActivityThirdBinding
import java.lang.Exception
import java.util.jar.Manifest

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    private lateinit var userViewModel: UserViewModel

    //    ANIM
    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.rotate_open_anim
        )
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.rotate_close_anim
        )
    }
    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.from_bottom_anim
        )
    }
    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.to_bottom_anim
        )
    }
    private var isClicked = false


    //    FIELDS FOR DB
    private lateinit var img: String
    private lateinit var id: String
    private lateinit var username: String
    private lateinit var password: String
    private lateinit var naam: String
    private lateinit var land: String
    private lateinit var sbu: String
    private lateinit var manager: String

    //    WRITE CONTACT
    private val TAG = "ADD_CONTACT_TAG"
    private lateinit var contactPermissions: Array<String>
    private val WRITE_CONTACT_PERMISSION_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_third)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        val intent = intent
        contactPermissions = arrayOf(android.Manifest.permission.WRITE_CONTACTS)
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
        binding.fabExpand.setOnClickListener {
            onExpandButtonClicked()
        }
        binding.fabLike?.setOnClickListener {
            insertDataToDatabase()
        }
        binding.fabContact?.setOnClickListener {
            if (isWriteContactPermissionEnabled()) {
                saveContact()
            } else {
                requestWriteContactPermission()
            }

        }

    }

    //    FAB STUFF
    private fun onExpandButtonClicked() {
        setVisibility(isClicked)
        setAnimation(isClicked)
        isClicked = !isClicked
    }

    private fun setVisibility(isClicked: Boolean) {
        if (!isClicked) {
            binding.fabLike?.visibility = View.VISIBLE
            binding.fabContact?.visibility = View.VISIBLE
        } else {
            binding.fabLike?.visibility = View.GONE
            binding.fabContact?.visibility = View.GONE
        }
    }

    private fun setAnimation(isClicked: Boolean) {
        if (!isClicked) {
            binding.fabLike?.startAnimation(fromBottom)
            binding.fabContact?.startAnimation(fromBottom)
            binding.fabExpand.startAnimation(rotateOpen)
        } else {
            binding.fabLike?.startAnimation(toBottom)
            binding.fabContact?.startAnimation(toBottom)
            binding.fabExpand.startAnimation(rotateClose)
        }
    }

    //    CONTACTS FN
    private fun isWriteContactPermissionEnabled(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.WRITE_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestWriteContactPermission() {
        ActivityCompat.requestPermissions(this, contactPermissions, WRITE_CONTACT_PERMISSION_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty()) {
            if (requestCode == WRITE_CONTACT_PERMISSION_CODE) {
                val haveWriteContactPermission =
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                if (haveWriteContactPermission) {
                    saveContact()
                } else {
                    Toast.makeText(
                        this,
                        "You didn't gave us permission to access your contacts :(",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun saveContact() {
        val firstName = naam.split(" ")[0]
        val lastName = naam.split(" ")[1]
        val cpo = ArrayList<ContentProviderOperation>()
        val rawContactId = cpo.size
        cpo.add(
            ContentProviderOperation.newInsert(
                ContactsContract.RawContacts.CONTENT_URI
            ).withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build()
        )
//        FN - LN
        cpo.add(
            ContentProviderOperation.newInsert(
                ContactsContract.Data.CONTENT_URI
            ).withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, rawContactId)
                .withValue(
                    ContactsContract.RawContacts.Data.MIMETYPE,
                    ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE
                )
                .withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, firstName)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME, lastName)
                .build()
        )
// NUM
        cpo.add(
            ContentProviderOperation.newInsert(
                ContactsContract.Data.CONTENT_URI
            ).withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, rawContactId)
                .withValue(
                    ContactsContract.RawContacts.Data.MIMETYPE,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
                )
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, "+32480123456")
                .withValue(
                    ContactsContract.CommonDataKinds.Phone.TYPE,
                    ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE
                )
                .build()
        )
//        ACTUALLY SAVE THE CONTACT
        try {
            contentResolver.applyBatch(ContactsContract.AUTHORITY, cpo)
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.d("contactSaving", "Error ${e.message}")
            Toast.makeText(this, "Could not save contact", Toast.LENGTH_SHORT).show()
        }

    }

    //    LIKED EMPLOYEES TO DB
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