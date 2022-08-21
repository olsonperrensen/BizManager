package com.helvetica.bizmanager

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.helvetica.bizmanager.databinding.ActivitySecondBinding
import com.helvetica.bizmanager.repository.Repository

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var viewModel: SecondViewModel
    private val myAdapter by lazy { RvEmployeesAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)
        val img = findViewById<ImageView>(R.id.ivPhoto)
        setupRecyclerView()
        val repository = Repository()
        val viewModelFactory = SecondViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[SecondViewModel::class.java]
        viewModel.getWorkers()
        var counter = 0
        while (counter < 100) {
            if (counter % 2 === 0) {
                viewModel.getWorkersImg("men", counter)
            } else {
                viewModel.getWorkersImg("women", counter)
            }
            counter++
        }
        viewModel.myResponse.observe(this) { response ->
            if (response.isSuccessful) {
                response.body()?.let { myAdapter.setData(it) }
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.myResponseImg.observe(this) { response ->
            if (response.isSuccessful) {
                response.body()?.let { myAdapter.setDataImg(it.url) }
            } else {
                Log.d("IMGERR", response.code().toString())
            }
        }
        binding.btnScdBack.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        binding.rvEmployees.adapter = myAdapter
        binding.rvEmployees.layoutManager = LinearLayoutManager(this)
    }
}
