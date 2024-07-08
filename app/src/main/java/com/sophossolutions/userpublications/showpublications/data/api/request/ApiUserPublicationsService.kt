package com.sophossolutions.userpublications.showpublications.data.api.request

import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.getuserbyid.GetUserPublicationByIdDto
import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.getusers.GetUserItemDto
import com.sophossolutions.userpublications.showpublications.utils.ConstantValues
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiUserPublicationsService {

    @GET(ConstantValues.GET_USERS)
    suspend fun getAllUsersPublications(): List<GetUserItemDto>

    @GET(ConstantValues.GET_USER_BY_ID)
    suspend fun getUserPublicationById(
        @Query("userId") idUser : Int
    ): GetUserPublicationByIdDto
}

