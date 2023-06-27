package com.fabiansc.tvmaze.di

import com.fabiansc.tvmaze.core.Constants.BASE_URL_TV_SHOWS
import com.fabiansc.tvmaze.data.network.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL_TV_SHOWS)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit) = retrofit.create(ApiClient::class.java)
}