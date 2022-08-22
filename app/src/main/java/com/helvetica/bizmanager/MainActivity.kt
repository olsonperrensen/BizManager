package com.helvetica.bizmanager

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.helvetica.bizmanager.databinding.ActivityMainBinding
import com.helvetica.bizmanager.dialog.LoadingDialog
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.miItem1 -> Toast.makeText(
                    applicationContext,
                    "Clicked Item 1",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.miItem2 -> Toast.makeText(
                    applicationContext,
                    "Clicked Item 2",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.miItem3 -> Toast.makeText(
                    applicationContext,
                    "Clicked Item 3",
                    Toast.LENGTH_SHORT
                ).show()

            }
            true
        }
        val appSetingPrefs: SharedPreferences = getSharedPreferences("AppSettingPrefs", 0)
        val sharedPrefsEdit: SharedPreferences.Editor = appSetingPrefs.edit()
        val isLight: Boolean = appSetingPrefs.getBoolean("LightMode", true)
        val navView = findViewById<BottomNavigationView>(R.id.bnv)
        val menuItem = navView.menu.findItem(R.id.btnNightMode)
        val menuItemFix = findViewById<View>(R.id.btnNightMode)
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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
