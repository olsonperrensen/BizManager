package com.helvetica.bizmanager.api

import com.helvetica.bizmanager.model.Heroku
import com.helvetica.bizmanager.model.PO
import com.helvetica.bizmanager.model.Worker
import com.helvetica.bizmanager.model.WorkerImg
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpleApi {
    @GET("/workers")
    suspend fun getWorkers(): Response<List<Worker>>

    @GET("/img")
    suspend fun getWorkersImg(): Response<List<WorkerImg>>

    @GET("/")
    suspend fun getHeroku(): Response<Heroku>

    @GET("/po")
    suspend fun getPOs(): Response<List<PO>>
}