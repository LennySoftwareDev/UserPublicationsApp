package com.sophossolutions.userpublications.showpublications.di

import android.content.Context
import androidx.room.Room
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sophossolutions.userpublications.showpublications.data.local.database.dao.UserDao
import com.sophossolutions.userpublications.showpublications.data.local.database.dao.UserDataBase
import com.sophossolutions.userpublications.showpublications.data.local.database.helpers.ConverterHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    @TypeConverters(ConverterHelper::class)
    fun provideUserPublicationDataBase(@ApplicationContext context: Context): UserDataBase {
        return Room.databaseBuilder(context, UserDataBase::class.java, "user_publication_db")
            .build()
    }

    @Provides
    @Singleton
    fun provideUserPublicationDao(userDataBase: UserDataBase): UserDao {
        return userDataBase.userDao()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }
}