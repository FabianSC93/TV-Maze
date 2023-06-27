package com.fabiansc.tvmaze.di

import android.content.Context
import com.fabiansc.tvmaze.data.network.ApiService
import com.fabiansc.tvmaze.data.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideTvShowRespository(apiService: ApiService, @ApplicationContext context: Context) = TvShowRepository(apiService, context)
}