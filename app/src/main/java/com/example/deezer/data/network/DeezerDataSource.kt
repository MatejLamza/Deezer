package com.example.deezer.data.network

import androidx.lifecycle.LiveData
import com.example.models.NetworkAlbum
import com.example.models.NetworkAlbumTracks
import com.example.models.NetworkGenre
import com.example.models.NetworkPlaylist


interface DeezerDataSource {

    val downloadedPlaylist:LiveData<NetworkPlaylist>
    val downloadedTopAlbums:LiveData<NetworkAlbum>
    val downloadedGenre:LiveData<NetworkGenre>
    val downloadedTracks:LiveData<NetworkAlbumTracks>

    suspend fun fetchPlaylist()
    suspend fun fetchTopAlbums()
    suspend fun fetchGenreByID(idGenre:Int)
    suspend fun fetchAlbumTracks(idAlbum:Int)
}