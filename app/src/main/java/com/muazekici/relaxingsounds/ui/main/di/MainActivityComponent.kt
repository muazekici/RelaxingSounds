package com.muazekici.relaxingsounds.ui.main.di

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.muazekici.relaxingsounds.di.qualifiers.ActivityContext
import com.muazekici.relaxingsounds.di.scopes.ActivityScope
import com.muazekici.relaxingsounds.ui.utils.RelaxingSoundsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Provider

@ActivityScope
@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent {

}

@Module(subcomponents = [MainActivityComponent::class])
class MainActivitySubComponent

@Module(includes = [MainActivityViewModelModule::class])
class MainActivityModule {

    @Provides
    @ActivityContext
    @ActivityScope
    fun bindActivityContext(activity: Activity): Context {
        return activity
    }

    @ActivityScope
    @Provides
    fun provideViewModelFactory(map: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory {
        return RelaxingSoundsViewModelFactory(map)
    }

}