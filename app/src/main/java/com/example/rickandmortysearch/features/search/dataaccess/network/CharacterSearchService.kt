package com.example.rickandmortysearch.features.search.dataaccess.network

import com.example.rickandmortysearch.BuildConfig
import com.example.rickandmortysearch.features.search.dataaccess.model.CharacterResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.time.Duration.Companion.seconds

interface CharacterSearchService {
    @GET("character")
    suspend fun searchCharacters(@Query("name") query: String): Response<CharacterResponse>

    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/api/"

        private val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
            coerceInputValues = true
        }

        private fun createHttpClient(): OkHttpClient {
            val loggerInterceptor = HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            }
            return OkHttpClient
                .Builder()
                .addInterceptor(loggerInterceptor)
                .connectTimeout(20.seconds)
                .readTimeout(20.seconds)
                .build()
        }

        fun create(): CharacterSearchService {
            val factory = json.asConverterFactory("application/json".toMediaType())
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createHttpClient())
                .addConverterFactory(factory)
                .build()
                .create(CharacterSearchService::class.java)
        }
    }
}