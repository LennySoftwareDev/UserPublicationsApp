package com.sophossolutions.userpublications.showpublications.data.api.modelresponse.getusers

data class GetUserItemDto(
    val id: Int,
    val name: String,
    val username: String?,
    val email: String?,
    val address: AddressDto,
    val phone: String?,
    val website: String?,
    val company: CompanyDto
)

fun GetUserItemDto.toUserEntity(): GetUserItemDto{
    return GetUserItemDto(
        id = id,
        name = name,
        username = username ?: "N.A",
        email = email ?: "N.A",
        address = AddressDto(
            street = address.street ?: "N.A",
            suite = address.suite ?: "N.A",
            city = address.city ?: "N.A",
            zipcode = address.zipcode ?: "N.A",
            geo = address.geo?.let {
                GeoDto(
                    lat = it.lat,
                    lng = it.lng
                )
            },
        ),
        phone = phone ?: "N.A",
        website = website ?: "N.A",
        company = CompanyDto(
            name = company.name ?: "N.A",
            catchPhrase = company.catchPhrase ?: "N.A",
            bs = company.bs ?: "N.A"
        )
    )
}
