package com.muazekici.relaxingsounds.gateways_and_adapters.repositories

import com.muazekici.relaxingsounds.gateways_and_adapters.SoundItem

interface FavoritesRepository {

    suspend fun getFavorites(): List<SoundItem>
    suspend fun addFavorite(soundItem: SoundItem)
    suspend fun removeFavorite(soundItem: SoundItem)
}