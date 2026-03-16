package com.miguelmialdea.data

import com.miguelmialdea.data.dto.CharactersResponse
import retrofit2.http.GET

interface ApiService {

    @GET("character")
    suspend fun getCharacters(): CharactersResponse
}
