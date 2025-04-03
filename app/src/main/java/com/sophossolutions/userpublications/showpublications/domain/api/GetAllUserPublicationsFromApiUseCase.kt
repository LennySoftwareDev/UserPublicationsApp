package com.sophossolutions.userpublications.showpublications.domain.api

import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.users.GetUserItemDto
import com.sophossolutions.userpublications.showpublications.data.api.repository.UserPublicationsApiRepository
import com.sophossolutions.userpublications.showpublications.utils.ResponseApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllUserPublicationsFromApiUseCase
@Inject constructor(private val apiRepository: UserPublicationsApiRepository){

    suspend operator fun invoke() : ResponseApi<Flow<List<GetUserItemDto>>> {
        return apiRepository.getAllUsersPublications()
    }
}