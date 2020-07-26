package com.muazekici.relaxingsounds.ui.main.fragment_library

import androidx.fragment.app.Fragment
import com.muazekici.relaxingsounds.ui.main.fragment_library.categories_fragment.CategoriesFragment
import com.muazekici.relaxingsounds.ui.navigation.NavigationHostFragment

class LibraryFragmentContainer : NavigationHostFragment() {

    override val initialFragment: Fragment
        get() = CategoriesFragment()
}