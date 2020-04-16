package com.example.internals

import com.example.models.*
import com.example.models.dbmodels.DBTopAlbums
import com.example.models.domain.*
import kotlinx.coroutines.Deferred

class ModelMapper {
    companion object {
        //Playlist
        private fun mapNetworkDataToDataModel(networkData: NetworkPlaylist.NetworkData): Playlist.Data {
            return Playlist.Data(
                networkData.title,
                networkData.nbTracks,
                networkData.picture,
                networkData.tracklist
            )
        }

        fun mapNetworkPlaylistToPlaylistModel(networkPlaylist: NetworkPlaylist): Playlist {
            return Playlist(
                mapListNetworkDataToListData(networkPlaylist.data),
                networkPlaylist.total
            )
        }

        private fun mapListNetworkDataToListData(listNetworkData: List<NetworkPlaylist.NetworkData>): List<Playlist.Data> =
            listNetworkData.map { mapNetworkDataToDataModel(it) }

        //TopAlbums
        private fun mapNetworkArtistToArtist(networkArtist: NetworkAlbum.NetworkAlbumData.NetworkArtist): Album.AlbumData.Artist {
            return Album.AlbumData.Artist(
                networkArtist.name,
                networkArtist.picture,
                networkArtist.link,
                networkArtist.tracklist
            )
        }

        private fun mapListNetworkAlbumDataToListAlbumData(listAlbum: List<NetworkAlbum.NetworkAlbumData>): List<Album.AlbumData> =
            listAlbum.map { mapNetworkDataToAlbumData(it) }

        private fun mapNetworkDataToAlbumData(networkAlbumData: NetworkAlbum.NetworkAlbumData): Album.AlbumData {
            return Album.AlbumData(
                networkAlbumData.title,
                networkAlbumData.cover,
                networkAlbumData.tracklist,
                mapNetworkArtistToArtist(networkAlbumData.artist)
            )
        }

        fun mapNetworkAlbumToAlbumModel(networkAlbum: NetworkAlbum): Album {
            return Album(
                mapListNetworkAlbumDataToListAlbumData(networkAlbum.data),
                networkAlbum.total
            )
        }

        //Genre
        fun mapNetworkGenreToGenreModel(networkGenre: NetworkGenre):Genre {
            return Genre(
                networkGenre.id,
                networkGenre.name,
                networkGenre.picture
            )
        }


        //DB MAPPING
        fun mapNetworkAlbumToDBAlbum(networkAlbum: NetworkAlbum): DBTopAlbums {
            return DBTopAlbums(
                mapListNetworkAlbumDataToListAlbumData(networkAlbum.data),
                networkAlbum.total
            )
        }

        //ERROR HERE
        fun mapDBTopAlbumsToAlbumModel(dbTopAlbums: DBTopAlbums): Album {
            return Album(
                dbTopAlbums.listOfAlbums,
                dbTopAlbums.numberOfAlbums
            )
        }

    }
}