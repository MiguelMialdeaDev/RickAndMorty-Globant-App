package com.miguelmialdea.data.datasource

import com.miguelmialdea.domain.model.CharacterModel

interface CharacterDataSource {
    suspend fun getCharacters(): List<CharacterModel>
}
