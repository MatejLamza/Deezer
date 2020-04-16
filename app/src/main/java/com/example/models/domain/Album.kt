package com.example.models.domain

data class Album(
    var data:List<AlbumData>?,
    var total:Int?
){
    data class AlbumData(
        val title:String?,
        val cover:String?,
        val tracklist:String?,
        val artist:Artist?
    ){
        data class Artist(
            val name:String?,
            val picture:String?,
            val link:String?,
            val trackList:String?
        )
    }
}