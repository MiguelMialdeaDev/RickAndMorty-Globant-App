package com.miguelmialdea.rickandmortyapp.nav

import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    data object Home : Routes

    @Serializable
    data class Detail(val id: Int) : Routes
}