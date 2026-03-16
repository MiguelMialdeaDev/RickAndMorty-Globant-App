package com.miguelmialdea.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class CharactersResponse(
    @SerialName("results") val results: List<CharacterDto>
)
