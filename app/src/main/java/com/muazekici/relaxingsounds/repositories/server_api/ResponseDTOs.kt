package com.muazekici.relaxingsounds.repositories.server_api

data class SoundItemDTO(val id: Long, val category: String, val name: String, val sourceUrl: String)

data class CategoryDTO(val id: Long, val name: String)