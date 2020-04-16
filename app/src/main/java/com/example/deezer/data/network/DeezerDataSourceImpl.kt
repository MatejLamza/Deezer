package com.example.deezer.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.deezer.data.network.services.DeezerService
import com.example.deezer.utils.NoConnectivityException
import com.example.models.NetworkAlbum
import com.example.models.NetworkGenre
import com.example.models.NetworkPlaylist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class DeezerDataSourceImpl
@Inject constructor(val deezerService: DeezerService) : DeezerDataSource {

    private val _downloadedPlaylist = MutableLiveData<NetworkPlaylist>()
    private val _downloadedTopAlbums = MutableLiveData<NetworkAlbum>()
    private val _downloadedGenre = MutableLiveData<NetworkGenre>()

    override val downloadedGenre: LiveData<NetworkGenre>
        get() = _downloadedGenre

    override val downloadedTopAlbums: LiveData<NetworkAlbum>
        get() = _downloadedTopAlbums

    override val downloadedPlaylist: LiveData<NetworkPlaylist>
        get() = _downloadedPlaylist //This line of code automatically casts MutableLiveData to LiveData so client code
    //requesting downloaded Playlists cant change the value. The only place where we can change value of downloaded data
    //is here in DataSourceImplementation

    override suspend fun fetchGenreByID(idGenre: Int) {
        try {
            withContext(Dispatchers.IO){
                val fetchedGenre = deezerService.fetchGenreById(idGenre).await()
                _downloadedGenre.postValue(fetchedGenre)
            }
        } catch (e: NoConnectivityException) {
            Timber.d("Error fetching genre, no connection!")
        }
    }

    //TODO delete after debugging
    override suspend fun fetchTopAlbums() {
        try {
            withContext(Dispatchers.IO){
                val fetchedTopAlbums = deezerService.fetchTopAlbums().await()
                _downloadedTopAlbums.postValue(fetchedTopAlbums)
            }
        } catch (e: NoConnectivityException) {
            Timber.d("Error while fecthing Top Albums no connection!")
        }
    }

    override suspend fun fetchPlaylist() {
        try {
            withContext(Dispatchers.IO){
                val fetchedNetworkPlaylist = deezerService.fetchPlaylists().await()
                _downloadedPlaylist.postValue(fetchedNetworkPlaylist)
            }
        } catch (e: NoConnectivityException) {
            //TODO Handle exception properly
            Timber.d("Error with connection!")
        }
    }
}