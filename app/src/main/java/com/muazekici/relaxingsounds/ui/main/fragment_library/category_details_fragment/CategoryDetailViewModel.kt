package com.muazekici.relaxingsounds.ui.main.fragment_library.category_details_fragment

import androidx.lifecycle.*
import com.muazekici.relaxingsounds.gateways_and_adapters.SoundItem
import com.muazekici.relaxingsounds.ui.utils.filter
import com.muazekici.relaxingsounds.usecases.AddFavoriteUseCase
import com.muazekici.relaxingsounds.usecases.GetCategorySoundUseCase
import com.muazekici.relaxingsounds.usecases.UseCaseResult
import com.muazekici.relaxingsounds.usecases.ifSuccess
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryDetailViewModel @Inject constructor(
    private val getCategorySoundUseCase: GetCategorySoundUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase
) : ViewModel() {

    private val _categorySounds = MutableLiveData<UseCaseResult<List<SoundItem>>>()
    val categorySounds: LiveData<UseCaseResult<List<SoundItem>>>
        get() = _categorySounds


    fun getCategorySounds(categoryId: Long) {
        viewModelScope.launch {
            getCategorySoundUseCase(categoryId, _categorySounds)
        }
    }

    fun addFavorite(soundItem: SoundItem) {
        _categorySounds.value.takeIf { it is UseCaseResult.Success }?.ifSuccess { sounds ->
            viewModelScope.launch {
                addFavoriteUseCase(Pair(soundItem, sounds), _categorySounds)
            }
        }
    }
}