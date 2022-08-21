package com.helvetica.bizmanager

import retrofit2.Response
import retrofit2.http.GET

interface WorkerService {
    @GET("/workers")
    suspend fun getWorkers(): Response<Worker>
}