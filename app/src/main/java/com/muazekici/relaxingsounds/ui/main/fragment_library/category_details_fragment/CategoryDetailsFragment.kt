package com.muazekici.relaxingsounds.ui.main.fragment_library.category_details_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.muazekici.relaxingsounds.R
import com.muazekici.relaxingsounds.ui.main.fragment_library.category_details_fragment.adapters.CategorySoundListAdapter
import com.muazekici.relaxingsounds.ui.utils.withArgs
import com.muazekici.relaxingsounds.ui.widgets.MarginItemDecorator
import kotlinx.android.synthetic.main.fragment_category_details.*

class CategoryDetailsFragment : Fragment(R.layout.fragment_category_details) {

    private var categoryId: Long = -1L

    private val categorySoundListAdapter by lazy {
        CategorySoundListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryId = requireArguments().getLong(CATEGORY_ID_KEY)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ListCategorySounds.adapter = categorySoundListAdapter
        ListCategorySounds.addItemDecoration(MarginItemDecorator(resources.getDimensionPixelSize(R.dimen.dimen_full)))
    }

    companion object {

        fun newInstance(categoryId: Long): CategoryDetailsFragment {
            return CategoryDetailsFragment().withArgs {
                putLong(CATEGORY_ID_KEY, categoryId)
            }
        }

        private const val CATEGORY_ID_KEY = "CategoryIdKey"
    }
}