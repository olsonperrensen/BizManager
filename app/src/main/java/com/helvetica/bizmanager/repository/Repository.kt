package com.helvetica.bizmanager.repository

import com.helvetica.bizmanager.api.RetrofitInstance
import com.helvetica.bizmanager.model.Worker
import com.helvetica.bizmanager.model.WorkerImg
import retrofit2.Response

class Repository {
    suspend fun getWorkers(): Response<List<Worker>> {
        return RetrofitInstance.api.getWorkers()
    }
    suspend fun getWorkersImg(genre:String,number:Int): Response<WorkerImg> {
        return RetrofitInstance.api.getWorkersImg(genre,number)
    }
}