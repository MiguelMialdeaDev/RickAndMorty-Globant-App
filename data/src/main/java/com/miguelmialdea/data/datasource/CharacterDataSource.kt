package com.miguelmialdea.data.datasource

import com.miguelmialdea.data.dto.CharacterDto

interface CharacterDataSource {
    suspend fun getCharacterById(id: Int): CharacterDto
}
