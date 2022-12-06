package com.smparkworld.park.data.source.remote

import com.google.gson.Gson
import com.smparkworld.hiltbinder.HiltBinds
import com.smparkworld.park.data.vo.ParkSectionsVO
import javax.inject.Inject


interface ParkRemoteDataSource {

    suspend fun requestSections(): ParkSectionsVO
}

@HiltBinds
class ParkRemoteDataSourceFakeImpl @Inject constructor(
    private val gson: Gson
) : ParkRemoteDataSource {

    override suspend fun requestSections(): ParkSectionsVO {

        return gson.fromJson(getRawData(), ParkSectionsVO::class.java)
    }

    private fun getRawData(): String = """
        {
            "requestUrl": {
                "nextPageUrl": null,
                "nextPageTriggerPosition": null 
            },
            "sections": [
                {
                    "sectionType": "PRODUCT",
                    "viewType": "TEST_PRODUCT_DEFAULT",
                    "data" {
                        "id": "1111",
                        "imageUrl": "http://smparkworld.com/test-product-image.png",
                        "title": "[서울] 아쿠아플라넷 63 & 맥스달튼 얼리버드 입장권",
                        "category": "입장권 • 서울",
                        "reviewScore": "4.6 (358)",
                        "price": "10,080원"
                    },
                    "action": {
                        "target": "ROOT",
                        "triggerType": "CLICK",
                        "type": "LINK",
                        "linkUrl": "/product/detail/1111"
                    },
                    "refresh": null,
                    "logs": [
                        {
                            "type": "CLICK",
                            "data": {
                                "id": "1111"
                            }
                        },
                        {
                            "type": "IMPRESSION",
                            "data": {
                                "id": "1111"
                            }
                        }
                    ]
                    ""
                }
            ]
        }
    """.trimIndent()
}