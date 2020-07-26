package com.muazekici.relaxingsounds.usecases

import com.muazekici.relaxingsounds.gateways_and_adapters.SoundItem
import com.muazekici.relaxingsounds.gateways_and_adapters.repositories.CategoriesRepository
import com.muazekici.relaxingsounds.gateways_and_adapters.repositories.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

/*
This use case assumes that server app doesn't check items according to user favorites
 */

class GetCategorySoundUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
    private val favoritesRepository: FavoritesRepository
) : UseCase<Long, List<SoundItem>>() {

    override suspend fun execute(parameter: Long): List<SoundItem> {
        delay(1000)//for fake loading progress purposes
        val categoryItems = categoriesRepository.getSoundsForCategory(parameter)
        val userFavorites = favoritesRepository.getFavorites()

        Timber.d("UserFavorites : $userFavorites")
        return withContext(Dispatchers.Default) {
            val favoriteIds = userFavorites.map { it.id }.toSet()
            categoryItems.filterNot { it.id in favoriteIds }
        }

    }
}