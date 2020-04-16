package com.example.deezer.data.repositories

import androidx.lifecycle.LiveData
import com.example.models.NetworkGenre
import com.example.models.NetworkPlaylist
import com.example.models.domain.Album
import com.example.models.domain.Genre
import com.example.models.domain.Playlist

interface APIRepostiroy {

    suspend fun fetchPlaylists():Playlist
    suspend fun fetchTopAlbums():Album
    suspend fun fetchGenreByID(genreID:Int):Genre

}