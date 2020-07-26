package com.muazekici.relaxingsounds.ui.main.di

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.muazekici.relaxingsounds.di.qualifiers.ActivityContext
import com.muazekici.relaxingsounds.di.scopes.ActivityScope
import com.muazekici.relaxingsounds.ui.main.MainActivity
import com.muazekici.relaxingsounds.ui.main.fragment_favorites.FavoritesFragment
import com.muazekici.relaxingsounds.ui.main.fragment_library.categories_fragment.CategoriesFragment
import com.muazekici.relaxingsounds.ui.main.fragment_library.category_details_fragment.CategoryDetailsFragment
import com.muazekici.relaxingsounds.ui.utils.RelaxingSoundsViewModelFactory
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Provider

@ActivityScope
@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: Activity): MainActivityComponent
    }

    fun injectActivity(mainActivity: MainActivity)

    fun injectCategoryFragment(categoryFragment: CategoriesFragment)
    fun injectCategoryDetailsFragment(categoryDetailsFragment: CategoryDetailsFragment)
    fun injectFavoritesFragment(favoritesFragment: FavoritesFragment)
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