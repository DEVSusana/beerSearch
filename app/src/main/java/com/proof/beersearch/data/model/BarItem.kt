package com.example.beersapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//Retrofit
data class BarItem(
    val abv: Double? = null,
    val attenuation_level: Double? = null,
    val boil_volume: BoilVolume? = null,
    val brewers_tips: String? = null,
    val contributed_by: String? = null,
    val description: String? = null,
    val ebc: Double? = null,
    val first_brewed: String? = null,
    val food_pairing: List<String>? = null,
    val ibu: Int? = null,
    val id: Int,
    @SerializedName("image_url")
    val photo: String? = null,
    val ingredients: Ingredients? = null,
    val method: Method? = null,
    val name: String? = null,
    val ph: Double? = null,
    val srm: Double? = null,
    val tagline: String? = null,
    val target_fg: Int? = null,
    val target_og: Int? = null,
    val volume: Volume? = null,
    var isFavorite:Boolean = false
)

//Room
@Entity(tableName = "FavoriteItemEntity")
data class FavoriteItemEntity(
    @PrimaryKey
    val id: Int
)

@Entity(tableName = "ItemEntity")
data class ItemEntity(
    val abv: Double? = null,
    val attenuation_level: Double? = null,
    val boil_volume: BoilVolume? = null,
    val brewers_tips: String? = null,
    val contributed_by: String? = null,
    val description: String? = null,
    val ebc: Double? = null,
    val first_brewed: String? = null,
    val food_pairing: List<String>? = null,
    val ibu: Int? = null,
    @PrimaryKey
    val id: Int,
    @SerializedName("image_url")
    val photo: String? = null,
    val ingredients: Ingredients? = null,
    val method: Method? = null,
    val name: String? = null,
    val ph: Double? = null,
    val srm: Double? = null,
    val tagline: String? = null,
    val target_fg: Int? = null,
    val target_og: Int? = null,
    val volume: Volume? = null,
    var isFavorite:Boolean = false
)