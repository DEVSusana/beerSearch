package com.proof.beersearch.data.model

import com.google.gson.annotations.SerializedName

class ApiResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("image_url")
    val imageURL: String,
    @SerializedName("abv")
    val abv: Float
)