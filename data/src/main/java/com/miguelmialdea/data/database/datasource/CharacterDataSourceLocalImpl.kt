package com.miguelmialdea.data.database.datasource

import com.miguelmialdea.data.database.dao.CharacterDao
import com.miguelmialdea.data.mapper.toEntity
import com.miguelmialdea.data.mapper.toModel
import com.miguelmialdea.domain.model.CharacterModel

class CharacterDataSourceLocalImpl(
    private val characterDao: CharacterDao
): CharacterDataSourceLocal {

    override suspend fun getCharacters(): List<CharacterModel> =
        characterDao.getAllCharacters().map { it.toModel() }

    override suspend fun saveCharacters(characters: List<CharacterModel>) =
        characterDao.saveCharacters(characters.map { it.toEntity() })

    override suspend fun removeCharacters() =
        characterDao.deleteAllCharacters()
}