package com.smparkworld.domain.usecase

import com.smparkworld.qualifier.IoDispatcher
import com.smparkworld.domain.dto.SectionDTO
import com.smparkworld.domain.repository.SectionRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RequestPartialUpdateSectionUseCase @Inject constructor(
    private val sectionRepository: SectionRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCaseWithParam<List<SectionDTO>, List<SectionDTO>>(dispatcher) {

    override suspend fun execute(parameters: List<SectionDTO>): List<SectionDTO> {

        val oldPartialSections = linkedMapOf<Int, SectionDTO>().apply {
            parameters.forEachIndexed { index, sectionDTO ->
                if (sectionDTO.refresh?.type == TYPE_RETURN) {
                    put(index, sectionDTO)
                }
            }
        }
        val payload = oldPartialSections.values.toList()

        val newPartialSections = sectionRepository.requestPartialUpdateSections(payload)

        return mergePartialUpdatedSections(parameters, oldPartialSections, newPartialSections)
    }

    private fun mergePartialUpdatedSections(
        origin: List<SectionDTO>,
        oldPartialUpdateSections: LinkedHashMap<Int, SectionDTO>,
        newPartialUpdateSections: List<SectionDTO>
    ): List<SectionDTO> {

        if (oldPartialUpdateSections.values.size != newPartialUpdateSections.size) {
            throw IllegalStateException("The size of the requested " +
                    "section partial update is different from the size received.")
        }

        val resultSections = origin.toMutableList()

        val updatedSections = oldPartialUpdateSections.onEachIndexed { index, map ->
            oldPartialUpdateSections[map.key] = newPartialUpdateSections[index]
        }

        for ((position, newSection) in updatedSections) {
            resultSections.removeAt(position)
            resultSections.add(position, newSection)
        }

        return resultSections
    }

    companion object {

        private const val TYPE_RETURN = "RETURN"
    }
}