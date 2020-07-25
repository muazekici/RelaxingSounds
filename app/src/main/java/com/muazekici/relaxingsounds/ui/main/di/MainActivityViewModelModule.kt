package com.muazekici.relaxingsounds.ui.main.di

import androidx.lifecycle.ViewModel
import com.muazekici.relaxingsounds.di.qualifiers.ViewModelKey
import com.muazekici.relaxingsounds.ui.main.MainViewModel
import com.muazekici.relaxingsounds.ui.main.fragment_favorites.FavoritesViewModel
import com.muazekici.relaxingsounds.ui.main.fragment_library.categories_fragment.CategoryViewModel
import com.muazekici.relaxingsounds.ui.main.fragment_library.category_details_fragment.CategoryDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    abstract fun bindCategoryViewModel(viewModel: CategoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryDetailViewModel::class)
    abstract fun bindCategoryDetailViewModel(viewModel: CategoryDetailViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindFavoritesViewModel(viewModel: FavoritesViewModel): ViewModel

}