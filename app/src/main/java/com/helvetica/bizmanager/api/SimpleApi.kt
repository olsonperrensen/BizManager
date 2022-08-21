package com.helvetica.bizmanager.api

import com.helvetica.bizmanager.model.Worker
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {
    @GET("/workers")
    suspend fun getWorkers(): Response<List<Worker>>
}