package com.muazekici.relaxingsounds.repositories.di

import dagger.Module

@Module(includes = [LocalDataSourceModule::class, RemoteDataSourceModule::class])
class DataSourceModule {

}