package com.muazekici.relaxingsounds.ui.main

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.muazekici.relaxingsounds.R
import com.muazekici.relaxingsounds.RelaxingSoundsApplication
import com.muazekici.relaxingsounds.gateways_and_adapters.repositories.CategoriesRepository
import com.muazekici.relaxingsounds.gateways_and_adapters.repositories.FavoritesRepository
import com.muazekici.relaxingsounds.ui.main.fragment_favorites.FavoritesFragment
import com.muazekici.relaxingsounds.ui.main.fragment_library.LibraryFragmentContainer
import com.muazekici.relaxingsounds.ui.navigation.NavigationHost
import com.muazekici.relaxingsounds.ui.navigation.NavigationHostActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val tabTags = mutableListOf("FavoritesTab", "LibraryTab")
    private val fragmentCreators = mutableListOf<() -> Fragment>(
        { FavoritesFragment() },
        { LibraryFragmentContainer() }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.FragmentContainer, fragmentCreators[0](), tabTags[0])
                .commit()
        }

        BottomNavigation.setOnNavigationItemSelectedListener { item ->
            return@setOnNavigationItemSelectedListener when (item.itemId) {
                R.id.favorites -> {
                    changeTab(0)
                    true
                }
                R.id.library -> {
                    changeTab(1)
                    true
                }
                else -> false
            }
        }

    }

    private fun changeTab(newIdx: Int) {
        val currentFrag = supportFragmentManager.findFragmentById(R.id.FragmentContainer)

        supportFragmentManager.beginTransaction().also { trans ->
            currentFrag?.let { trans.detach(currentFrag) }

            val tag = tabTags[newIdx]
            val next = supportFragmentManager.findFragmentByTag(tag)

            next?.let { trans.attach(it) } ?: trans.add(
                R.id.FragmentContainer,
                fragmentCreators[newIdx](),
                tag
            )
        }.commit()
    }


    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(R.id.FragmentContainer) as? NavigationHost)?.let {
            if (!it.consumeBackPress()) {
                super.onBackPressed()
            }
        } ?: super.onBackPressed()
    }

}
