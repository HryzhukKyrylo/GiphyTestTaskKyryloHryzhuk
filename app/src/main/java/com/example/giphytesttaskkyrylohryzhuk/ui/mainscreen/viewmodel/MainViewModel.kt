package com.example.giphytesttaskkyrylohryzhuk.ui.mainscreen.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.giphytesttaskkyrylohryzhuk.data.model.GiphyModel
import com.example.giphytesttaskkyrylohryzhuk.data.repository.GiphyRepository
import com.example.giphytesttaskkyrylohryzhuk.utils.CheckNetwork
import com.example.giphytesttaskkyrylohryzhuk.utils.Resource
import com.example.giphytesttaskkyrylohryzhuk.utils.StringUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    app: Application,
    private val repository: GiphyRepository,
    private val stringUtils: StringUtils
) : AndroidViewModel(app) {

    private val _responseGiphy = MutableLiveData<Resource<GiphyModel>>()
    val responseGiphy = _responseGiphy

    fun getGiphy() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            _responseGiphy.postValue(Resource.loading(data = null))
            try {
                if (CheckNetwork.hasInternetConnection(getApplication())) {
                    _responseGiphy.postValue(Resource.success(data = repository.loadGiphy()))
                } else {
                    _responseGiphy.postValue(
                        Resource.error(
                            data = null,
                            message = stringUtils.noInternetConnection()
                        )
                    )
                }
            } catch (exception: Exception) {
                _responseGiphy.postValue(
                    Resource.error(
                        data = null,
                        message = exception.message ?: stringUtils.somethingWentWrong()
                    )
                )
            }
        }
    }
}