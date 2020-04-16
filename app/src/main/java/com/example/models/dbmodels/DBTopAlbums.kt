package com.example.models.dbmodels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.models.domain.Album.AlbumData

const val CURRENT_TOP_ALBUMS_ID = 0

@Entity(tableName = "top_albums")
data class DBTopAlbums(
    var listOfAlbums:List<AlbumData>?,
    var numberOfAlbums:Int?
){
    @PrimaryKey(autoGenerate = false)
    var idTopAlbums = CURRENT_TOP_ALBUMS_ID
}