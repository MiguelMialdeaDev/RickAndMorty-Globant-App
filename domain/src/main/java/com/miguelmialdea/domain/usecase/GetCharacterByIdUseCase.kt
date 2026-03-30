package com.miguelmialdea.domain.usecase

import com.miguelmialdea.domain.model.CharacterModel
import com.miguelmialdea.domain.repository.CharacterRepository

class GetCharacterByIdUseCase(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(id: Int): Result<CharacterModel> =
        characterRepository.getCharacterById(id)
}
