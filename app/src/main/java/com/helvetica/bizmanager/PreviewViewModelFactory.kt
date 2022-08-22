package com.helvetica.bizmanager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.helvetica.bizmanager.repository.Repository

class PreviewViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PreviewViewModel(repository) as T
    }

}