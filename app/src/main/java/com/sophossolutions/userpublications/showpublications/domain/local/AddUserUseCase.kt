package com.sophossolutions.userpublications.showpublications.domain.local

import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.getusers.GetUserItemDto
import com.sophossolutions.userpublications.showpublications.data.local.repository.UserRepository
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userItemDto: GetUserItemDto) = userRepository.addUser(userItemDto)
}