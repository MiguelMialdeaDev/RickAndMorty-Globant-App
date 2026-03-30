package com.miguelmialdea.domain.di

import com.miguelmialdea.domain.usecase.GetCharacterByIdUseCase
import com.miguelmialdea.domain.usecase.GetCharactersUseCase
import org.koin.dsl.module

val domainModule = module {

    single { GetCharactersUseCase(get()) }
    single { GetCharacterByIdUseCase(get()) }
}