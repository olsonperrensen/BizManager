package com.helvetica.bizmanager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainActivityViewModel : ViewModel() {
    var myResponse = MutableLiveData<String>()

    init {
        myResponse.value = " "
    }
}