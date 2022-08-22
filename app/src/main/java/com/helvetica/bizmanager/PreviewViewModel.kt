package com.helvetica.bizmanager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.helvetica.bizmanager.model.Heroku
import com.helvetica.bizmanager.model.Worker
import com.helvetica.bizmanager.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class PreviewViewModel(private val repository: Repository) : ViewModel() {
    val myHerokuResponse: MutableLiveData<Response<Heroku>> = MutableLiveData()

    fun getHeroku() {
        viewModelScope.launch {
            val response = repository.getHeroku()
            myHerokuResponse.value = response
        }
    }
}