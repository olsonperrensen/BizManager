package com.helvetica.bizmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.helvetica.bizmanager.repository.Repository

class PreviewActivity : AppCompatActivity() {
    private lateinit var viewModel: PreviewViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        val repository = Repository()
        val viewModelFactory = PreviewViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[PreviewViewModel::class.java]
        viewModel.getHeroku()
        viewModel.myHerokuResponse.observe(this) { response ->
            if (response.isSuccessful) {
                Toast.makeText(this, "Backend Up!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error ${response.code()} backend down", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        supportActionBar?.hide()
        Handler().postDelayed({
            val intent = Intent(this@PreviewActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}