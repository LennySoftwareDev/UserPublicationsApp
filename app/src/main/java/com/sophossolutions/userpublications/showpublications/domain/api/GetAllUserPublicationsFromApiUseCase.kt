package com.sophossolutions.userpublications.showpublications.domain.api

import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.getusers.GetUserItemDto
import com.sophossolutions.userpublications.showpublications.data.api.repository.UserPublicationsApiRepository
import com.sophossolutions.userpublications.showpublications.utils.ResponseApi
import javax.inject.Inject

class GetAllUserPublicationsFromApiUseCase
@Inject constructor(private val apiRepository: UserPublicationsApiRepository){

    suspend fun getAllUsersPublications() : ResponseApi<List<GetUserItemDto>> {
        return apiRepository.getAllUsersPublications()
    }
}