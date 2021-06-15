package com.example.giphytesttaskkyrylohryzhuk.ui.mainscreen.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.giphytesttaskkyrylohryzhuk.data.model.GiphyModel
import com.example.giphytesttaskkyrylohryzhuk.data.repository.GiphyRepository
import com.example.giphytesttaskkyrylohryzhuk.utils.CheckNetwork
import com.example.giphytesttaskkyrylohryzhuk.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GiphyRepository
) :ViewModel(){

    private val _responseGiphy = MutableLiveData<Resource<GiphyModel>>()
    val responseGiphy = _responseGiphy

    fun getGiphy() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            _responseGiphy.postValue(Resource.loading(data = null))
            try {
                    _responseGiphy.postValue(Resource.success(data = repository.loadGiphy()))
            } catch (exception: Exception) {
                _responseGiphy.postValue(
                    Resource.error(
                        data = null,
                        message = exception.message ?: "Error Occurred!"
                    )
                )
            }
        }
    }
}