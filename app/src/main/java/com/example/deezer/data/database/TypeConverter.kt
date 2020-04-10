package com.example.deezer.data.database

import androidx.room.TypeConverter
import com.example.models.dbmodels.DBTopAlbums
import com.example.models.domain.Album.AlbumData
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