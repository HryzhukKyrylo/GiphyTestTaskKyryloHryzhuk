package com.example.giphytesttaskkyrylohryzhuk.data.repository

import com.example.giphytesttaskkyrylohryzhuk.data.model.GiphyModel

interface GiphyRepository {
    suspend fun loadGiphy(): GiphyModel
}