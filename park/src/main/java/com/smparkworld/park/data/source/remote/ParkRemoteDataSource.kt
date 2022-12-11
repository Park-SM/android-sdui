package com.smparkworld.park.data.source.remote

import com.google.gson.Gson
import com.smparkworld.hiltbinder.HiltBinds
import com.smparkworld.park.data.vo.ParkSectionsVO
import javax.inject.Inject

interface ParkRemoteDataSource {

    suspend fun requestSections(url: String): ParkSectionsVO
}

@HiltBinds
class ParkRemoteDataSourceFakeImpl @Inject constructor(
    private val gson: Gson
) : ParkRemoteDataSource {

    override suspend fun requestSections(url: String): ParkSectionsVO {

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
                "sectionType": "PRODUCT_SECTION",
                "viewType": "TEST_PRODUCT_DEFAULT",
                "id": "333",
                "imageUrl": "http://smparkworld.com/img/blog_images/98866035372614732852192638679394314086458144735809.png",
                "title": "[서울] 아쿠아플라넷 63 & 맥스달튼 얼리버드 입장권",
                "category": "입장권 • 서울",
                "reviewScore": "4.6 (358)",
                "price": "10,080원",
                "linkUrl": "sdui://product/detail/333",
                "refresh": null,
                "logs": [
                    {
                        "type": "CLICK",
                        "data": {
                            "id": "333"
                        }
                    },
                    {
                        "type": "IMPRESSION",
                        "data": {
                            "id": "333"
                        }
                    }
                ]
            },
            {
                "sectionType": "PRODUCT_SECTION",
                "viewType": "TEST_PRODUCT_DEFAULT",
                "id": "555",
                "imageUrl": "http://smparkworld.com/img/blog_images/06457547313343780518062733748263636158307658431761.png",
                "title": "[제주] 제주투어패스 48시간 프리패스권",
                "category": "입장권 • 제주",
                "reviewScore": "4.8 (247)",
                "price": "18,905원",
                "linkUrl": "sdui://product/detail/555",
                "refresh": null,
                "logs": [
                    {
                        "type": "CLICK",
                        "data": {
                            "id": "555"
                        }
                    },
                    {
                        "type": "IMPRESSION",
                        "data": {
                            "id": "555"
                        }
                    }
                ]
            }
        ]
    }
    """.trimIndent()
}