package com.example.deezer.data.repositories

import com.example.models.dbmodels.DBTopAlbums
import com.example.models.domain.Album
import com.example.models.domain.Genre
import com.example.models.domain.Playlist

interface APIRepostiroy {

    suspend fun fetchPlaylists():Playlist
    suspend fun fetchTopAlbums():Album
    suspend fun fetchGenreByID(genreID:Int):Genre
}