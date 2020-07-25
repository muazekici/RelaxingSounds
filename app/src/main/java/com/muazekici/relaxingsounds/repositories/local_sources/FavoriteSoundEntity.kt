package com.muazekici.relaxingsounds.repositories.local_sources

import androidx.room.*

@Entity(tableName = "favorites")
data class FavoriteSoundEntity(
    @PrimaryKey val id: Long,
    val category: String,
    val name: String,
    val sourceUrl: String
)

@Dao
interface FavoriteSoundDao {

    @Query("SELECT * FROM favorites")
    suspend fun getAll(): List<FavoriteSoundEntity>

    @Query("SELECT * FROM favorites WHERE id IN (:soundIds)")
    suspend fun getAllWithIds(soundIds: List<Long>): List<FavoriteSoundEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteSoundEntity: FavoriteSoundEntity)

    @Delete
    suspend fun delete(favoriteSoundEntity: FavoriteSoundEntity)
}

@Database(entities = [FavoriteSoundEntity::class], version = 4, exportSchema = false)
abstract class AppDB : RoomDatabase() {
    abstract fun favoriteSoundDAO(): FavoriteSoundDao
}