package com.smparkworld.park.data.repository

import com.smparkworld.hiltbinder.HiltBinds
import com.smparkworld.park.data.source.remote.ParkRemoteDataSource
import com.smparkworld.park.domain.repository.ParkRepository
import com.smparkworld.park.model.Section
import javax.inject.Inject
import javax.inject.Singleton

@HiltBinds
@Singleton
class ParkRepositoryImpl @Inject constructor(
    private val remoteDataSource: ParkRemoteDataSource
) : ParkRepository {

    override suspend fun requestSections(url: String): List<Section> {
        return remoteDataSource.requestSections()
    }
}