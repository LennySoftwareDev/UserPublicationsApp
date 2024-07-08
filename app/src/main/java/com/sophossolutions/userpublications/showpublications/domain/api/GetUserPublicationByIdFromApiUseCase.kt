package com.sophossolutions.userpublications.showpublications.domain.api

import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.getuserbyid.GetUserPublicationByIdDto
import com.sophossolutions.userpublications.showpublications.data.api.repository.UserPublicationsApiRepository
import com.sophossolutions.userpublications.showpublications.utils.ResponseApi
import javax.inject.Inject

class GetUserPublicationByIdFromApiUseCase @Inject constructor(
    private val apiRepository: UserPublicationsApiRepository
) {
    suspend operator fun invoke(idUser: Int): ResponseApi<GetUserPublicationByIdDto> {
        return apiRepository.getUserPublicationById(idUser)
    }
}