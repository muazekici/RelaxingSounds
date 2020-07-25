package com.muazekici.relaxingsounds.ui.main.di

import androidx.lifecycle.ViewModel
import com.muazekici.relaxingsounds.di.qualifiers.ViewModelKey
import com.muazekici.relaxingsounds.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

}