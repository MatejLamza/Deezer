package com.example.models.domain

import java.io.Serializable

data class Album(
    var data: List<AlbumData>?,
    var total: Int?
):Serializable {
    data class AlbumData(
        val id: Int? = 0,
        val title: String?,
        val cover: String?,
        val tracklist: String?,
        val artist: Artist?
    ):Serializable {
        data class Artist(
            val name: String?,
            val picture: String?,
            val link: String?,
            val trackList: String?
        ):Serializable
    }
}