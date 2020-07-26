package com.muazekici.relaxingsounds.di

import android.app.Application
import android.content.Context
import com.muazekici.relaxingsounds.di.qualifiers.ApplicationContext
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    @ApplicationContext
    abstract fun bindApplicationContext(application: Application): Context
}