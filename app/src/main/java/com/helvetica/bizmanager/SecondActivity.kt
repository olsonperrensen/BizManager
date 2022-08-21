package com.helvetica.bizmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.helvetica.bizmanager.databinding.ActivitySecondBinding
import retrofit2.Response

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)
        val recyclerView = binding.rvEmployees
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = rvEmployeesAdapter()
        val retService = RetrofitInstance.getRetrofitInstance()
            .create(WorkerService::class.java)
        val responseLiveData:LiveData<Response<Worker>> = liveData {
            val response = retService.getWorkers()
            emit(response)
        }
        responseLiveData.observe(this){
            val workersList = it.body()?.listIterator()
            if(workersList!=null)
            {
                while (workersList.hasNext())
                {
                    val workerItem = workersList.next()
                    Log.i("HI",workerItem.username)
                }
            }
        }
        binding.btnScdBack.setOnClickListener {
            finish()
        }
    }
}