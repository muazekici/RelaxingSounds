package com.muazekici.relaxingsounds.repositories

import com.muazekici.relaxingsounds.di.scopes.AppScope
import com.muazekici.relaxingsounds.gateways_and_adapters.Category
import com.muazekici.relaxingsounds.gateways_and_adapters.SoundItem
import com.muazekici.relaxingsounds.gateways_and_adapters.repositories.CategoriesRepository
import com.muazekici.relaxingsounds.repositories.server_api.RetrofitMockAPI
import com.muazekici.relaxingsounds.repositories.util.CategoryDTO2Category
import com.muazekici.relaxingsounds.repositories.util.CategoryDTOList2CategoryList
import com.muazekici.relaxingsounds.repositories.util.SoundItemDTOList2SoundItemList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AppScope
class CategoriesRepositoryImpl @Inject constructor(private val retrofitMockAPI: RetrofitMockAPI) :
    CategoriesRepository {

    override suspend fun getCategories(): List<Category> {
        val response = retrofitMockAPI.getCategories()
        return withContext(Dispatchers.Default) {
            CategoryDTOList2CategoryList.map(response)
        }
    }

    override suspend fun getSoundsForCategory(categoryId: Long): List<SoundItem> {
        val response = retrofitMockAPI.getCategorySounds(categoryId)
        return withContext(Dispatchers.Default) {
            SoundItemDTOList2SoundItemList.map(response)
        }
    }
}