package com.sophossolutions.userpublications.showpublications.ui.listusers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.getusers.GetUserItemDto
import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.getusers.toUserEntity
import com.sophossolutions.userpublications.showpublications.domain.api.GetAllUserPublicationsFromApiUseCase
import com.sophossolutions.userpublications.showpublications.domain.local.AddUserUseCase
import com.sophossolutions.userpublications.showpublications.domain.local.GetUsersUseCase
import com.sophossolutions.userpublications.showpublications.utils.ResponseApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelUser @Inject constructor(
    private val getAllUsersPublicationsFromApiUseCase: GetAllUserPublicationsFromApiUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    private val _dataUserFromApi = MutableLiveData<ResponseApi<List<GetUserItemDto>>>()

    private val _dataUserLocal = MutableLiveData<ResponseApi<List<GetUserItemDto>>>()
    val dataUserLocal: LiveData<ResponseApi<List<GetUserItemDto>>> = _dataUserLocal

    private val _nameUser = MutableLiveData("")
    val nameUser: LiveData<String> = _nameUser

    private val _isLoadingUsers = MutableLiveData<Boolean>()
    val isLoadingUsers: LiveData<Boolean> = _isLoadingUsers

    fun getPublications() {
        _isLoadingUsers.value = true
        viewModelScope.launch {
            if (getUsersLocal().isEmpty()) {
                _dataUserFromApi.value =
                    getAllUsersPublicationsFromApiUseCase.getAllUsersPublications()

                _dataUserFromApi.value?.result?.forEach {
                    addUserUseCase(it.toUserEntity())
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
            val filterUser = _dataUserLocal.value!!.result!!.filter {
                it.name.contains(userName, ignoreCase = true)
            }
            filterUser.forEach {
                mapUser[it.id] = it.name
            }
        }
        return mapUser
    }
}
