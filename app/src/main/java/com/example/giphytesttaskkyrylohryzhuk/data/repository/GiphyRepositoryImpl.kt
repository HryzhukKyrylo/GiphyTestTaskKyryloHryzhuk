package com.example.giphytesttaskkyrylohryzhuk.data.repository

import com.example.giphytesttaskkyrylohryzhuk.data.api.ApiService
import com.example.giphytesttaskkyrylohryzhuk.data.model.GiphyModel
import javax.inject.Inject

class GiphyRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): GiphyRepository{
    override suspend fun loadGiphy(): GiphyModel = apiService.loadGiphy()

}