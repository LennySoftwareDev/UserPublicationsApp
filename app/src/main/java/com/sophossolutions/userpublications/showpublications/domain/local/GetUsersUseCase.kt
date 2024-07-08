package com.sophossolutions.userpublications.showpublications.domain.local

import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.getusers.GetUserItemDto
import com.sophossolutions.userpublications.showpublications.data.local.repository.UserRepository
import com.sophossolutions.userpublications.showpublications.utils.ResponseApi
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
     suspend operator fun invoke() : ResponseApi<List<GetUserItemDto>> = userRepository.getAllUsers()
}