package com.sophossolutions.userpublications.showpublications.data.api.modelresponse.userbyid

data class GetUserPublicationByIdItemDto(
    val body: String?,
    val id: Int,
    val title: String?,
    val userId: Int
)