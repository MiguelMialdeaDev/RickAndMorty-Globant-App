package com.miguelmialdea.data.mapper

import com.miguelmialdea.data.database.entity.CharacterEntity
import com.miguelmialdea.data.dto.CharacterDto
import com.miguelmialdea.data.dto.CharactersResponse
import com.miguelmialdea.data.dto.LocationDto
import com.miguelmialdea.data.dto.OriginDto
import com.miguelmialdea.domain.model.CharacterModel
import com.miguelmialdea.domain.model.LocationModel
import com.miguelmialdea.domain.model.OriginModel

fun CharacterDto.toModel() =
    CharacterModel(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        type = this.type,
        gender = this.gender,
        origin = this.origin.toModel(),
        location = this.location.toModel(),
        image = this.image,
        episode = this.episode
    )

fun OriginDto.toModel() =
    OriginModel(
        name = this.name
    )

fun LocationDto.toModel() =
    LocationModel(
        name = this.name
    )

fun CharacterEntity.toModel() =
    CharacterModel(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        type = this.type,
        gender = this.gender,
        origin = OriginModel(this.origin),
        location = LocationModel(this.location),
        image = this.image,
        episode = this.episode
    )

fun CharacterModel.toEntity() =
    CharacterEntity(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        type = this.type,
        gender = this.gender,
        origin = this.origin.name,
        location = this.location.name,
        image = this.image,
        episode = this.episode
    )
