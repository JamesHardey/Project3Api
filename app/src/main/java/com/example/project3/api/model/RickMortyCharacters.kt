package com.example.project3.api.model

import com.squareup.moshi.Json

data class RickMortyCharacters (
    @Json(name="results")
    var characters: List<RickMorty>
)