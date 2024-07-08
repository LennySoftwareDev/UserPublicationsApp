package com.sophossolutions.userpublications.showpublications.data.api.modelresponse.getuserbyid

data class GetUserPublicationByIdItemDto(
    val body: String?,
    val id: Int,
    val title: String?,
    val userId: Int
)