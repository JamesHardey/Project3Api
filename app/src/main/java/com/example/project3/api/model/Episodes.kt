package com.example.project3.api.model

import com.squareup.moshi.Json

data class Episodes(

    @Json(name = "results")
    val episodes: List<Episode>

    )
