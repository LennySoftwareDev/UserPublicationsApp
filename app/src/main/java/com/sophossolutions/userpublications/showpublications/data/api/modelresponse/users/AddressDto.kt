package com.sophossolutions.userpublications.showpublications.data.api.modelresponse.users

import androidx.room.Embedded

data class AddressDto(
    val city: String?,
    val street: String?,
    val suite: String?,
    val zipcode: String?,
    @Embedded
    val geo: GeoDto?
)