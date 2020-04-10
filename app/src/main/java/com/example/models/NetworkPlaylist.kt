package com.example.models

import com.google.gson.annotations.SerializedName


data class NetworkPlaylist(
    var `data`: List<NetworkData>,
    var total: Int
){
    data class NetworkData(
        var checksum: String,
        @SerializedName("creation_date")
        var creationDate: String,
        var id: Long,
        var link: String,
        @SerializedName("nb_tracks")
        var nbTracks: Int,
        var picture: String,
        @SerializedName("picture_big")
        var pictureBig: String,
        @SerializedName("picture_medium")
        var pictureMedium: String,
        @SerializedName("picture_small")
        var pictureSmall: String,
        @SerializedName("picture_xl")
        var pictureXl: String,
        var `public`: Boolean,
        var title: String,
        var tracklist: String,
        var type: String,
        var user: NetworkUser
    ){
        data class NetworkUser(
            var id: Long,
            var name: String,
            var tracklist: String,
            var type: String
        )
    }
}