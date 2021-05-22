package com.example.project3.api

import com.squareup.moshi.Json

data class RickMorty (
    @Json(name="name")
    var name:String,

    @Json(name="status")
    var status:String,

    @Json(name="species")
    var species:String,

    @Json(name="image")
    var image:String
)