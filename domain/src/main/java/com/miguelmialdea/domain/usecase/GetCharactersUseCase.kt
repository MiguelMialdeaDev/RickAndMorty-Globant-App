package com.miguelmialdea.domain.usecase

import com.miguelmialdea.domain.model.CharacterModel
import com.miguelmialdea.domain.repository.CharacterRepository

class GetCharactersUseCase(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(forceRefresh: Boolean = false): List<CharacterModel> =
        characterRepository.getCharacters(forceRefresh)
}
