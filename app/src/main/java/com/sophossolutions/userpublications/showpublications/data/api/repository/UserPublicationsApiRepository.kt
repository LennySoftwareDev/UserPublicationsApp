package com.sophossolutions.userpublications.showpublications.data.api.repository

import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.getuserbyid.GetUserPublicationByIdDto
import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.getusers.GetUserItemDto
import com.sophossolutions.userpublications.showpublications.data.api.response.ApiUserPublicationsServiceImpl
import com.sophossolutions.userpublications.showpublications.utils.ResponseApi
import javax.inject.Inject

class UserPublicationsApiRepository @Inject constructor(
    private val api: ApiUserPublicationsServiceImpl
) {
    suspend fun getAllUsersPublications() : ResponseApi<List<GetUserItemDto>> {
        return api.getAllUsersPublications()
    }

    suspend fun getUserPublicationById(userId: Int) : ResponseApi<GetUserPublicationByIdDto> {
        return api.getUserPublicationById(userId)
    }
}