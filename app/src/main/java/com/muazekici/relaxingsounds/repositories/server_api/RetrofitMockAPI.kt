package com.muazekici.relaxingsounds.repositories.server_api

import retrofit2.http.GET
import retrofit2.http.Path

public interface RetrofitMockAPI {

    @GET("categories")
    suspend fun getCategories(): List<CategoryDTO>

    @GET("favorites")
    suspend fun getFavorites(): List<SoundItemDTO>

    @GET("category/{categoryId}")
    suspend fun getCategorySounds(@Path("categoryId") categoryId: Long): List<SoundItemDTO>
}