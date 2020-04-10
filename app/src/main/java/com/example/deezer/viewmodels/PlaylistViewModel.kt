package com.example.deezer.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.deezer.data.repositories.APIRepostiroy
import com.example.models.domain.Genre
import com.example.models.domain.Playlist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class PlaylistViewModel
@Inject constructor(val deezerRepo: APIRepostiroy) : ViewModel() {

    init {
        getPlaylists()
    }

    val data : LiveData<Playlist> = liveData(Dispatchers.IO){
        val retrive = deezerRepo.fetchPlaylists()
        emit(retrive)
    }

    val genre:LiveData<Genre> = liveData(Dispatchers.IO){
        val retrived = deezerRepo.fetchGenreByID(132)
        emit(retrived)
    }

    val liveGenres = MutableLiveData<Genre>()
    var livePlaylists = MutableLiveData<Playlist>()

    fun getGenreById(idGenre:Int){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                liveGenres.value = deezerRepo.fetchGenreByID(idGenre)
            }
        } catch (e:Exception){
            Timber.d("Error in VM while fetching genre!")
        }
    }

    fun getPlaylists(){
        try {
            viewModelScope.launch {
                livePlaylists.value = deezerRepo.fetchPlaylists()
            }
        } catch (e:Exception){
            Log.d("aaa","error fetching playlists: ${e.message}")
        }
    }
}