package com.example.deezer.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.deezer.data.repositories.APIRepostiroy
import com.example.models.domain.Album
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class TopAlbumsViewModel
    @Inject constructor(repo:APIRepostiroy):ViewModel() {

    val albums :LiveData<Album> = liveData(Dispatchers.IO) {
        val retrive = repo.fetchTopAlbums()
        emit(retrive)
    }
}