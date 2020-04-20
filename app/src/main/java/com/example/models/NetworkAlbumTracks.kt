package com.example.models


import com.google.gson.annotations.SerializedName

data class NetworkAlbumTracks(
    var `data`: List<NetworkAlbumTracksData> = listOf(),
    var total: Int = 0
){
    data class NetworkAlbumTracksData(
        var artist: NetworkAlbum.NetworkAlbumData.NetworkArtist,
        @SerializedName("disk_number")
        var diskNumber: Int = 0,
        var duration: Int = 0,
        @SerializedName("explicit_content_cover")
        var explicitContentCover: Int = 0,
        @SerializedName("explicit_content_lyrics")
        var explicitContentLyrics: Int = 0,
        @SerializedName("explicit_lyrics")
        var explicitLyrics: Boolean = false,
        var id: Int = 0,
        var isrc: String = "",
        var link: String = "",
        var preview: String = "",
        var rank: Int = 0,
        var readable: Boolean = false,
        var title: String = "",
        @SerializedName("title_short")
        var titleShort: String = "",
        @SerializedName("title_version")
        var titleVersion: String = "",
        @SerializedName("track_position")
        var trackPosition: Int = 0,
        var type: String = ""
    )
}