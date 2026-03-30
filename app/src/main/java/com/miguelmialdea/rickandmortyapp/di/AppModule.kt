package com.miguelmialdea.rickandmortyapp.di

import com.miguelmialdea.rickandmortyapp.detail.DetailViewModel
import com.miguelmialdea.rickandmortyapp.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}