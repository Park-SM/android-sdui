package com.smparkworld.park.domain.usecase

import com.smparkworld.park.di.qualifier.IoDispatcher
import com.smparkworld.park.domain.dto.ParkSectionsDTO
import com.smparkworld.park.domain.repository.ParkRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

private typealias ParamType = String
private typealias ResultType = ParkSectionsDTO

class GetSectionsUseCase @Inject constructor(
    private val parkRepository: ParkRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCaseWithParam<ParamType, ResultType>(dispatcher) {

    override suspend fun execute(parameter: ParamType): ResultType {
        return parkRepository.requestSections(parameter)
    }
}