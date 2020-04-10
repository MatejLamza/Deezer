package com.example.models.dbmodels

import com.example.models.domain.Album.AlbumData.Artist

data class DBAlbumData(
    val title:String,
    val cover:String,
    val tracklist:String,
    val artist: Artist
)