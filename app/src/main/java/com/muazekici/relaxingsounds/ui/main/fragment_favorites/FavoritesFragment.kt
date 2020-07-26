package com.muazekici.relaxingsounds.ui.main.fragment_favorites

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
import com.muazekici.relaxingsounds.ui.main.fragment_favorites.adapters.FavoritesListAdapter
import com.muazekici.relaxingsounds.ui.main.showProgress
import com.muazekici.relaxingsounds.ui.widgets.MarginItemDecorator
import com.muazekici.relaxingsounds.usecases.UseCaseResult
import com.muazekici.relaxingsounds.usecases.ifSuccess
import kotlinx.android.synthetic.main.fragment_favorites.*
import javax.inject.Inject

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val favoritesListAdapter by lazy {
        FavoritesListAdapter(
            { soundItem ->
                favoritesViewModel.removeFavorite(soundItem)
            },
            { soundItem, b ->
                favoritesViewModel.playPauseSound(soundItem, b)
            },
            { soundItem ->
                favoritesViewModel.changeVolume(soundItem)
            }
        )
    }

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val favoritesViewModel by viewModels<FavoritesViewModel> { vmFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.MainActivityComponent().injectFavoritesFragment(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ListFavorites.adapter = favoritesListAdapter
        ListFavorites.addItemDecoration(MarginItemDecorator(resources.getDimensionPixelSize(R.dimen.dimen_full)))
        favoritesViewModel.favorites.observe(viewLifecycleOwner) {
            if (it is UseCaseResult.Loading) requireActivity().showProgress() else requireActivity().dismissProgress()

            it.ifSuccess {
                favoritesListAdapter.submitList(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.let {
            it.title = "Favorites"
            it.setDisplayHomeAsUpEnabled(false)
        }
    }


}