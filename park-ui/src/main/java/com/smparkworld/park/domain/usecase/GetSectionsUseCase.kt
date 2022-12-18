package com.smparkworld.park.domain.usecase

import com.smparkworld.park.di.annotation.IoDispatcher
import com.smparkworld.park.domain.dto.ParkSectionsDTO
import com.smparkworld.park.domain.repository.SectionRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetSectionsUseCase @Inject constructor(
    private val sectionRepository: SectionRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCaseWithParam<String, ParkSectionsDTO>(dispatcher) {

    override suspend fun execute(parameter: String): ParkSectionsDTO {
        return sectionRepository.requestSections(parameter)
    }
}