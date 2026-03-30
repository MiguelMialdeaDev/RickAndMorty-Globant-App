package com.miguelmialdea.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.miguelmialdea.data.ApiService
import com.miguelmialdea.data.database.AppDatabase
import com.miguelmialdea.data.mapper.toModel
import com.miguelmialdea.data.paging.CharacterRemoteMediator
import com.miguelmialdea.domain.model.CharacterModel
import com.miguelmialdea.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterRepositoryImpl(
    private val apiService: ApiService,
    private val database: AppDatabase,
    private val remoteDataSource: com.miguelmialdea.data.datasource.CharacterDataSource
) : CharacterRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getCharactersPaged(query: String): Flow<PagingData<CharacterModel>> {
        val searchQuery = query.trim().takeIf { it.isNotBlank() }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                initialLoadSize = 40,
                prefetchDistance = 10,
                maxSize = 200
            ),
            remoteMediator = CharacterRemoteMediator(
                query = searchQuery,
                apiService = apiService,
                database = database
            ),
            pagingSourceFactory = {
                if (searchQuery != null) {
                    database.characterDao().searchCharactersPaged(searchQuery)
                } else {
                    database.characterDao().getAllCharactersPaged()
                }
            }
        ).flow.map { pagingData ->
            pagingData.map { it.toModel() }
        }
    }

    override suspend fun getCharacterById(id: Int): Result<CharacterModel> {
        return try {
            val character = remoteDataSource.getCharacterById(id)
            Result.success(character.toModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
