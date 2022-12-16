package com.smparkworld.park.domain.usecase

import com.smparkworld.park.di.annotation.IoDispatcher
import com.smparkworld.park.domain.dto.SectionDTO
import com.smparkworld.park.domain.repository.SectionRepository
import com.smparkworld.park.domain.repository.WishRepository
import com.smparkworld.park.model.action.WishClickableDTO
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

// Assume that all products are managed with one ID system.
// If the ID system is divided by product domain,
// the `SectionRepository` and `WishRepository` classes can
// be combined into one.
class SyncSectionWishStateUseCase @Inject constructor(
    private val sectionRepository: SectionRepository,
    private val wishRepository: WishRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCaseWithParam<List<SectionDTO>, List<SectionDTO>>(dispatcher) {

    override suspend fun execute(parameters: List<SectionDTO>): List<SectionDTO> {

        val weakClonedItems = parameters.toMutableList()
        weakClonedItems.forEachIndexed { index, item ->
            if (isIncorrectWishStateItem(item)) {
                val newItem = getUpdatedWishSection(item)
                weakClonedItems.removeAt(index)
                weakClonedItems.add(index, newItem)
            }
        }
        return weakClonedItems
    }

    private suspend fun isIncorrectWishStateItem(item: SectionDTO): Boolean {
        return if (item is WishClickableDTO) {

            val targetId = item.getWishTargetId()
                ?: return false

            (item.getWishState() != wishRepository.getCachedWishState(targetId))
        } else {
            false
        }
    }

    private suspend fun getUpdatedWishSection(item: SectionDTO): SectionDTO {
        val newItem = sectionRepository.cloneSection(item)
        if (newItem is WishClickableDTO) {

            val newItemId = newItem.getWishTargetId()
                ?: return newItem

            newItem.setWishState(wishRepository.getCachedWishState(newItemId) ?: false)
        }
        return newItem
    }
}