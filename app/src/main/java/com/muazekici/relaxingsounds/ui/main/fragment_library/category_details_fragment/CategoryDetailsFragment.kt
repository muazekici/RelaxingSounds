package com.muazekici.relaxingsounds.ui.main.fragment_library.category_details_fragment

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.muazekici.relaxingsounds.R
import com.muazekici.relaxingsounds.ui.main.MainActivityComponent
import com.muazekici.relaxingsounds.ui.main.dismissProgress
import com.muazekici.relaxingsounds.ui.main.fragment_library.categories_fragment.CategoryViewModel
import com.muazekici.relaxingsounds.ui.main.fragment_library.category_details_fragment.adapters.CategorySoundListAdapter
import com.muazekici.relaxingsounds.ui.main.showProgress
import com.muazekici.relaxingsounds.ui.utils.withArgs
import com.muazekici.relaxingsounds.ui.widgets.MarginItemDecorator
import com.muazekici.relaxingsounds.usecases.UseCaseResult
import com.muazekici.relaxingsounds.usecases.ifSuccess
import kotlinx.android.synthetic.main.fragment_category_details.*
import javax.inject.Inject

class CategoryDetailsFragment : Fragment(R.layout.fragment_category_details) {

    private var categoryId: Long = -1L
    private lateinit var categoryName: String

    private val categorySoundListAdapter by lazy {
        CategorySoundListAdapter {
            categoryDetailViewModel.addFavorite(it)
        }
    }

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val categoryDetailViewModel by viewModels<CategoryDetailViewModel> { vmFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.MainActivityComponent().injectCategoryDetailsFragment(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryId = requireArguments().getLong(CATEGORY_ID_KEY)
        categoryName = requireArguments().getString(CATEGORY_NAME_KEY)!!
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ListCategorySounds.adapter = categorySoundListAdapter
        ListCategorySounds.addItemDecoration(MarginItemDecorator(resources.getDimensionPixelSize(R.dimen.dimen_full)))

        categoryDetailViewModel.getCategorySounds(categoryId)

        categoryDetailViewModel.categorySounds.observe(viewLifecycleOwner) {
            if (it is UseCaseResult.Loading) requireActivity().showProgress() else requireActivity().dismissProgress()

            it.ifSuccess {
                categorySoundListAdapter.submitList(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.let {
            it.title = categoryName
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    companion object {

        fun newInstance(categoryId: Long, categoryName: String): CategoryDetailsFragment {
            return CategoryDetailsFragment().withArgs {
                putLong(CATEGORY_ID_KEY, categoryId)
                putString(CATEGORY_NAME_KEY, categoryName)
            }
        }

        private const val CATEGORY_ID_KEY = "CategoryIdKey"
        private const val CATEGORY_NAME_KEY = "CategoryNameKey"
    }
}