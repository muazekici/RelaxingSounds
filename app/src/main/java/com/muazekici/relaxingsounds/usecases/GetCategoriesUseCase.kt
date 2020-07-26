package com.muazekici.relaxingsounds.usecases

import com.muazekici.relaxingsounds.gateways_and_adapters.Category
import com.muazekici.relaxingsounds.gateways_and_adapters.repositories.CategoriesRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val categoriesRepository: CategoriesRepository) :
    UseCase<Unit, List<Category>>() {

    override suspend fun execute(parameter: Unit): List<Category> {
        delay(1000)//for fake loading progress purposes
        return categoriesRepository.getCategories()
    }
}