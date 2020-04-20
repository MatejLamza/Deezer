package com.example.deezer.data.database

import androidx.room.TypeConverter
import com.example.models.dbmodels.DBTopAlbums
import com.example.models.domain.Album.AlbumData
import com.example.models.domain.Playlist
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {

    @TypeConverter
    fun fromTopAlbums(albums: DBTopAlbums): String?{
        val gson = Gson()
        val type = object: TypeToken<DBTopAlbums>() {}.type
        return gson.toJson(albums,type)
    }

    @TypeConverter
    fun toTopAlbums(albumsString: String): DBTopAlbums? {
        val gson = Gson()
        val type = object: TypeToken<DBTopAlbums>() {}.type
        return gson.fromJson(albumsString,type)
    }

    @TypeConverter
    fun toAlbumData(albumsString: String): AlbumData? {
        val gson = Gson()
        val type = object: TypeToken<AlbumData>() {}.type
        return gson.fromJson(albumsString,type)
    }

    @TypeConverter
    fun fromAlbumData(albums: AlbumData): String?{
        val gson = Gson()
        val type = object: TypeToken<AlbumData>() {}.type
        return gson.toJson(albums,type)
    }

    @TypeConverter
    fun toPlaylistData(playlistString: String): Playlist.Data? {
        val gson = Gson()
        val type = object: TypeToken<Playlist.Data>() {}.type
        return gson.fromJson(playlistString,type)
    }


    @TypeConverter
        fun fromPlaylistData(data: Playlist.Data): String?{
        val gson = Gson()
        val type = object: TypeToken<Playlist.Data>() {}.type
        return gson.toJson(data,type)
    }


    @TypeConverter
    fun fromListPlaylistData(data: List<Playlist.Data>): String?{
        val gson = Gson()
        val type = object: TypeToken<List<Playlist.Data>>() {}.type
        return gson.toJson(data,type)
    }

    @TypeConverter
    fun toListPlaylistData(str: String): List<Playlist.Data>? {
        val gson = Gson()
        val type = object: TypeToken<List<Playlist.Data>>() {}.type
        return gson.fromJson(str,type)
    }

    @TypeConverter
    fun fromListAlbumData(albums: List<AlbumData>): String?{
        val gson = Gson()
        val type = object: TypeToken<List<AlbumData>>() {}.type
        return gson.toJson(albums,type)
    }

    @TypeConverter
    fun toListAlbumData(albumsString: String): List<AlbumData>? {
        val gson = Gson()
        val type = object: TypeToken<List<AlbumData>>() {}.type
        return gson.fromJson(albumsString,type)
    }


}