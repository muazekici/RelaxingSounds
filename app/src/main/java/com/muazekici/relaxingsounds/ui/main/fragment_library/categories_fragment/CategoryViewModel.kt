package com.muazekici.relaxingsounds.ui.main.fragment_library.categories_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muazekici.relaxingsounds.gateways_and_adapters.Category
import com.muazekici.relaxingsounds.usecases.GetCategoriesUseCase
import com.muazekici.relaxingsounds.usecases.UseCaseResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryViewModel @Inject constructor(private val getCategoriesUseCase: GetCategoriesUseCase) :
    ViewModel() {

    private val _categories = MutableLiveData<UseCaseResult<List<Category>>>()
    val categories: LiveData<UseCaseResult<List<Category>>>
        get() = _categories

    init {
        viewModelScope.launch {
            getCategoriesUseCase(Unit, _categories)
        }
    }
}