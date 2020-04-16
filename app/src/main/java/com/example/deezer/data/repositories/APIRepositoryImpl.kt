package com.example.deezer.data.repositories

import android.util.Log
import android.view.Display
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.deezer.data.database.DeezerDAO
import com.example.deezer.data.network.DeezerDataSource
import com.example.internals.ModelMapper
import com.example.models.NetworkGenre
import com.example.models.NetworkPlaylist
import com.example.models.dbmodels.DBTopAlbums
import com.example.models.domain.Album
import com.example.models.domain.Genre
import com.example.models.domain.Playlist
import kotlinx.coroutines.*
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

class APIRepositoryImpl
@Inject constructor(
    val deezerDS: DeezerDataSource,
    val deezerDAO: DeezerDAO
) : APIRepostiroy {

    //This class doesent have life cycle so we don't have to be worried about this repo being destroyed
    init {
        deezerDS.downloadedTopAlbums.observeForever {
            //TODO Mapping then persisting
            //Check if null
            if (it != null) {
                persistFetchedTopAlbums(ModelMapper.mapNetworkAlbumToDBAlbum(it))
            }
        }
    }

    override suspend fun fetchGenreByID(genreID: Int): Genre {
        return withContext(Dispatchers.Main) {
            deezerDS.fetchGenreByID(genreID)
            return@withContext ModelMapper.mapNetworkGenreToGenreModel(deezerDS.downloadedGenre.value!!)
        }
    }

    override suspend fun fetchTopAlbums(): Album {
        return withContext(Dispatchers.Main) {
            //TODO check if fetch is needed from server or from cache
            initTopAlbumsData()
//            ModelMapper.mapDBTopAlbumsToAlbumModel(deezerDAO.getTopAlbumsFromCache())
            return@withContext ModelMapper.mapNetworkAlbumToAlbumModel(deezerDS.downloadedTopAlbums.value!!)
        }
    }

    override suspend fun fetchPlaylists(): Playlist {
        return withContext(Dispatchers.Main) {
            deezerDS.fetchPlaylist()
            return@withContext ModelMapper.mapNetworkPlaylistToPlaylistModel(deezerDS.downloadedPlaylist.value!!)
        }
    }

    private suspend fun initTopAlbumsData() {
        //TODO Get Last Time fetched
        if (isFetchNeeded(ZonedDateTime.now().minusHours(1))) {
            fetchAlbums()
        } else {

        }
    }

    //Fetch TopAlbums From network
    private suspend fun fetchAlbums() {
        deezerDS.fetchTopAlbums()
    }

    //Helper Methods
    private fun persistFetchedTopAlbums(dbTopAlbums: DBTopAlbums) {
        GlobalScope.launch(Dispatchers.IO) {
            deezerDAO.upsertTopAlbums(dbTopAlbums)
        }
    }

    private fun isFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }

}