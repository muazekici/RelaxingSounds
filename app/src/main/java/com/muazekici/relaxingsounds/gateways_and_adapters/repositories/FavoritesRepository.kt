package com.muazekici.relaxingsounds.gateways_and_adapters.repositories

import com.muazekici.relaxingsounds.gateways_and_adapters.SoundItem
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun getFavorites(): List<SoundItem>
    suspend fun addFavorite(soundItem: SoundItem)
    suspend fun removeFavorite(soundItem: SoundItem)
    fun getFavoriteFlow(): Flow<List<SoundItem>>
}