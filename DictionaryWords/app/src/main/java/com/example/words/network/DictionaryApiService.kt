package com.example.words.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://www.dictionaryapi.com/api/v3/references/collegiate/json/"
const val API_KEY = "70144c00-3f74-488c-aa83-141127b49af5"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface DictionaryApiService {
    @GET("{searchWord}?key=${API_KEY}")
    suspend fun getWord(@Path("searchWord") word: String): Response<String>
}

object DictionaryApi {
    val retrofitService : DictionaryApiService by lazy {
        retrofit.create(DictionaryApiService::class.java) }
}

enum class WordApiFilter(val value: String) {
    SHOW_ALL("all"),
    SHOW_ACTIVE("active"),
    SHOW_INACTIVE("inactive")}