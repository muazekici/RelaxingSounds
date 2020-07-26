package com.muazekici.relaxingsounds.repositories

import com.muazekici.relaxingsounds.di.scopes.AppScope
import com.muazekici.relaxingsounds.gateways_and_adapters.SoundItem
import com.muazekici.relaxingsounds.gateways_and_adapters.repositories.FavoritesRepository
import com.muazekici.relaxingsounds.repositories.local_sources.AppDB
import com.muazekici.relaxingsounds.repositories.local_sources.FavoriteSoundEntity
import com.muazekici.relaxingsounds.repositories.server_api.RetrofitMockAPI
import com.muazekici.relaxingsounds.repositories.util.FavoriteSoundEntityList2SoundItemList
import com.muazekici.relaxingsounds.repositories.util.SoundItemDTOList2SoundItemList
import com.muazekici.relaxingsounds.repositories.util.toTypeName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AppScope
class FavoritesRepositoryImpl @Inject constructor(
    private val appDB: AppDB,
    private val mockAPI: RetrofitMockAPI
) : FavoritesRepository {

    override suspend fun getFavorites(): List<SoundItem> {
        return coroutineScope {
            val responseAPI = async { mockAPI.getFavorites() }
            val responseLocal = async { appDB.favoriteSoundDAO().getAll() }

            return@coroutineScope withContext(Dispatchers.Default) {
                mutableListOf<SoundItem>().also {
                    it.addAll(SoundItemDTOList2SoundItemList.map(responseAPI.await()))
                    it.addAll(FavoriteSoundEntityList2SoundItemList.map(responseLocal.await()))
                }
            }
        }

    }

    override fun getFavoriteFlow() =
        appDB.favoriteSoundDAO().getAllFlow()
            .map {
                FavoriteSoundEntityList2SoundItemList.map(it)
            }
            .flowOn(Dispatchers.Default)
            .combine(flow { emit(mockAPI.getFavorites()) }) { a, b ->
                a + SoundItemDTOList2SoundItemList.map(b)
            }
            .flowOn(Dispatchers.Default)


    override suspend fun addFavorite(soundItem: SoundItem) {
        appDB.favoriteSoundDAO().insert(
            FavoriteSoundEntity(
                soundItem.id,
                soundItem.category.toTypeName(),
                soundItem.name,
                soundItem.sourceUrl
            )
        )
    }

    override suspend fun removeFavorite(soundItem: SoundItem) {
        appDB.favoriteSoundDAO().delete(
            FavoriteSoundEntity(
                soundItem.id,
                soundItem.category.toTypeName(),
                soundItem.name,
                soundItem.sourceUrl
            )
        )
    }
}