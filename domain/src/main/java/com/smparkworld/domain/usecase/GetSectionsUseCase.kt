package com.smparkworld.domain.usecase

import com.smparkworld.qualifier.IoDispatcher
import com.smparkworld.domain.dto.ParkSectionsDTO
import com.smparkworld.domain.repository.SectionRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetSectionsUseCase @Inject constructor(
    private val sectionRepository: SectionRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCaseWithParam<String, ParkSectionsDTO>(dispatcher) {

    override suspend fun execute(parameters: String): ParkSectionsDTO {
        return sectionRepository.requestSections(parameters)
    }
}