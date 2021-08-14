package com.example.project3.api

import com.example.project3.api.ApiService

class Repository(private val apiService: ApiService) {


    suspend fun getCharacters()=apiService.getCharacts()

    suspend fun getEpisodes() = apiService.getEpisodes()

    suspend fun getEpisode(id:Int) =apiService.getEpisode(id)

    suspend fun getCharacterDetails(id: Int) = apiService.getCharacterDetails(id)

}