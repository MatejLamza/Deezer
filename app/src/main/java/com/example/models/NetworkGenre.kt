package com.example.models


import com.google.gson.annotations.SerializedName

data class NetworkGenre(
    var id: Int,
    var name: String,
    var picture: String,
    @SerializedName("picture_big")
    var pictureBig: String,
    @SerializedName("picture_medium")
    var pictureMedium: String,
    @SerializedName("picture_small")
    var pictureSmall: String,
    @SerializedName("picture_xl")
    var pictureXl: String,
    var type: String
)