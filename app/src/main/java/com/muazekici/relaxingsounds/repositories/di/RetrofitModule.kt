package com.muazekici.relaxingsounds.repositories.di

import com.muazekici.relaxingsounds.di.scopes.AppScope
import com.muazekici.relaxingsounds.repositories.server_api.MockAPI
import com.muazekici.relaxingsounds.repositories.server_api.RetrofitMockAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Named

@Module
class RetrofitModule {

    @Provides
    @Named(BASE_URL_TAG)
    fun provideBaseUrl() = "https://www.muazekici.com/"

    @Provides
    @AppScope
    fun provideHttpLoggingInterceptor() =
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.d(message)
            }
        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @AppScope
    fun provideHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        mockAPI: MockAPI
    ) =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(mockAPI)
            .build()

    @Provides
    @AppScope
    fun provideGsonConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    @AppScope
    fun provideRetrofitInstance(
        @Named(BASE_URL_TAG) baseUrl: String,
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()

    @Provides
    @AppScope
    fun provideMockAPI(retrofit: Retrofit) =
        retrofit.create(RetrofitMockAPI::class.java)
}

const val BASE_URL_TAG = "BASE_URL"
