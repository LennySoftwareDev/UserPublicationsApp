package com.sophossolutions.userpublications.showpublications.data.local.database.helpers

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.getusers.GetUserItemDto
import javax.inject.Inject

@ProvidedTypeConverter
class ConverterHelper @Inject constructor(
    private val gson : Gson
){

    @TypeConverter
    fun toUserModelDto(getUserItemDto: GetUserItemDto):GetUserItemDto{
        val type = object : TypeToken<GetUserItemDto>() {}.type
        return gson.fromJson(gson.toJson(getUserItemDto), type)
    }
}