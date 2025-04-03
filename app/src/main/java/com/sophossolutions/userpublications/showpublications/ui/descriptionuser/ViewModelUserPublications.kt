package com.sophossolutions.userpublications.showpublications.ui.descriptionuser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.userbyid.GetUserPublicationByIdItemDto
import com.sophossolutions.userpublications.showpublications.domain.api.GetUserPublicationByIdFromApiUseCase
import com.sophossolutions.userpublications.showpublications.utils.ResponseApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelUserPublications @Inject constructor(
    private val getUserPublicationByIdFromApiUseCase: GetUserPublicationByIdFromApiUseCase
) : ViewModel() {

    private val _dataUserById =
        MutableStateFlow<ResponseApi<Flow<List<GetUserPublicationByIdItemDto>>>>(
            ResponseApi.ApiResponseSuccess(
                dataApi = flowOf(
                    emptyList()
                )
            )
        )

    private val _mapUser = MutableStateFlow<Map<String, String>>(emptyMap())
    val mapUser: StateFlow<Map<String, String>> = _mapUser

    private val _idUser = MutableStateFlow(0)
    val idUser = _idUser.asStateFlow()

    private val _isLoadingUserInformation = MutableStateFlow(false)
    val isLoadingUserInformation = _isLoadingUserInformation.asStateFlow()

    fun getUserById(id: Int) {
        _idUser.value = id
        _isLoadingUserInformation.value = true
        viewModelScope.launch {
            _dataUserById.value =
                getUserPublicationByIdFromApiUseCase.invoke(_idUser.value)
            delay(2000)
            _isLoadingUserInformation.value = false
        }
    }

    fun onShowUserPublications() {

        val mapUserData: MutableMap<String, String> = mutableMapOf()
        viewModelScope.launch {
            _dataUserById.value.result?.collect {
                it.onEach {
                    mapUserData[it.title ?: "N.A"] = it.body ?: "N.A"
                }
            }
            _mapUser.value = mapUserData
        }
    }
}
