package com.helvetica.bizmanager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.helvetica.bizmanager.model.Worker
import com.helvetica.bizmanager.model.WorkerImg
import com.helvetica.bizmanager.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class SecondViewModel(private val repository: Repository) : ViewModel() {
    val myResponse: MutableLiveData<Response<List<Worker>>> = MutableLiveData()
    val myResponseImg: MutableLiveData<Response<List<WorkerImg>>> = MutableLiveData()

    fun getWorkers() {
        viewModelScope.launch {
            val response = repository.getWorkers()
            myResponse.value = response
        }
    }
    fun getWorkersImg() {
        viewModelScope.launch {
            val response = repository.getWorkersImg()
            myResponseImg.value = response
        }
    }
}