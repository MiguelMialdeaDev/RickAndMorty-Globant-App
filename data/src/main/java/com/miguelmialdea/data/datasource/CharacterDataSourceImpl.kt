package com.miguelmialdea.data.datasource

import com.miguelmialdea.data.ApiService
import com.miguelmialdea.data.mapper.toModel
import com.miguelmialdea.domain.model.CharacterModel
import kotlin.collections.map

class CharacterDataSourceImpl(
    private val apiService: ApiService
): CharacterDataSource {
    override suspend fun getCharacters(): List<CharacterModel> {
        return apiService.getCharacters().results.map { it.toModel() }
    }
}