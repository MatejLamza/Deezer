package com.example.deezer.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.models.dbmodels.CURRENT_TOP_ALBUMS_ID
import com.example.models.dbmodels.DBTopAlbums

@Dao
interface DeezerDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertTopAlbums(albums:DBTopAlbums)

    @Query("SELECT* FROM top_albums WHERE idTopAlbums = $CURRENT_TOP_ALBUMS_ID")
    fun getTopAlbumsFromCache():DBTopAlbums
}