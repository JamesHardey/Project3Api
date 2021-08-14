package com.example.project3.api.model

import com.squareup.moshi.Json
import java.io.Serializable

data class RickMorty (

    @Json(name="id")
    val id:Int,

    @Json(name="name")
    val name:String,

    @Json(name="status")
    val status:String,

    @Json(name="species")
    val species:String,

    @Json(name="image")
    val image:String,

    @Json(name="gender")
    val gender:String,

    @Json(name="created")
    val created:String,

    @Json(name="episode")
    val episodes:List<String>

) : Serializable

data class Origin(
    @Json(name="name")
    val name:String,

    @Json(name="url")
    val url:String
)
