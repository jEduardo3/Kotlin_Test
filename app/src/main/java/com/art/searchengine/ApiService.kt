package com.art.searchengine

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character/")
    suspend fun getCharacter(@Query("name") name: String): Response<RickAndMortyDataResponse>

    @GET("{id}")
    suspend fun getCharacterDetail(@Path("id") id: String): Response<RickAndMortyDetailResponse>

}