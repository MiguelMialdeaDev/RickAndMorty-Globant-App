package com.miguelmialdea.domain.repository

import com.miguelmialdea.domain.model.CharacterModel

interface CharacterRepository {
    suspend fun getCharacters(forceRefresh: Boolean): List<CharacterModel>
}