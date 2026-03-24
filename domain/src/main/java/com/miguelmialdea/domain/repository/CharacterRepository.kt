package com.miguelmialdea.domain.repository

import androidx.paging.PagingData
import com.miguelmialdea.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharactersPaged(query: String = ""): Flow<PagingData<CharacterModel>>
}
