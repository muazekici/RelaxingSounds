package com.muazekici.relaxingsounds.gateways_and_adapters.repositories

import com.muazekici.relaxingsounds.gateways_and_adapters.Category
import com.muazekici.relaxingsounds.gateways_and_adapters.SoundItem

interface CategoriesRepository {

    suspend fun getCategories(): List<Category>
    suspend fun getSoundsForCategory(categoryId: Long): List<SoundItem>

}