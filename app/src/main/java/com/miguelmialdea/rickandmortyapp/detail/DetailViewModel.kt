package com.miguelmialdea.rickandmortyapp.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguelmialdea.domain.model.CharacterModel
import com.miguelmialdea.domain.usecase.GetCharacterByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DetailState {
    object Loading : DetailState()
    data class Success(val character: CharacterModel) : DetailState()
    data class Error(val message: String) : DetailState()
}

class DetailViewModel(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<DetailState>(DetailState.Loading)
    val state: StateFlow<DetailState> = _state.asStateFlow()

    fun loadCharacter(id: Int) {
        viewModelScope.launch {
            _state.value = DetailState.Loading
            getCharacterByIdUseCase(id).fold(
                onSuccess = { character ->
                    _state.value = DetailState.Success(character)
                },
                onFailure = { error ->
                    _state.value = DetailState.Error(error.message ?: "Unknown error")
                }
            )
        }
    }
}
