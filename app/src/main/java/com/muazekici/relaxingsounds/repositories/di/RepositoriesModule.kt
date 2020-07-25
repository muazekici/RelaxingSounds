package com.muazekici.relaxingsounds.repositories.di

import com.muazekici.relaxingsounds.gateways_and_adapters.repositories.CategoriesRepository
import com.muazekici.relaxingsounds.gateways_and_adapters.repositories.FavoritesRepository
import com.muazekici.relaxingsounds.repositories.CategoriesRepositoryImpl
import com.muazekici.relaxingsounds.repositories.FavoritesRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun provideCategoriesRepository(categoriesRepositoryImpl: CategoriesRepositoryImpl): CategoriesRepository

    @Binds
    abstract fun provideFavoritesRepository(favoritesRepositoryImpl: FavoritesRepositoryImpl): FavoritesRepository
}