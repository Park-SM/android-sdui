package com.smparkworld.domain.usecase

import com.smparkworld.qualifier.IoDispatcher
import com.smparkworld.domain.dto.SectionDTO
import com.smparkworld.domain.dto.action.WishClickableDTO
import com.smparkworld.domain.repository.SectionRepository
import com.smparkworld.domain.repository.WishRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

// Assume that all products are managed with one ID system.
// If the ID system is divided by product domain,
// the `SectionRepository` and `WishRepository` classes can
// be combined into one.
class SyncSectionsWishStateUseCase @Inject constructor(
    private val sectionRepository: SectionRepository,
    private val wishRepository: WishRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCaseWithParam<List<SectionDTO>, List<SectionDTO>>(dispatcher) {

    override suspend fun execute(parameters: List<SectionDTO>): List<SectionDTO> {
        val resultItems = mutableListOf<SectionDTO>()

        parameters.forEach { item ->
            if (isIncorrectWishStateItem(item)) {
                resultItems.add(getUpdatedWishSection(item))
            } else {
                resultItems.add(item)
            }
        }
        return resultItems
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

            wishRepository.getCachedWishState(newItemId)?.let { newWishState ->
                newItem.setWishState(newWishState)
            }
        }
        return newItem
    }
}