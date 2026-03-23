package com.miguelmialdea.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoDto(
    @SerialName("count") val count: Int = 0,
    @SerialName("pages") val pages: Int = 1,
    @SerialName("next") val next: String? = null,
    @SerialName("prev") val prev: String? = null
)