package com.muazekici.relaxingsounds.di

import android.app.Application
import com.muazekici.relaxingsounds.RelaxingSoundsApplication
import com.muazekici.relaxingsounds.di.scopes.AppScope
import com.muazekici.relaxingsounds.repositories.di.DataSourcesModule
import com.muazekici.relaxingsounds.repositories.di.RepositoriesModule
import com.muazekici.relaxingsounds.ui.main.MainActivity
import com.muazekici.relaxingsounds.ui.main.di.MainActivityComponent
import com.muazekici.relaxingsounds.ui.main.di.MainActivitySubComponent
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [
        AppModule::class,
        RepositoriesModule::class,
        DataSourcesModule::class,
        MainActivitySubComponent::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun injectApplication(relaxingSoundsApplication: RelaxingSoundsApplication)

    fun mainActivityComponent(): MainActivityComponent.Factory
}