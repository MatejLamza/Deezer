package com.example.deezer.data.network.services

import com.example.models.NetworkAlbum
import com.example.models.NetworkGenre
import com.example.models.NetworkPlaylist
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface DeezerService {


    //By using Deferred its possible for us to wait for result before proceeding with another job
    @GET("/chart/0/playlists")
    fun fetchPlaylists():Deferred<NetworkPlaylist>

    @GET("/chart/0/albums")
    fun fetchTopAlbums():Deferred<NetworkAlbum>

    @GET("genre/{idGenre}")
    fun fetchGenreById(@Path("idGenre")idGenre:Int):Deferred<NetworkGenre>

}