package com.muazekici.relaxingsounds.usecases

import com.muazekici.relaxingsounds.gateways_and_adapters.SoundItem
import com.muazekici.relaxingsounds.gateways_and_adapters.repositories.FavoritesRepository
import com.muazekici.relaxingsounds.repositories.media.SoundPoolManager
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val soundPoolManager: SoundPoolManager
) : UseCase<SoundItem, Unit>() {

    override suspend fun execute(parameter: SoundItem) {
        soundPoolManager.stopSound(parameter.id)
        favoritesRepository.removeFavorite(parameter)
    }
}