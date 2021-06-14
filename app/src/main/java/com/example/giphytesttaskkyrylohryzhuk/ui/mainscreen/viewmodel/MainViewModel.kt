package com.example.giphytesttaskkyrylohryzhuk.ui.mainscreen.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.giphytesttaskkyrylohryzhuk.data.repository.GiphyRepository
import com.example.giphytesttaskkyrylohryzhuk.ui.base.BaseApplication
import com.example.giphytesttaskkyrylohryzhuk.utils.CheckNetwork
import com.example.giphytesttaskkyrylohryzhuk.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GiphyRepository
) :ViewModel(){

    fun getGiphy(context: Context) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            if (CheckNetwork.hasInternetConnection(context)) {
                emit(Resource.success(data = repository.loadGiphy()))
            } else {
                emit(Resource.error(data = null, message = "No Internet connection!"))
            }
        }catch (exception: Exception){
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}