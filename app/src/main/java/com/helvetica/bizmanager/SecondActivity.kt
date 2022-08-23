package com.helvetica.bizmanager

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.helvetica.bizmanager.databinding.ActivitySecondBinding
import com.helvetica.bizmanager.dialog.LoadingDialog
import com.helvetica.bizmanager.repository.Repository

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
        val loadingDialog = LoadingDialog(this)
        var firstTime = viewModel.getFirstTime()
        if(firstTime)
        {
            loadingDialog.startLoadingDialog()
        }
        viewModel.getWorkers()
        viewModel.getWorkersImg()
        viewModel.myResponse.observe(this) { response ->
            if (response.isSuccessful) {
                response.body()?.let { myAdapter.setData(it) }
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.myResponseImg.observe(this) { response ->
            if (response.isSuccessful) {
                response.body()?.let { myAdapter.setDataImg(it) }
            } else {
                Log.d("IMGERR", response.code().toString())
            }
            if (firstTime) {
                Handler().postDelayed({
                    loadingDialog.dismissDialog()
                }, 1000)
            }
            else{
                loadingDialog.dismissDialog()
            }
            viewModel.setFirstTime(false)
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
