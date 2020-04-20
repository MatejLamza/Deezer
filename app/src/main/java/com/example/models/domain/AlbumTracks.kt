package com.example.models.domain

class AlbumTracks(
    var tracks: List<AlbumTracksData>,
    var total: Int
) {
    data class AlbumTracksData(
        var artist: Album.AlbumData.Artist,
        var id: Int = 0,
        var title: String = "",
        var preview: String = ""
    )
}