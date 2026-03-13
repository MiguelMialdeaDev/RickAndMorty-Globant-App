package com.miguelmialdea.data.di

import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.miguelmialdea.data.ApiService
import com.miguelmialdea.data.database.AppDatabase
import com.miguelmialdea.data.database.datasource.CharacterDataSourceLocal
import com.miguelmialdea.data.database.datasource.CharacterDataSourceLocalImpl
import com.miguelmialdea.data.datasource.CharacterDataSource
import com.miguelmialdea.data.datasource.CharacterDataSourceImpl
import com.miguelmialdea.data.repository.CharacterRepositoryImpl
import com.miguelmialdea.domain.repository.CharacterRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val dataModule = module {

    // Json
    single {
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
    }

    // OkHttpClient
    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor as okhttp3.Interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    // Retrofit
    single {
        val contentType = "application/json".toMediaType()

        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .client(get())
            .addConverterFactory(get<Json>().asConverterFactory(contentType))
            .build()
    }

    single {
        get<Retrofit>().create(ApiService::class.java)
    }

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "rickandmorty_db"
        ).build(
        )
    }

    single { get<AppDatabase>().characterDao() }

    single<CharacterDataSource> {
        CharacterDataSourceImpl(get())
    }
    single<CharacterDataSourceLocal> {
        CharacterDataSourceLocalImpl(get())
    }

    single<CharacterRepository> {
        CharacterRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }
}