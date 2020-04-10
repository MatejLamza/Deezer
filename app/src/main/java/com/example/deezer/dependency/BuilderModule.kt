package com.example.deezer.dependency

import com.example.deezer.viewmodels.modules.PlaylistModule
import com.example.deezer.viewmodels.modules.TopAlbumsModule
import com.example.views.fragments.AlbumsFragment
import com.example.views.fragments.FeedFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule{
    @ContributesAndroidInjector(modules = arrayOf(PlaylistModule::class))
    abstract fun bindsFeedFragment():FeedFragment

    @ContributesAndroidInjector(modules = arrayOf(TopAlbumsModule::class))
    abstract fun bindsAlbumsFragment():AlbumsFragment

}