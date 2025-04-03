package com.sophossolutions.userpublications.showpublications.data.api.response

import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.userbyid.GetUserPublicationByIdDto
import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.userbyid.GetUserPublicationByIdItemDto
import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.users.GetUserItemDto
import com.sophossolutions.userpublications.showpublications.data.api.request.ApiUserPublicationsService
import com.sophossolutions.userpublications.showpublications.utils.ResponseApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ApiUserPublicationsServiceImpl @Inject constructor(
    private val api: ApiUserPublicationsService
) {
    suspend fun getAllUsersPublications(): ResponseApi<Flow<List<GetUserItemDto>>> {

        return withContext(Dispatchers.IO) {
            try {
                val result: Flow<List<GetUserItemDto>> = flow {
                    emit(api.getAllUsersPublications())
                }
                if (result.toList().isNotEmpty()) {
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

    suspend fun getUserPublicationById(idUser: Int): ResponseApi<Flow<List<GetUserPublicationByIdItemDto>>>{
        return withContext(Dispatchers.IO) {
            try {
                val result:Flow<List<GetUserPublicationByIdItemDto>> = flow {
                    emit(api.getUserPublicationById(idUser))
                }
                if (result.toList().isNotEmpty()) {
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