package com.muazekici.relaxingsounds.ui.main.fragment_favorites.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.SeekBar
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muazekici.relaxingsounds.R
import com.muazekici.relaxingsounds.gateways_and_adapters.SoundItem
import com.muazekici.relaxingsounds.ui.utils.inflateLayout
import kotlinx.android.synthetic.main.item_sound_favorite.view.*

class FavoritesListAdapter(
    private val removeFavorite: (SoundItem) -> Unit,
    private val playPauseSound: (SoundItem, Boolean) -> Unit,
    private val changeVolume: (SoundItem) -> Unit
) : ListAdapter<SoundItem, FavoritesListAdapter.FavoriteSoundItemViewHolder>(SoundItemDiffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteSoundItemViewHolder {
        return FavoriteSoundItemViewHolder(parent.inflateLayout(R.layout.item_sound_favorite))
    }

    override fun onBindViewHolder(holder: FavoriteSoundItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.apply {
            textSoundName.text = item.name
            textSoundCategoryName.text = item.category.typeName
            buttonLike.isChecked = item.isFavorite
            seekBarVolume.progress = (item.soundLevel * 100).toInt()
            setPlayState(item.isPlaying)
        }
    }


    inner class FavoriteSoundItemViewHolder(v: View) : RecyclerView.ViewHolder(v),
        CompoundButton.OnCheckedChangeListener {
        val textSoundName = itemView.TextSoundName
        val textSoundCategoryName = itemView.TextSoundCategory
        val buttonLike = itemView.ButtonLike
        val seekBarVolume = itemView.SeekBarVolume
        val buttonPlayPause = itemView.ButtonPlayPause


        init {
            buttonLike.setOnCheckedChangeListener { _, b ->
                if (!b) {
                    removeFavorite(getItem(adapterPosition))
                }
            }

            buttonPlayPause.setOnCheckedChangeListener(this)

            seekBarVolume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                    p0?.let {
                        getItem(adapterPosition).apply {
                            soundLevel = it.progress / 100f
                            changeVolume(this)
                        }
                    }
                }

            }

            )
        }

        fun setPlayState(isPlaying: Boolean) {
            buttonPlayPause.setOnCheckedChangeListener(null)
            buttonPlayPause.isChecked = isPlaying
            buttonPlayPause.setOnCheckedChangeListener(this)
        }

        override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
            if (!p1) {
                seekBarVolume.progress = 50
            }
            playPauseSound(getItem(adapterPosition), p1)
        }
    }
}

object SoundItemDiffUtil : DiffUtil.ItemCallback<SoundItem>() {

    override fun areItemsTheSame(oldItem: SoundItem, newItem: SoundItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SoundItem, newItem: SoundItem): Boolean {
        return oldItem.id == newItem.id
                && oldItem.isFavorite == newItem.isFavorite
                && oldItem.isPlaying == newItem.isPlaying
                && oldItem.soundLevel == newItem.soundLevel
    }
}