package com.miguelmialdea.data.datasource

import com.miguelmialdea.data.ApiService
import com.miguelmialdea.data.dto.CharacterDto

class CharacterDataSourceImpl(
    private val apiService: ApiService
): CharacterDataSource {
    override suspend fun getCharacterById(id: Int): CharacterDto {
        return apiService.getCharacterById(id)
    }
}
