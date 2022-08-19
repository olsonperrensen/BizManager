package com.helvetica.bizmanager

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.helvetica.bizmanager.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
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
    }
}
