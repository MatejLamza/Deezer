package com.example.models.domain

data class Playlist(
    var data:List<Data>?,
    var total:Int?
){
    data class Data(
        var title:String?,
        var nBTracks:Int?,
        var picture:String?,
        var tracklist:String?
    )
}