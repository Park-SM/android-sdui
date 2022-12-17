package com.smparkworld.park.domain.usecase

import com.smparkworld.park.di.annotation.DefaultDispatcher
import com.smparkworld.park.domain.dto.SectionDTO
import com.smparkworld.park.domain.repository.SectionRepository
import com.smparkworld.park.model.action.WishClickableDTO
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RollbackSectionWishStateUseCase @Inject constructor(
    private val sectionRepository: SectionRepository,
    @DefaultDispatcher dispatcher: CoroutineDispatcher
) : UseCaseWithParam<RollbackSectionWishStateUseCase.Payload, List<SectionDTO>>(dispatcher) {

    override suspend fun execute(parameters: Payload): List<SectionDTO> {
        val resultItems = mutableListOf<SectionDTO>()

        parameters.originItems.forEach { item ->
            if (isRollbackTargetSection(item, parameters.id, parameters.isWished)) {
                resultItems.add(getRollbackWishSection(item, parameters.isWished))
            } else {
                resultItems.add(item)
            }
        }

        return resultItems
    }

    private fun isRollbackTargetSection(item: SectionDTO, id: Long, isWished: Boolean) =
        (item is WishClickableDTO && item.getWishTargetId() == id && item.getWishState() == isWished)

    private suspend fun getRollbackWishSection(item: SectionDTO, isWished: Boolean): SectionDTO {
        val newItem = sectionRepository.cloneSection(item)
        if (newItem is WishClickableDTO) {
            newItem.setWishState(!isWished)
        }
        return newItem
    }

    data class Payload(
        val id: Long,
        val isWished: Boolean,
        val originItems: List<SectionDTO>
    )
}