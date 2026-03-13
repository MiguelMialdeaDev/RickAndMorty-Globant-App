package com.miguelmialdea.data.repository

import com.miguelmialdea.data.database.datasource.CharacterDataSourceLocal
import com.miguelmialdea.data.datasource.CharacterDataSource
import com.miguelmialdea.domain.model.CharacterModel
import com.miguelmialdea.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val remoteDataSource: CharacterDataSource,
    private val localDataSource: CharacterDataSourceLocal
): CharacterRepository {
    override suspend fun getCharacters(forceRefresh: Boolean): List<CharacterModel> {
        return if (forceRefresh) {
            try {
                val remoteCharacters = remoteDataSource.getCharacters()
                localDataSource.removeCharacters()
                localDataSource.saveCharacters(remoteCharacters)
                remoteCharacters
            } catch (e: Exception) {
                localDataSource.getCharacters()
            }
        } else {
            val localCharacters = localDataSource.getCharacters()
            localCharacters.ifEmpty {
                try {
                    val remoteCharacters = remoteDataSource.getCharacters()
                    localDataSource.saveCharacters(remoteCharacters)
                    remoteCharacters
                } catch (e: Exception) {
                    emptyList()
                }
            }
        }
    }
}