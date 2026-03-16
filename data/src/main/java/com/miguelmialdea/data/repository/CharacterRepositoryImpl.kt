package com.miguelmialdea.data.repository

import android.util.Log
import com.miguelmialdea.data.database.datasource.CharacterDataSourceLocal
import com.miguelmialdea.data.datasource.CharacterDataSource
import com.miguelmialdea.domain.model.CharacterModel
import com.miguelmialdea.domain.repository.CharacterRepository
import java.io.IOException

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
            } catch (e: IOException) {
                Log.e(TAG, "Network error fetching characters", e)
                localDataSource.getCharacters()
            } catch (e: retrofit2.HttpException) {
                Log.e(TAG, "HTTP error fetching characters", e)
                localDataSource.getCharacters()
            }
        } else {
            val localCharacters = localDataSource.getCharacters()
            localCharacters.ifEmpty {
                try {
                    val remoteCharacters = remoteDataSource.getCharacters()
                    localDataSource.saveCharacters(remoteCharacters)
                    remoteCharacters
                } catch (e: IOException) {
                    Log.e(TAG, "Network error fetching characters", e)
                    emptyList()
                } catch (e: retrofit2.HttpException) {
                    Log.e(TAG, "HTTP error fetching characters", e)
                    emptyList()
                }
            }
        }
    }

    companion object {
        private const val TAG = "CharacterRepository"
    }
}
