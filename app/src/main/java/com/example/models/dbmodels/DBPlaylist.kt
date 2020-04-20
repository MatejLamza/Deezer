package com.example.models.dbmodels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.models.domain.Playlist

const val CURRENT_PLAYLISTS_ID = 0

@Entity(tableName = "playlists")
class DBPlaylist(
    var data:List<Playlist.Data>?,
    var total:Int?
){
    @PrimaryKey(autoGenerate = false)
    var idPlaylist = CURRENT_PLAYLISTS_ID

}