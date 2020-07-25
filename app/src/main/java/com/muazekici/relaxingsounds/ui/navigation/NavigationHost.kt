package com.muazekici.relaxingsounds.ui.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.muazekici.relaxingsounds.R
import com.muazekici.relaxingsounds.ui.navigation.NavigationHost.Companion.INITIAL_FRAG_TAG

interface NavigationHost {

    fun showFragment(
        fragment: Fragment,
        addToBackStack: Boolean,
        backStackTag: String?,
        transactionOp: (transaction: FragmentTransaction, fragmentManager: FragmentManager, containerId: Int, fragment: Fragment) -> Unit
    )

    val initialFragment: Fragment

    val fragmentContainer: Int


    fun consumeBackPress(): Boolean

    companion object {
        const val INITIAL_FRAG_TAG = "INITIAL_FRAG"
    }
}

abstract class NavigationHostFragment(val layoutId: Int = R.layout.container_layout) :
    Fragment(layoutId), NavigationHost {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState == null && childFragmentManager.findFragmentById(fragmentContainer) == null) {
            childFragmentManager.beginTransaction()
                .add(fragmentContainer, initialFragment, INITIAL_FRAG_TAG).commit()
        }
    }

    override val fragmentContainer: Int
        get() = R.id.FragmentContainer

    override fun showFragment(
        fragment: Fragment,
        addToBackStack: Boolean,
        backStackTag: String?,
        transactionOp: (transaction: FragmentTransaction, fragmentManager: FragmentManager, containerId: Int, fragment: Fragment) -> Unit
    ) {
        childFragmentManager.beginTransaction()
            .apply {
                transactionOp(this, childFragmentManager, fragmentContainer, fragment)
                if (addToBackStack) addToBackStack(backStackTag)
            }
            .commit()
    }


    override fun consumeBackPress(): Boolean {
        val childFrag = childFragmentManager.findFragmentById(fragmentContainer)
        if (childFrag is NavigationHost) {
            if (childFrag.consumeBackPress()) return true
        }
        return if (childFragmentManager.backStackEntryCount > 0) {
            childFragmentManager.popBackStackImmediate()
            true
        } else {
            false
        }
    }

}

abstract class NavigationHostActivity(layoutId: Int, override val fragmentContainer: Int) :
    AppCompatActivity(layoutId), NavigationHost {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(
                fragmentContainer, initialFragment,
                INITIAL_FRAG_TAG
            ).commit()
        }
    }

    override fun showFragment(
        fragment: Fragment,
        addToBackStack: Boolean,
        backStackTag: String?,
        transactionOp: (transaction: FragmentTransaction, fragmentManager: FragmentManager, containerId: Int, fragment: Fragment) -> Unit
    ) {
        supportFragmentManager.beginTransaction()
            .apply {
                transactionOp(this, supportFragmentManager, fragmentContainer, fragment)
                if (addToBackStack) addToBackStack(backStackTag)
            }
            .commit()
    }

    override fun consumeBackPress(): Boolean {
        val childFrag = supportFragmentManager.findFragmentById(fragmentContainer)
        if (childFrag is NavigationHost) {
            if (childFrag.consumeBackPress()) return true
        }
        return if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
            true
        } else {
            false
        }
    }


    override fun onBackPressed() {
        if (!consumeBackPress()) {
            super.onBackPressed()
        }
    }
}

fun Fragment.backPressed() {
    val parentAct = activity
    (parentAct as NavigationHost).consumeBackPress()
}

fun Fragment.showInParentHost(
    fragment: Fragment,
    isBackStack: Boolean = false,
    backStackTag: String? = null,
    transactionOp: (transaction: FragmentTransaction, fragmentManager: FragmentManager, containerId: Int, fragment: Fragment) -> Unit = simpleReplaceTransaction
) {
    val parentFrag = parentFragment
    val parentActivity = activity
    if (parentFrag is NavigationHost) {
        parentFrag.showFragment(
            fragment,
            isBackStack,
            backStackTag,
            transactionOp
        )
    } else if (parentActivity is NavigationHost) {
        parentActivity.showFragment(
            fragment,
            isBackStack,
            backStackTag,
            transactionOp
        )
    }
}

val simpleReplaceTransaction: (transaction: FragmentTransaction, fragmentManager: FragmentManager, containerId: Int, fragment: Fragment) -> Unit =
    { transaction, _, containerId, fragment ->
        transaction.replace(
            containerId,
            fragment
        )
    }