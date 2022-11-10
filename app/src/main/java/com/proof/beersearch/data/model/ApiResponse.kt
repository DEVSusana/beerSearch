package com.proof.beersearch.data.model

import com.google.gson.annotations.SerializedName

class ApiResponse (
    val id: Int,
    val name: String,
    val description: String,
    val tagline: String,
    @SerializedName("image_url")
    val imageURL: String,
    val abv: Float,
)