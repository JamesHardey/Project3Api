package com.example.project3.api

import com.example.project3.api.model.Episode
import com.example.project3.api.model.Episodes
import com.example.project3.api.model.RickMorty
import com.example.project3.api.model.RickMortyCharacters
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private  val BASE_URL ="https://rickandmortyapi.com/"

interface ApiService {

    @GET("api/character")
    suspend fun getCharacts(): RickMortyCharacters

    @GET("api/character/{id}")
    suspend fun getCharacterDetails(@Path("id") id:Int):RickMorty

    @GET("api/episode")
    suspend fun getEpisodes():Episodes

    @GET("api/episode/{id}")
    suspend fun getEpisode(@Path("id") id:Int):Episode

}

val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object Api{
    val apiService: ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }
}