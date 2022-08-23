package com.helvetica.bizmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.helvetica.bizmanager.databinding.ActivityFourthBinding
import com.helvetica.bizmanager.repository.Repository

class FourthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFourthBinding
    private lateinit var viewModel: FourthViewModel
    private val myAdapter by lazy { RvPOsAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fourth)
        val repository = Repository()
        val viewModelFactory = FourthViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[FourthViewModel::class.java]
        setupRecyclerView()
        viewModel.getPOs()
        viewModel.myResponse.observe(this) { response ->
            if (response.isSuccessful) {
                response.body()?.let { myAdapter.setPOData(it) }
            } else {
                Log.d("PO-ERR", response.code().toString())
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvPOs.adapter = myAdapter
        binding.rvPOs.layoutManager = LinearLayoutManager(this)
    }
}