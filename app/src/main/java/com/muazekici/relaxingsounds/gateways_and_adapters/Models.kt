package com.muazekici.relaxingsounds.gateways_and_adapters

data class SoundItem(
    val id: Long,
    val category: CategoryType,
    val name: String,
    val sourceUrl: String,
    var isPlaying: Boolean = false,
    var soundLevel: Float = 0.5f,
    var isFavorite: Boolean = false
)

data class Category(
    val id: Long,
    val name: String
)

enum class CategoryType(val typeName:String) {
    BIRD("Bird"), PIANO("Piano"), NATURE("Nature")
}