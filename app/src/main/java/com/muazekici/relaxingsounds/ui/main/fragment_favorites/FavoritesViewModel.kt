package com.muazekici.relaxingsounds.ui.main.fragment_favorites

import androidx.lifecycle.*
import com.muazekici.relaxingsounds.gateways_and_adapters.SoundItem
import com.muazekici.relaxingsounds.gateways_and_adapters.repositories.FavoritesRepository
import com.muazekici.relaxingsounds.usecases.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val playStopSoundUseCase: PlayStopSoundUseCase,
    private val changeSoundVolumeUseCase: ChangeSoundVolumeUseCase
) : ViewModel() {

    private val _favorites = MutableLiveData<UseCaseResult<List<SoundItem>>>()

    val favorites: LiveData<UseCaseResult<List<SoundItem>>>
        get() = _favorites


    init {
        viewModelScope.launch {
            getFavoritesUseCase.run(Unit).collect {
                _favorites.postValue(it)
            }
        }
    }

    fun removeFavorite(soundItem: SoundItem) {
        viewModelScope.launch {
            removeFavoriteUseCase(soundItem)
        }
    }

    fun playPauseSound(soundItem: SoundItem, state: Boolean) {
        viewModelScope.launch {
            playStopSoundUseCase(Pair(soundItem, state))
        }
    }

    fun changeVolume(soundItem: SoundItem) {
        viewModelScope.launch {
            changeSoundVolumeUseCase(soundItem)
        }
    }
}