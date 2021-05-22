package com.example.project3.api

import com.example.project3.api.RickMorty
import com.squareup.moshi.Json

data class RickMortyCharacters (
    @Json(name="results")
    var characters: List<RickMorty>
)