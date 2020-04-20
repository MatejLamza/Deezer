package com.example.deezer.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.deezer.data.repositories.APIRepostiroy
import com.example.models.domain.AlbumTracks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class AlbumTracksViewModel
@Inject constructor(val repo: APIRepostiroy) : ViewModel() {

    var liveAlbumTracks = MutableLiveData<AlbumTracks>()


    fun getAlbumTracks(albumID:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                liveAlbumTracks.postValue(repo.fetchAlbumTracks(albumID))
            } catch (e:Exception){
                Log.d("aaa","Exception while fetching album tracks: ${e.message}")
            }
        }
    }

}