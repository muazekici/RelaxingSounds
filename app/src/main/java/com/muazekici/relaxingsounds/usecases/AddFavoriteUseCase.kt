package com.muazekici.relaxingsounds.usecases

import com.muazekici.relaxingsounds.gateways_and_adapters.SoundItem
import com.muazekici.relaxingsounds.gateways_and_adapters.repositories.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) :
    UseCase<Pair<SoundItem, List<SoundItem>>, List<SoundItem>>() {

    override suspend fun execute(parameter: Pair<SoundItem, List<SoundItem>>): List<SoundItem> {
        delay(1000)//for fake loading progress purposes
        try {
            favoritesRepository.addFavorite(parameter.first)
        } catch (e: Exception) {
            return parameter.second
        }
        return withContext(Dispatchers.Default) {
            parameter.second.filter { it.id != parameter.first.id }.toList()
        }
    }
}