package com.helvetica.bizmanager

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.helvetica.bizmanager.api.RetrofitInstance
import com.helvetica.bizmanager.databinding.ActivitySecondBinding
import com.helvetica.bizmanager.model.Worker
import com.helvetica.bizmanager.repository.Repository
import retrofit2.Response

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var viewModel: SecondViewModel
    private val myAdapter by lazy { RvEmployeesAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)
        setupRecyclerView()
        val repository = Repository()
        val viewModelFactory = SecondViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[SecondViewModel::class.java]
        viewModel.getWorkers()
        viewModel.myResponse.observe(this) { response ->
            if(response.isSuccessful){
                response.body()?.let { myAdapter.setData(it) }
            }
            else
            {
                Toast.makeText(this,response.code(),Toast.LENGTH_SHORT).show()
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
