package com.sophossolutions.userpublications.showpublications.di

import com.sophossolutions.userpublications.showpublications.data.api.request.ApiUserPublicationsService
import com.sophossolutions.userpublications.showpublications.utils.ConstantValues
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ConstantValues.BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiUserPublicationsService {
        return retrofit.create(ApiUserPublicationsService::class.java)
    }
}