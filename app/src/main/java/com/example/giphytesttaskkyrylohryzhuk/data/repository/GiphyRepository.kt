package com.example.giphytesttaskkyrylohryzhuk.data.repository

import com.example.giphytesttaskkyrylohryzhuk.data.model.GiphyResponse

interface GiphyRepository{
    suspend fun loadGiphy(): GiphyResponse
}