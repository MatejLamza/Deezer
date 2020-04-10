package com.example.deezer.dependency

import android.content.Context
import androidx.room.Room
import com.example.deezer.base.BaseApp
import com.example.deezer.data.database.DeezerDAO
import com.example.deezer.data.database.DeezerDatabase
import com.example.deezer.data.network.ConnectivityInterceptor
import com.example.deezer.data.network.ConnectivityInterceptorImpl
import com.example.deezer.data.network.DeezerDataSource
import com.example.deezer.data.network.DeezerDataSourceImpl
import com.example.deezer.data.network.services.DeezerService
import com.example.deezer.data.repositories.APIRepositoryImpl
import com.example.deezer.data.repositories.APIRepostiroy
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


private const val BASE_URL = "https://api.deezer.com"
private const val DB_NAME = "DeezerDatabase"

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: BaseApp): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideDeezerService(connectivityInterceptor: ConnectivityInterceptor): DeezerService {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(connectivityInterceptor)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DeezerService::class.java)
    }

    @Singleton
    @Provides
    fun provideDeezerDataSource(deezerDataSourceImpl: DeezerDataSourceImpl): DeezerDataSource {
        return deezerDataSourceImpl
    }

    @Singleton
    @Provides
    fun provideAPIRepository(apiRepositoryImpl: APIRepositoryImpl):APIRepostiroy{
        return apiRepositoryImpl
    }

    @Provides
    fun provideConnectivityInterceptor(interceptor: ConnectivityInterceptorImpl):ConnectivityInterceptor{
        return interceptor
    }

    @Provides
    @Singleton
    fun provideDeezerDatabase(context: Context):DeezerDatabase{
        return Room.databaseBuilder(context,
            DeezerDatabase::class.java,
            DB_NAME).build()
    }

    @Provides
    @Singleton
    fun provideDeezerDAO(db:DeezerDatabase):DeezerDAO{
        return db.getDeezerDAO()
    }
}