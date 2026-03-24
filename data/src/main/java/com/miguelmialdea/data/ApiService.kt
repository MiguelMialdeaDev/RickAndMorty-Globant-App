package com.miguelmialdea.data

import com.miguelmialdea.data.dto.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int = 1
    ): CharactersResponse

    @GET("character")
    suspend fun searchCharacters(
        @Query("page") page: Int = 1,
        @Query("name") name: String
    ): CharactersResponse
}
