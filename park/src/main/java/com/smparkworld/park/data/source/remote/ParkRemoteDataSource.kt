package com.smparkworld.park.data.source.remote

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smparkworld.hiltbinder.HiltBinds
import com.smparkworld.park.model.Section
import javax.inject.Inject


interface ParkRemoteDataSource {

    suspend fun requestSections(): List<Section>
}

@HiltBinds
class ParkRemoteDataSourceFakeImpl @Inject constructor(
    private val gson: Gson
) : ParkRemoteDataSource {

    override suspend fun requestSections(): List<Section> {

        val responseType = object : TypeToken<List<Section>?>() {}.type

        return gson.fromJson(getRawData(), responseType)
    }

    private fun getRawData(): String = """
        [
            {   
                "viewType": "TEST_PRODUCT",
                ""
            }
        ]
    """.trimIndent()
}