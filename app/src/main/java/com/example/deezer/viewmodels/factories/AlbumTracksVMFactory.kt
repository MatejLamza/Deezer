package com.example.deezer.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.deezer.data.repositories.APIRepostiroy
import com.example.deezer.viewmodels.AlbumTracksViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

class AlbumTracksVMFactory
    @Inject constructor(val repo:APIRepostiroy):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumTracksViewModel::class.java)){
            return AlbumTracksViewModel(repo) as T
        } else{
            throw IllegalArgumentException("Unknown class AlbumTracksViewModel !!")
        }
    }
}