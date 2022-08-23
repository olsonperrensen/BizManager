package com.helvetica.bizmanager

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.helvetica.bizmanager.databinding.ActivityLockedBinding
import com.helvetica.bizmanager.databinding.ActivityMainBinding
import com.helvetica.bizmanager.dialog.LoadingDialog
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingLocked: ActivityLockedBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appSetingPrefs: SharedPreferences = getSharedPreferences("AppSettingPrefs", 0)
        val sharedPrefsEdit: SharedPreferences.Editor = appSetingPrefs.edit()
        val isLight: Boolean = appSetingPrefs.getBoolean("LightMode", true)
        val isLocked: Boolean = appSetingPrefs.getBoolean("isLocked", false)
        if (!isLocked) {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
            viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
            toggle =
                ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
            binding.drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            binding.navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.miItem1 -> {
                        Toast.makeText(
                            applicationContext,
                            "Launching Purchase Orders",
                            Toast.LENGTH_SHORT
                        ).show()
                        Intent(this, FourthActivity::class.java).also { activity ->
                            startActivity(activity)
                        }
                    }
                    R.id.miItem2 -> {
                        Toast.makeText(
                            applicationContext,
                            "Launching Employees",
                            Toast.LENGTH_SHORT
                        ).show()
                        Intent(this, SecondActivity::class.java).also { activity ->
                            startActivity(activity)
                        }
                    }
                    R.id.miItem3 -> {
                        Toast.makeText(
                            applicationContext,
                            "Launching My Liked Employees",
                            Toast.LENGTH_SHORT
                        ).show()
                        Intent(this, FifthActivity::class.java).also { activity ->
                            startActivity(activity)
                        }
                    }

                }
                true
            }
            val navView = findViewById<BottomNavigationView>(R.id.bnv)
            val menuItem = navView.menu.findItem(R.id.btnNightMode)
            val menuItemFix = findViewById<View>(R.id.btnNightMode)
            val btnAbout = findViewById<BottomNavigationItemView>(R.id.btnAbout)
            val btnLock = findViewById<BottomNavigationItemView>(R.id.btnLock)
            val userLang = Locale.getDefault().displayLanguage
            if (isLight) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                menuItem.title = when (userLang) {
                    "English" -> "Night Mode"
                    "Nederlands" -> "Nachtmodus"
                    "français" -> "Mode nuit"
                    "Deutsch" -> "Nacht-Modus"
                    else -> "Night Mode."
                }
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                menuItem.title = when (userLang) {
                    "English" -> "Light Mode"
                    "Nederlands" -> "Lichtmodus"
                    "français" -> "Mode lumière"
                    "Deutsch" -> "Lichtmodus"
                    else -> "Light Mode."
                }
            }
            menuItemFix.setOnClickListener {
                if (isLight) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    menuItem.title = "Light Mode"
                    sharedPrefsEdit.putBoolean("LightMode", false)
                    sharedPrefsEdit.apply()
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    menuItem.title = "Dark Mode"
                    sharedPrefsEdit.putBoolean("LightMode", true)
                    sharedPrefsEdit.apply()
                }
            }
            binding.btnMedewerker.setOnClickListener {
                Intent(this, SecondActivity::class.java).also {
                    startActivity(it)
                }
            }
            binding.btnPO.setOnClickListener {
                Intent(this, FourthActivity::class.java).also {
                    startActivity(it)
                }
            }
            btnAbout.setOnClickListener {
                Intent(this, AboutActivity::class.java).also {
                    startActivity(it)
                }
            }
            btnLock.setOnClickListener {
                sharedPrefsEdit.putBoolean("isLocked", true)
                sharedPrefsEdit.apply()
                finish()
            }
        } else {
            bindingLocked = DataBindingUtil.setContentView(this, R.layout.activity_locked)
            viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
            Toast.makeText(this,"Welcome to the Early Testing!!",Toast.LENGTH_LONG).show()
            val btnUnlock = findViewById<BottomNavigationItemView>(R.id.btnUnlock).setOnClickListener {
                sharedPrefsEdit.putBoolean("isLocked", false)
                sharedPrefsEdit.apply()
                finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
