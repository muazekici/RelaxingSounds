package com.muazekici.relaxingsounds.ui.main

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.muazekici.relaxingsounds.R
import com.muazekici.relaxingsounds.RelaxingSoundsApplication
import com.muazekici.relaxingsounds.ui.main.di.MainActivityComponent
import com.muazekici.relaxingsounds.ui.main.fragment_favorites.FavoritesFragment
import com.muazekici.relaxingsounds.ui.main.fragment_library.LibraryFragmentContainer
import com.muazekici.relaxingsounds.ui.navigation.NavigationHost
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val tabTags = mutableListOf("FavoritesTab", "LibraryTab")
    private val fragmentCreators = mutableListOf<() -> Fragment>(
        { FavoritesFragment() },
        { LibraryFragmentContainer() }
    )

    var progressDialog: ProgressDialog? = null

    @Inject
    lateinit var activityComponent: MainActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent =
            (application as RelaxingSoundsApplication).appComponent.mainActivityComponent()
                .create(this).also {
                }
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

fun Context.MainActivityComponent(): MainActivityComponent {
    return (this as MainActivity).activityComponent
}

fun Activity.showProgress() {
    (this as MainActivity).let {
        if (it.progressDialog == null) it.progressDialog = ProgressDialog(this)
        it.progressDialog?.let { it.show() }
    }
}

fun Activity.dismissProgress() {
    (this as MainActivity).let {
        it.progressDialog?.let { it.dismiss() }
    }
}
