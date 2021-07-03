package com.sd.testmo.data.remote

import com.sd.testmo.data.entities.Repo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ItemService {
    @GET("repo")
    suspend fun getAllRepo() : Response<Repo>


}