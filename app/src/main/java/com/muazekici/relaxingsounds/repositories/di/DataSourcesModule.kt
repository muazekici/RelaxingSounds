package com.muazekici.relaxingsounds.repositories.di

import android.content.Context
import androidx.room.Room
import com.muazekici.relaxingsounds.di.qualifiers.ApplicationContext
import com.muazekici.relaxingsounds.di.scopes.AppScope
import com.muazekici.relaxingsounds.gateways_and_adapters.repositories.FavoritesRepository
import com.muazekici.relaxingsounds.repositories.local_sources.AppDB
import dagger.Module
import dagger.Provides


@Module(includes = [RetrofitModule::class])
class DataSourcesModule {

    @Provides
    @AppScope
    fun provideDbInstance(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDB::class.java, DB_NAME).fallbackToDestructiveMigration().build()

}

const val DB_NAME = "RelaxingSoundsDb"