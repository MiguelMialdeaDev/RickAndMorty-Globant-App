package com.miguelmialdea.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class CharacterDto(
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String = "",
    @SerialName("status") val status: String = "",
    @SerialName("species") val species: String = "",
    @SerialName("type") val type: String = "",
    @SerialName("gender") val gender: String = "",
    @SerialName("origin") val origin: OriginDto,
    @SerialName("location") val location: LocationDto? = null,
    @SerialName("image") val image: String = "",
    @SerialName("episode") val episode: List<String>? = listOf()
)
