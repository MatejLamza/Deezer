package com.example.internals

import com.example.models.*
import com.example.models.dbmodels.DBTopAlbums
import com.example.models.domain.*

class ModelMapper {
    companion object {

        fun mapNetworkPlaylistToPlaylistModel(networkPlaylist: NetworkPlaylist): Playlist {
            return Playlist(
                mapListNetworkDataToListData(networkPlaylist.data),
                networkPlaylist.total
            )
        }

        fun mapNetworkAlbumToAlbumModel(networkAlbum: NetworkAlbum): Album {
            return Album(
                mapListNetworkAlbumDataToListAlbumData(networkAlbum.data),
                networkAlbum.total
            )
        }

        fun mapNetworkGenreToGenreModel(networkGenre: NetworkGenre): Genre {
            return Genre(
                networkGenre.id,
                networkGenre.name,
                networkGenre.picture
            )
        }

        fun mapNetworkAlbumTracks(networkAlbumTracks: NetworkAlbumTracks):AlbumTracks{
            return AlbumTracks(
                mapListNetworkAlbumTracksData(networkAlbumTracks.data),
                networkAlbumTracks.total
            )
        }

        private fun mapNetworkDataToAlbumData(networkAlbumData: NetworkAlbum.NetworkAlbumData): Album.AlbumData {
            return Album.AlbumData(
                networkAlbumData.id,
                networkAlbumData.title,
                networkAlbumData.cover,
                networkAlbumData.tracklist,
                mapNetworkArtistToArtist(networkAlbumData.artist)
            )
        }

        private fun mapNetworkDataToDataModel(networkData: NetworkPlaylist.NetworkData): Playlist.Data {
            return Playlist.Data(
                networkData.title,
                networkData.nbTracks,
                networkData.picture,
                networkData.tracklist
            )
        }

        private fun mapNetworkArtistToArtist(networkArtist: NetworkAlbum.NetworkAlbumData.NetworkArtist): Album.AlbumData.Artist {
            return Album.AlbumData.Artist(
                networkArtist.name,
                networkArtist.picture,
                networkArtist.link,
                networkArtist.tracklist
            )
        }

        private fun mapNetworkAlbumTracksData(network: NetworkAlbumTracks.NetworkAlbumTracksData): AlbumTracks.AlbumTracksData {
            return AlbumTracks.AlbumTracksData(
                mapNetworkArtistToArtist(network.artist),
                network.id,
                network.title,
                network.preview
            )
        }

        private fun mapListNetworkAlbumTracksData(network: List<NetworkAlbumTracks.NetworkAlbumTracksData>): List<AlbumTracks.AlbumTracksData> =
            network.map { mapNetworkAlbumTracksData(it) }

        private fun mapListNetworkDataToListData(listNetworkData: List<NetworkPlaylist.NetworkData>): List<Playlist.Data> =
            listNetworkData.map { mapNetworkDataToDataModel(it) }

        private fun mapListNetworkAlbumDataToListAlbumData(listAlbum: List<NetworkAlbum.NetworkAlbumData>): List<Album.AlbumData> =
            listAlbum.map { mapNetworkDataToAlbumData(it) }


        //DB MAPPING
        fun mapNetworkAlbumToDBAlbum(networkAlbum: NetworkAlbum): DBTopAlbums {
            return DBTopAlbums(
                mapListNetworkAlbumDataToListAlbumData(networkAlbum.data),
                networkAlbum.total
            )
        }

        fun mapDBTopAlbumsToAlbumModel(dbTopAlbums: DBTopAlbums): Album {
            return Album(
                dbTopAlbums.listOfAlbums,
                dbTopAlbums.numberOfAlbums
            )
        }

    }
}