package com.muazekici.relaxingsounds.ui.main.fragment_library.categories_fragment.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muazekici.relaxingsounds.R
import com.muazekici.relaxingsounds.gateways_and_adapters.Category
import com.muazekici.relaxingsounds.gateways_and_adapters.CategoryType
import com.muazekici.relaxingsounds.ui.utils.inflateLayout

class CategoriesListAdapter(private val categorySelected: (Category) -> Unit) :
    ListAdapter<Category, CategoriesListAdapter.CategoryTypeItemViewHolder>(CategoryItemDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryTypeItemViewHolder {
        return CategoryTypeItemViewHolder(parent.inflateLayout(R.layout.item_category))
    }

    override fun onBindViewHolder(holder: CategoryTypeItemViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 10
    }

    inner class CategoryTypeItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        init {
            itemView.setOnClickListener {
                categorySelected(Category(1L, "test"))/*categorySelected(getItem(adapterPosition))*/
            }
        }
    }
}

object CategoryItemDiffUtil : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return newItem.id == oldItem.id
    }
}