package com.sophossolutions.userpublications.showpublications.ui.descriptionuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sophossolutions.userpublications.showpublications.data.api.modelresponse.getuserbyid.GetUserPublicationByIdDto
import com.sophossolutions.userpublications.showpublications.domain.api.GetUserPublicationByIdFromApiUseCase
import com.sophossolutions.userpublications.showpublications.utils.ResponseApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelUserPublications @Inject constructor(
    private val getUserPublicationByIdFromApiUseCase: GetUserPublicationByIdFromApiUseCase
) : ViewModel() {

    private val _dataUserById = MutableLiveData<ResponseApi<GetUserPublicationByIdDto>>()

    private val _idUser = MutableLiveData<Int>()
    val idUser: LiveData<Int> = _idUser

    private val _isLoadingUserInformation = MutableLiveData<Boolean>()
    val isLoadingUserInformation: LiveData<Boolean> = _isLoadingUserInformation

    fun getUserById(id: Int) {
        _idUser.value = id
        _isLoadingUserInformation.value = true
        viewModelScope.launch {
            _dataUserById.value = getUserPublicationByIdFromApiUseCase(id)
            delay(2000)
            _isLoadingUserInformation.value = false
        }
    }

    fun onShowUserPublications(): Map<String, String> {
        val mapUser: MutableMap<String, String> = mutableMapOf()

        if (_dataUserById.value != null) {
            _dataUserById.value!!.let {
                it.result?.forEach {
                    mapUser[it.title ?: "N.A"] = it.body ?: "N.A"
                }
            }
        }
        return mapUser
    }
}