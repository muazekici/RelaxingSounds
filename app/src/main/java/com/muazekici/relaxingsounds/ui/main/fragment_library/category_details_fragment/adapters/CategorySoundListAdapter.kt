package com.muazekici.relaxingsounds.ui.main.fragment_library.category_details_fragment.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muazekici.relaxingsounds.R
import com.muazekici.relaxingsounds.gateways_and_adapters.SoundItem
import com.muazekici.relaxingsounds.ui.utils.inflateLayout
import kotlinx.android.synthetic.main.item_category_detail.view.*

class CategorySoundListAdapter(private val soundeSelected: (SoundItem) -> Unit) :
    ListAdapter<SoundItem, CategorySoundListAdapter.CategorySoundItemViewHolder>(
        CategorySoundItemDiffUtil
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorySoundItemViewHolder {
        return CategorySoundItemViewHolder(parent.inflateLayout(R.layout.item_category_detail))
    }

    override fun onBindViewHolder(holder: CategorySoundItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.also {
            it.textCategoryName.text = item.category.typeName
            it.textSoundName.text = item.name
        }
    }


    inner class CategorySoundItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val textSoundName = itemView.TextSoundName
        val textCategoryName = itemView.TextSoundCategory
        val buttonLike = itemView.ButtonLike

        init {
            buttonLike.setOnCheckedChangeListener { _, b ->
                if (b) {
                    soundeSelected(getItem(adapterPosition))
                }
            }
        }
    }
}

object CategorySoundItemDiffUtil : DiffUtil.ItemCallback<SoundItem>() {

    override fun areItemsTheSame(oldItem: SoundItem, newItem: SoundItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SoundItem, newItem: SoundItem): Boolean {
        return true
    }
}