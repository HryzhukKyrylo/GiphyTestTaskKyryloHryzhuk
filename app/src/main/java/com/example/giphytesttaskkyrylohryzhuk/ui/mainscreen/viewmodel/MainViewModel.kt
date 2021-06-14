package com.example.giphytesttaskkyrylohryzhuk.ui.mainscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.giphytesttaskkyrylohryzhuk.data.repository.GiphyRepository
import com.example.giphytesttaskkyrylohryzhuk.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GiphyRepository
) :ViewModel(){

    fun getGiphy() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.loadGiphy()))
        }catch (exception: Exception){
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}