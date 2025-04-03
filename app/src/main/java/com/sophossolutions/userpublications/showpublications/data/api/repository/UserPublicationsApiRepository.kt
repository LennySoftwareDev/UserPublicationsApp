package com.sophossolutions.userpublications.showpublications.data.api.repository

import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.userbyid.GetUserPublicationByIdItemDto
import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.users.GetUserItemDto
import com.sophossolutions.userpublications.showpublications.data.api.response.ApiUserPublicationsServiceImpl
import com.sophossolutions.userpublications.showpublications.utils.ResponseApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserPublicationsApiRepository @Inject constructor(
    private val api: ApiUserPublicationsServiceImpl
) {
    suspend fun getAllUsersPublications() : ResponseApi<Flow<List<GetUserItemDto>>> {
        return api.getAllUsersPublications()
    }

    suspend fun getUserPublicationById(userId: Int) : ResponseApi<Flow<List<GetUserPublicationByIdItemDto>>> {
        return api.getUserPublicationById(userId)
    }
}