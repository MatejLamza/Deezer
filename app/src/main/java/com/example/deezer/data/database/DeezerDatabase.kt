package com.example.deezer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.models.dbmodels.DBTopAlbums

@Database(entities = arrayOf(DBTopAlbums::class),version = 1,exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class DeezerDatabase: RoomDatabase() {
    abstract fun getDeezerDAO():DeezerDAO
}