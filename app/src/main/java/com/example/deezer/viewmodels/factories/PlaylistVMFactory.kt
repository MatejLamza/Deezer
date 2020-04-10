package com.example.deezer.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.deezer.data.repositories.APIRepostiroy
import com.example.deezer.viewmodels.PlaylistViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

class PlaylistVMFactory
@Inject constructor(val repo:APIRepostiroy):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PlaylistViewModel::class.java)){
            return PlaylistViewModel(repo) as T
        } else{
            throw IllegalArgumentException("Unknown class PlaylistViewModel!")
        }
    }
}