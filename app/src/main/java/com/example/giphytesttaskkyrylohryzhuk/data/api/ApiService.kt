package com.example.giphytesttaskkyrylohryzhuk.data.api

import com.example.giphytesttaskkyrylohryzhuk.data.model.GiphyModel
import com.example.giphytesttaskkyrylohryzhuk.utils.Constasts.API_KEY
import com.example.giphytesttaskkyrylohryzhuk.utils.Constasts.END_POINT
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(END_POINT)
    suspend fun loadGiphy(
        @Query("api_key") api_key: String = API_KEY,
        @Query("q") q: String = QUERY_G,
        @Query("limit") limit: Int = QUERY_LIMIT,
        @Query("offset") offset: Int = QUERY_OFFSET,
        @Query("rating") rating: String = QUERY_RATING,
        @Query("lang") lang: String = QUERY_LANG
    ): GiphyModel

    companion object {
        const val QUERY_LIMIT = 25
        const val QUERY_OFFSET = 0
        const val QUERY_RATING = "g"
        const val QUERY_LANG = "en"
        const val QUERY_G = "popular"
    }
}