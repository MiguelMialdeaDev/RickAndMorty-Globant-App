package com.miguelmialdea.domain.usecase

import androidx.paging.PagingData
import com.miguelmialdea.domain.model.CharacterModel
import com.miguelmialdea.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetCharactersUseCase(
    private val characterRepository: CharacterRepository
) {
    operator fun invoke(): Flow<PagingData<CharacterModel>> =
        characterRepository.getCharactersPaged()
}
