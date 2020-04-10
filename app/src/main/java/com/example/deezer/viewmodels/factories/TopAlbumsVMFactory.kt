package com.example.deezer.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.deezer.data.repositories.APIRepostiroy
import com.example.deezer.viewmodels.TopAlbumsViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

class TopAlbumsVMFactory
@Inject constructor(val repo: APIRepostiroy) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TopAlbumsViewModel::class.java)) {
            return TopAlbumsViewModel(repo) as T
        } else{
            throw IllegalArgumentException("Unknown class TopAlbumViewModel!!")
        }
    }
}