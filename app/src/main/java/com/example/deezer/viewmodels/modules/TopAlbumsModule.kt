package com.example.deezer.viewmodels.modules

import com.example.deezer.data.repositories.APIRepostiroy
import com.example.deezer.viewmodels.factories.TopAlbumsVMFactory
import dagger.Module
import dagger.Provides

@Module
class TopAlbumsModule {
    @Provides
    fun provideTopAlbumsVMFactory(repo:APIRepostiroy):TopAlbumsVMFactory{
        return TopAlbumsVMFactory(repo)
    }
}