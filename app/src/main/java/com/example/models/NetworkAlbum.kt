package com.example.models

import com.google.gson.annotations.SerializedName


data class NetworkAlbum(
    var `data`: List<NetworkAlbumData>,
    var total: Int
){
    data class NetworkAlbumData(
        var artist: NetworkArtist,
        var cover: String,
        @SerializedName("cover_big")
        var coverBig: String,
        @SerializedName("cover_medium")
        var coverMedium: String,
        @SerializedName("cover_small")
        var coverSmall: String,
        @SerializedName("cover_xl")
        var coverXl: String,
        @SerializedName("explicit_lyrics")
        var explicitLyrics: Boolean,
        var id: Int,
        var link: String,
        var position: Int,
        @SerializedName("record_type")
        var recordType: String,
        var title: String,
        var tracklist: String,
        var type: String
    ){
        data class NetworkArtist(
            var id: Int,
            var link: String,
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
            var radio: Boolean,
            var tracklist: String,
            var type: String
        )
    }
}