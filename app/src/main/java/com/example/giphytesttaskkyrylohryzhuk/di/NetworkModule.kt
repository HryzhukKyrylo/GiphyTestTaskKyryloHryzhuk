package com.example.giphytesttaskkyrylohryzhuk.di

import androidx.viewbinding.BuildConfig
import com.example.giphytesttaskkyrylohryzhuk.data.api.ApiService
import com.example.giphytesttaskkyrylohryzhuk.data.repository.GiphyRepository
import com.example.giphytesttaskkyrylohryzhuk.data.repository.GiphyRepositoryImpl
import com.example.giphytesttaskkyrylohryzhuk.utils.Constasts.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

//    @Singleton
//    @Provides
//    fun provideOkHttpClient(): OkHttpClient =
//        OkHttpClient.Builder().build()
    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(
        ApiService::class.java
    )

    @Provides
    @Singleton
    fun provideGiphyRepository(apiService: ApiService): GiphyRepository = GiphyRepositoryImpl(apiService)
}