package com.sophossolutions.userpublications.showpublications.data.local.repository

import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.getusers.GetUserItemDto
import com.sophossolutions.userpublications.showpublications.data.local.database.dao.UserDao
import com.sophossolutions.userpublications.showpublications.data.local.database.entities.UserEntity
import com.sophossolutions.userpublications.showpublications.utils.ResponseApi
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun addUser(getUserItemDto: GetUserItemDto) {

        val userExist = userDao.getUserById(getUserItemDto.id)
        if (userExist == null) {
            userDao.addUser(
                UserEntity(
                    idUser = 0,
                    id = getUserItemDto.id,
                    addressDto = getUserItemDto.address,
                    companyDto = getUserItemDto.company,
                    email = getUserItemDto.email ?: "N.A",
                    name = getUserItemDto.name,
                    phone = getUserItemDto.phone ?: "N.A",
                    username = getUserItemDto.username ?: "N.A",
                    website = getUserItemDto.website ?: "N.A"
                )
            )
        }
    }


    suspend fun getAllUsers(): ResponseApi<List<GetUserItemDto>> {
        return try {
            val users = userDao.getUsers().map { userEntity ->
                GetUserItemDto(
                    id = userEntity.id,
                    address = userEntity.addressDto,
                    company = userEntity.companyDto,
                    email = userEntity.email,
                    name = userEntity.name,
                    phone = userEntity.phone,
                    username = userEntity.username,
                    website = userEntity.website
                )
            }
            ResponseApi.ApiResponseSuccess(users)
        } catch (e: Exception) {
            ResponseApi.ApiResponseError(e.message ?: "Ocurrió un error al obtener los usuarios")
        }
    }
}