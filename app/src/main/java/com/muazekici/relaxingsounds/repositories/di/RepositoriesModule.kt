package com.muazekici.relaxingsounds.repositories.di

import dagger.Module

@Module(includes = [LocalRepositoriesModule::class, RemoteRepositoriesModule::class])
class RepositoriesModule {

}