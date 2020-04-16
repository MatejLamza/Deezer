package com.example.deezer.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.deezer.data.repositories.APIRepostiroy
import com.example.internals.lazyDeferred
import com.example.models.domain.Genre
import com.example.models.domain.Playlist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class PlaylistViewModel
@Inject constructor(val deezerRepo: APIRepostiroy) : ViewModel() {

    var livePlaylists = liveData(Dispatchers.IO) {
        emit(deezerRepo.fetchPlaylists())
    }

    var liveGenre = liveData(Dispatchers.IO) {
        val listOfGenres = arrayListOf<Genre>()
        listOfGenres.add(deezerRepo.fetchGenreByID(116))
        listOfGenres.add(deezerRepo.fetchGenreByID(132))
        emit(listOfGenres)
    }





}