package com.example.deezer.viewmodels.modules

import com.example.deezer.data.repositories.APIRepostiroy
import com.example.deezer.viewmodels.factories.AlbumTracksVMFactory
import dagger.Module
import dagger.Provides

@Module
class AlbumTracksModule {
    @Provides
    fun provideAlbumTracksVMFactory(repo:APIRepostiroy):AlbumTracksVMFactory{
        return AlbumTracksVMFactory(repo)
    }
}