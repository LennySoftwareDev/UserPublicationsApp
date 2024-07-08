package com.sophossolutions.userpublications.showpublications.data.api.response

import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.getuserbyid.GetUserPublicationByIdDto
import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.getusers.GetUserItemDto
import com.sophossolutions.userpublications.showpublications.data.api.request.ApiUserPublicationsService
import com.sophossolutions.userpublications.showpublications.utils.ResponseApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ApiUserPublicationsServiceImpl @Inject constructor(
    private val api: ApiUserPublicationsService
) {
    suspend fun getAllUsersPublications(): ResponseApi<List<GetUserItemDto>> {
        return withContext(Dispatchers.IO) {
            try {
                val result = api.getAllUsersPublications()

                if (result.isNotEmpty()) {
                    ResponseApi.ApiResponseSuccess(dataApi = result)
                } else {
                    ResponseApi.ApiResponseError(message = "No hay publicaciones")
                }
            } catch (e: HttpException) {
                ResponseApi.ApiResponseError(message = e.message.toString())
            } catch (e: IOException) {
                ResponseApi.ApiResponseError(message = e.message.toString())
            } catch (e: Exception) {
                ResponseApi.ApiResponseError(message = e.message.toString())
            }
        }
    }

    suspend fun getUserPublicationById(idUser: Int): ResponseApi<GetUserPublicationByIdDto> {
        return withContext(Dispatchers.IO) {
            try {
                val result = api.getUserPublicationById(idUser)

                if (result.isNotEmpty()) {
                    ResponseApi.ApiResponseSuccess(dataApi = result)
                } else {
                    ResponseApi.ApiResponseError(message = "No hay publicaciones")
                }
            } catch (e: HttpException) {
                ResponseApi.ApiResponseError(message = e.message.toString())
            } catch (e: IOException) {
                ResponseApi.ApiResponseError(message = e.message.toString())
            } catch (e: Exception) {
                ResponseApi.ApiResponseError(message = e.message.toString())
            }
        }
    }
}