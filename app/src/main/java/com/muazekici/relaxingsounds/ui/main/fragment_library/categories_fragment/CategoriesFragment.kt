package com.muazekici.relaxingsounds.ui.main.fragment_library.categories_fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.muazekici.relaxingsounds.R
import com.muazekici.relaxingsounds.ui.main.MainActivityComponent
import com.muazekici.relaxingsounds.ui.main.dismissProgress
import com.muazekici.relaxingsounds.ui.main.fragment_library.categories_fragment.adapters.CategoriesListAdapter
import com.muazekici.relaxingsounds.ui.main.fragment_library.category_details_fragment.CategoryDetailsFragment
import com.muazekici.relaxingsounds.ui.main.showProgress
import com.muazekici.relaxingsounds.ui.navigation.showInParentHost
import com.muazekici.relaxingsounds.ui.widgets.MarginItemDecorator
import com.muazekici.relaxingsounds.usecases.UseCaseResult
import com.muazekici.relaxingsounds.usecases.ifSuccess
import kotlinx.android.synthetic.main.fragment_categories.*
import javax.inject.Inject

class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private val categoriesListAdapter by lazy {
        CategoriesListAdapter {
            showInParentHost(CategoryDetailsFragment.newInstance(it.id), true)
        }
    }

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val categoryViewModel by viewModels<CategoryViewModel> { vmFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.MainActivityComponent().injectCategoryFragment(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ListCategories.adapter = categoriesListAdapter
        ListCategories.addItemDecoration(MarginItemDecorator(resources.getDimensionPixelSize(R.dimen.dimen_full)))

        categoryViewModel.categories.observe(viewLifecycleOwner) {
            if (it is UseCaseResult.Loading) requireActivity().showProgress() else requireActivity().dismissProgress()

            it.ifSuccess {
                categoriesListAdapter.submitList(it)
            }
        }
    }
}