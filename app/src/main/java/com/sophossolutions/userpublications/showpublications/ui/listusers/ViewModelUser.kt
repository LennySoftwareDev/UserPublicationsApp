package com.sophossolutions.userpublications.showpublications.ui.listusers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.users.GetUserItemDto
import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.users.toUserEntity
import com.sophossolutions.userpublications.showpublications.domain.api.GetAllUserPublicationsFromApiUseCase
import com.sophossolutions.userpublications.showpublications.domain.local.AddUserUseCase
import com.sophossolutions.userpublications.showpublications.domain.local.GetUsersUseCase
import com.sophossolutions.userpublications.showpublications.utils.ResponseApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelUser @Inject constructor(
    private val getAllUsersPublicationsFromApiUseCase: GetAllUserPublicationsFromApiUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    private val _dataUserFromApi =
        MutableStateFlow<ResponseApi<Flow<List<GetUserItemDto>>>>(
            ResponseApi.ApiResponseSuccess(
                dataApi = flowOf(
                    emptyList()
                )
            )
        )

    private val _dataUserLocal = MutableLiveData<ResponseApi<List<GetUserItemDto>>>()
    val dataUserLocal: LiveData<ResponseApi<List<GetUserItemDto>>> = _dataUserLocal

    private val _nameUser = MutableStateFlow("")
    val nameUser = _nameUser.asStateFlow()

    private val _isLoadingUsers = MutableStateFlow(false)
    val isLoadingUsers = _isLoadingUsers.asStateFlow()

    fun getPublications() {
        _isLoadingUsers.value = true
        viewModelScope.launch {
            if (getUsersLocal().isEmpty()) {
                // TODO: no está obteniendo la data del servicio
                _dataUserFromApi.value = getAllUsersPublicationsFromApiUseCase()

                _dataUserFromApi.value.result?.collect {
                    it.forEach {
                        addUserUseCase(it.toUserEntity())
                    }
                }

                _isLoadingUsers.value = false
            } else {
                _dataUserLocal.value = ResponseApi.ApiResponseSuccess(dataApi = getUsersLocal())
                _isLoadingUsers.value = false
            }
        }
    }

    private fun getUsersLocal(): List<GetUserItemDto> {
        viewModelScope.launch {
            _dataUserLocal.value = getUsersUseCase()
        }
        return _dataUserLocal.value?.result.orEmpty()
    }

    fun onTextChanged(userName: String) {
        _nameUser.value = userName
    }

    fun onSearchUser(userName: String): Map<Int, String> {

        val mapUser: MutableMap<Int, String> = mutableMapOf()

        _nameUser.value = userName

        if (_dataUserLocal.value != null) {
            _dataUserLocal.value!!.result!!.filter {
                it.name.contains(userName, ignoreCase = true)
            }.run {
                this.forEach {
                    mapUser[it.id] = it.name
                }
            }
        }
        return mapUser
    }

    fun onClearSearchBar(){
        _nameUser.value = String()
    }
}
