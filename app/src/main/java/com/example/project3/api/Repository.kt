package com.example.project3.api

import com.example.project3.api.ApiService

class Repository(private val apiService: ApiService) {

    suspend fun getCharacters() = apiService.getCharacts()

}