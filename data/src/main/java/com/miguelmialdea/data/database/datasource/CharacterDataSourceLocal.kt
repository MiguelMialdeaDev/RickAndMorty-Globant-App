package com.miguelmialdea.data.database.datasource

import com.miguelmialdea.domain.model.CharacterModel

interface CharacterDataSourceLocal {
    suspend fun getCharacters(): List<CharacterModel>
    suspend fun saveCharacters(characters: List<CharacterModel>)
    suspend fun removeCharacters()
}

