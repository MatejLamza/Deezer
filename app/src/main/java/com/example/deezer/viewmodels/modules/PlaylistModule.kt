package com.example.deezer.viewmodels.modules

import com.example.deezer.data.repositories.APIRepostiroy
import com.example.deezer.viewmodels.factories.PlaylistVMFactory
import dagger.Module
import dagger.Provides

@Module
class PlaylistModule {
    @Provides
    fun providePlaylistVMFactory(repo:APIRepostiroy):PlaylistVMFactory{
        return PlaylistVMFactory(repo)
    }
}