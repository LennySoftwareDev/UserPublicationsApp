package com.sophossolutions.userpublications.showpublications.domain.api

import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.userbyid.GetUserPublicationByIdDto
import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.userbyid.GetUserPublicationByIdItemDto
import com.sophossolutions.userpublications.showpublications.data.api.repository.UserPublicationsApiRepository
import com.sophossolutions.userpublications.showpublications.utils.ResponseApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserPublicationByIdFromApiUseCase @Inject constructor(
    private val apiRepository: UserPublicationsApiRepository
) {
    suspend operator fun invoke(idUser: Int): ResponseApi<Flow<List<GetUserPublicationByIdItemDto>>> {
        return apiRepository.getUserPublicationById(idUser)
    }
}