package com.muazekici.relaxingsounds.usecases

import com.muazekici.relaxingsounds.R
import com.muazekici.relaxingsounds.gateways_and_adapters.SoundItem
import com.muazekici.relaxingsounds.gateways_and_adapters.repositories.FavoritesRepository
import com.muazekici.relaxingsounds.repositories.media.SoundPoolManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val soundPoolManager: SoundPoolManager
) : FlowUseCase<Unit, List<SoundItem>>() {

    override fun execute(parameter: Unit): Flow<UseCaseResult<List<SoundItem>>> {
        return favoritesRepository.getFavoriteFlow()
            .onEach {
                it.forEach {
                    it.isFavorite = true
                    it.soundLevel = DEFAULT_VOLUME
                }
            }
            .flowOn(Dispatchers.Default)
            .onEach {
                it.forEach { item ->
                    soundPoolManager.checkSound(item.id)?.let {
                        item.soundLevel = it.second
                        item.isPlaying = true
                    }
                }
            }.map {
                UseCaseResult.Success(it)
            }
    }

    companion object {
        private const val DEFAULT_VOLUME = 0.5f
    }
}