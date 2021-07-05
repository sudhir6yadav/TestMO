package com.sd.testmo.data.remote

import com.sd.testmo.data.entities.Repo
import retrofit2.Response
import retrofit2.http.GET

interface ItemService {
    @GET("search/repositories?q=android%20language:java&sort=stars&per_page=20&order=desc")
    suspend fun getAllRepo() : Response<Repo>
}