package com.helvetica.bizmanager.repository

import com.helvetica.bizmanager.api.RetrofitInstance
import com.helvetica.bizmanager.model.Worker
import retrofit2.Response

class Repository {
    suspend fun getWorkers(): Response<List<Worker>> {
        return RetrofitInstance.api.getWorkers()
    }
}