package com.sophossolutions.userpublications.showpublications.data.local.database.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sophossolutions.userpublications.showpublications.data.local.database.entities.UserEntity
import com.sophossolutions.userpublications.showpublications.data.local.database.helpers.ConverterHelper

@Database(entities = [UserEntity::class], version = 1)
@TypeConverters(ConverterHelper::class)
abstract class UserDataBase: RoomDatabase() {
    abstract fun userDao(): UserDao
}