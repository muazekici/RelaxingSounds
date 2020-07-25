package com.muazekici.relaxingsounds.ui.main.fragment_favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.muazekici.relaxingsounds.R
import com.muazekici.relaxingsounds.ui.main.fragment_favorites.adapters.FavoritesListAdapter
import com.muazekici.relaxingsounds.ui.widgets.MarginItemDecorator
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val favoriteSoundsAdapter by lazy {
        FavoritesListAdapter()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ListFavorites.adapter = favoriteSoundsAdapter
        ListFavorites.addItemDecoration(MarginItemDecorator(resources.getDimensionPixelSize(R.dimen.dimen_full)))
    }
}