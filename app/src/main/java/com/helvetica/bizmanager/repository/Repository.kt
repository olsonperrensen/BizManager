package com.helvetica.bizmanager.repository

import com.helvetica.bizmanager.api.RetrofitInstance
import com.helvetica.bizmanager.model.Heroku
import com.helvetica.bizmanager.model.PO
import com.helvetica.bizmanager.model.Worker
import com.helvetica.bizmanager.model.WorkerImg
import retrofit2.Response

class Repository {
    suspend fun getWorkers(): Response<List<Worker>> {
        return RetrofitInstance.api.getWorkers()
    }
    suspend fun getWorkersImg(): Response<List<WorkerImg>> {
        return RetrofitInstance.api.getWorkersImg()
    }
    suspend fun getHeroku(): Response<Heroku> {
        return RetrofitInstance.api.getHeroku()
    }
    suspend fun getPOs(): Response<List<PO>> {
        return RetrofitInstance.api.getPOs()
    }
}