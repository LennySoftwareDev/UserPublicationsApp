package com.sophossolutions.userpublications.showpublications.data.local.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.getusers.AddressDto
import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.getusers.CompanyDto

@Entity
@TypeConverters
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val idUser: Int,
    val id: Int,
    @Embedded
    val addressDto: AddressDto,
    @Embedded(prefix = "name_company")
    val companyDto: CompanyDto,
    val email: String,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)