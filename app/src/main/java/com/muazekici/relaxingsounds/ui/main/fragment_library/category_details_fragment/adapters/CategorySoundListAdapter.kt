package com.muazekici.relaxingsounds.ui.main.fragment_library.category_details_fragment.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muazekici.relaxingsounds.R
import com.muazekici.relaxingsounds.gateways_and_adapters.SoundItem
import com.muazekici.relaxingsounds.ui.utils.inflateLayout

class CategorySoundListAdapter :
    ListAdapter<SoundItem, CategorySoundListAdapter.CategorySoundItemViewHolder>(
        CategorySoundItemDiffUtil
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorySoundItemViewHolder {
        return CategorySoundItemViewHolder(parent.inflateLayout(R.layout.item_category_detail))
    }

    override fun onBindViewHolder(holder: CategorySoundItemViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 20
    }

    inner class CategorySoundItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    }
}

object CategorySoundItemDiffUtil : DiffUtil.ItemCallback<SoundItem>() {

    override fun areItemsTheSame(oldItem: SoundItem, newItem: SoundItem): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: SoundItem, newItem: SoundItem): Boolean {
        return false
    }
}