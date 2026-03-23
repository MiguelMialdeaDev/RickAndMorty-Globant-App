package com.miguelmialdea.rickandmortyapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.miguelmialdea.domain.model.CharacterModel
import com.miguelmialdea.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.flow.Flow

class HomeViewModel(
    getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    val pagingDataFlow: Flow<PagingData<CharacterModel>> =
        getCharactersUseCase()
            .cachedIn(viewModelScope)
}