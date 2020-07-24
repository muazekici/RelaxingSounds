package com.muazekici.relaxingsounds.di

import android.app.Application
import com.muazekici.relaxingsounds.di.scopes.AppScope
import com.muazekici.relaxingsounds.repositories.di.DataSourceModule
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [DataSourceModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}