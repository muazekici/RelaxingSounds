package com.muazekici.relaxingsounds.ui.main.fragment_favorites.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muazekici.relaxingsounds.R
import com.muazekici.relaxingsounds.gateways_and_adapters.SoundItem
import com.muazekici.relaxingsounds.ui.utils.inflateLayout

class FavoritesListAdapter :
    ListAdapter<SoundItem, FavoritesListAdapter.FavoriteSoundItemViewHolder>(SoundItemDiffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteSoundItemViewHolder {
        return FavoriteSoundItemViewHolder(parent.inflateLayout(R.layout.item_sound_favorite))
    }

    override fun onBindViewHolder(holder: FavoriteSoundItemViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 20
    }

    inner class FavoriteSoundItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    }
}

object SoundItemDiffUtil : DiffUtil.ItemCallback<SoundItem>() {

    override fun areItemsTheSame(oldItem: SoundItem, newItem: SoundItem): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: SoundItem, newItem: SoundItem): Boolean {
        return false
    }
}