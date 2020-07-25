package com.muazekici.relaxingsounds.ui.main.fragment_library.categories_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.muazekici.relaxingsounds.R
import com.muazekici.relaxingsounds.ui.main.fragment_library.categories_fragment.adapters.CategoriesListAdapter
import com.muazekici.relaxingsounds.ui.main.fragment_library.category_details_fragment.CategoryDetailsFragment
import com.muazekici.relaxingsounds.ui.navigation.showInParentHost
import com.muazekici.relaxingsounds.ui.widgets.MarginItemDecorator
import kotlinx.android.synthetic.main.fragment_categories.*

class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private val categoriesListAdapter by lazy {
        CategoriesListAdapter {
            showInParentHost(CategoryDetailsFragment.newInstance(it.id), true)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ListCategories.adapter = categoriesListAdapter
        ListCategories.addItemDecoration(MarginItemDecorator(resources.getDimensionPixelSize(R.dimen.dimen_full)))
    }
}