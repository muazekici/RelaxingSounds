package com.muazekici.relaxingsounds.repositories.util

import com.muazekici.relaxingsounds.gateways_and_adapters.Category
import com.muazekici.relaxingsounds.gateways_and_adapters.CategoryType
import com.muazekici.relaxingsounds.gateways_and_adapters.SoundItem
import com.muazekici.relaxingsounds.repositories.local_sources.FavoriteSoundEntity
import com.muazekici.relaxingsounds.repositories.server_api.CategoryDTO
import com.muazekici.relaxingsounds.repositories.server_api.SoundItemDTO

interface Mapper<I, O> {
    fun map(input: I): O
}

interface ListMapper<I, O> : Mapper<List<I>, List<O>>

open class ListMapperImpl<I, O>(private val mapper: Mapper<I, O>) :
    ListMapper<I, O> {
    override fun map(input: List<I>): List<O> {
        return input.map { mapper.map(it) }
    }
}

object SoundItemDTO2SoundItem :
    Mapper<SoundItemDTO, SoundItem> {
    override fun map(input: SoundItemDTO): SoundItem {
        return SoundItem(input.id, input.category.toCategoryType(), input.name, input.sourceUrl)
    }
}

object SoundItemDTOList2SoundItemList :
    ListMapperImpl<SoundItemDTO, SoundItem>(SoundItemDTO2SoundItem)

object FavoriteSoundEntity2SoundItem : Mapper<FavoriteSoundEntity, SoundItem> {
    override fun map(input: FavoriteSoundEntity): SoundItem {
        return SoundItem(input.id, input.category.toCategoryType(), input.name, input.sourceUrl)
    }
}

object FavoriteSoundEntityList2SoundItemList :
    ListMapperImpl<FavoriteSoundEntity, SoundItem>(FavoriteSoundEntity2SoundItem)

object CategoryDTO2Category :
    Mapper<CategoryDTO, Category> {

    override fun map(input: CategoryDTO): Category {
        return Category(input.id, input.name)
    }
}

object CategoryDTOList2CategoryList : ListMapperImpl<CategoryDTO, Category>(
    CategoryDTO2Category
)

fun String.toCategoryType(): CategoryType {
    CategoryType.values().forEach {
        if (it.typeName == this) return it
    }
    error("Undefined Category")
}

fun CategoryType.toTypeName() = this.typeName