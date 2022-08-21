package com.helvetica.bizmanager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.helvetica.bizmanager.model.Worker
import com.helvetica.bizmanager.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class SecondViewModel(private val repository: Repository): ViewModel() {
val myResponse: MutableLiveData<Response<List<Worker>>> = MutableLiveData()

    fun getWorkers(){
        viewModelScope.launch {
            val response = repository.getWorkers()
            myResponse.value = response
        }
    }
}