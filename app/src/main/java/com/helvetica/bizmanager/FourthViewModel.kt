package com.helvetica.bizmanager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.helvetica.bizmanager.model.PO
import com.helvetica.bizmanager.model.Worker
import com.helvetica.bizmanager.model.WorkerImg
import com.helvetica.bizmanager.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class FourthViewModel(private val repository: Repository) : ViewModel() {
    val myResponse: MutableLiveData<Response<List<PO>>> = MutableLiveData()

    fun getPOs() {
        viewModelScope.launch {
            val response = repository.getPOs()
            myResponse.value = response
        }
    }
}