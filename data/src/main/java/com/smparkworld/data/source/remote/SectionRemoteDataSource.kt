package com.smparkworld.data.source.remote

import com.google.gson.Gson
import com.smparkworld.data.vo.BannerSectionVO
import com.smparkworld.data.vo.ParkRefreshVO
import com.smparkworld.data.vo.ParkSectionsVO
import com.smparkworld.data.vo.SectionVO
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

interface SectionRemoteDataSource {

    suspend fun requestSections(uri: String): ParkSectionsVO

    suspend fun cloneSection(section: SectionVO): SectionVO

    suspend fun requestPartialUpdateSections(sections: List<SectionVO>): List<SectionVO>
}

class FakeSectionRemoteDataSourceImpl @Inject constructor(
    private val gson: Gson
) : SectionRemoteDataSource {

    override suspend fun requestSections(uri: String): ParkSectionsVO {

        return when {
            (uri == "/products?page=1" || uri == "/products") -> {
                delay(350L) // mocked network latency
                gson.fromJson(getRawDataForPage1(), ParkSectionsVO::class.java)
            }
            (uri == "/products?page=2") -> {
                delay(350L) // mocked network latency
                gson.fromJson(getRawDataForPage2(), ParkSectionsVO::class.java)
            }
            else -> {
                throw Exception("Test Exception - Unknown Request URI.")
            }
        }
    }

    override suspend fun cloneSection(section: SectionVO): SectionVO {
        return gson.fromJson(gson.toJson(section), SectionVO::class.java)
    }

    override suspend fun requestPartialUpdateSections(sections: List<SectionVO>): List<SectionVO> {
        val results = mutableListOf<SectionVO>()

        sections.forEach { section ->

            // about BANNER_SECTION
            if (section.sectionType == "BANNER_SECTION" &&
                section.viewType == "BANNER_ONE_COLUMN" &&
                section.refresh?.id == "CURATE_PROMOTION_BANNER") {

                when (Random.nextInt(1, 5)) {
                    1 -> results.add(getBanner1())
                    2 -> results.add(getBanner2())
                    3 -> results.add(getBanner3())
                    4 -> results.add(getBanner4())
                    5 -> results.add(getBanner5())
                }
            }
        }

        return results
    }

    private fun getRawDataForPage1(): String = """
        {
        "requestUri": {
            "nextPageUri": "/products?page=2",
            "nextPageTriggerPosition": 2
        },
        "sections": [
            {
                "sectionType": "PRODUCT_SECTION",
                "viewType": "PRODUCT_ONE_COLUMN",
                "id": "333",
                "imageUri": "http://smparkworld.com/img/blog_images/98866035372614732852192638679394314086458144735809.png",
                "title": "[서울] 아쿠아플라넷 63 & 맥스달튼 얼리버드 입장권",
                "category": "입장권 • 서울",
                "reviewScore": "4.6 (358)",
                "price": "10,080원",
                "isWished": "true",
                "linkUri": "parkui://product/detail?product_id=333",
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
                "viewType": "PRODUCT_ONE_COLUMN_2",
                "id": "555",
                "imageUri": "http://smparkworld.com/img/blog_images/28700093715932212986310862007881110398175148893334.png",
                "title": "[대구] 이월드 연간회원권",
                "category": "입장권 • 대구",
                "reviewScore": "4.6 (382)",
                "price": "119,000원",
                "isWished": "true",
                "linkUri": "parkui://product/detail?product_id=555",
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
            },
            {
                "sectionType": "PRODUCT_SECTION",
                "viewType": "PRODUCT_ONE_COLUMN",
                "id": "777",
                "imageUri": "http://smparkworld.com/img/blog_images/06457547313343780518062733748263636158307658431761.png",
                "title": "[제주] 제주투어패스 48시간 프리패스권",
                "category": "입장권 • 제주",
                "reviewScore": "4.8 (247)",
                "price": "18,905원",
                "isWished": "false",
                "linkUri": "parkui://product/detail?product_id=777",
                "refresh": null,
                "logs": [
                    {
                        "type": "CLICK",
                        "data": {
                            "id": "777"
                        }
                    },
                    {
                        "type": "IMPRESSION",
                        "data": {
                            "id": "777"
                        }
                    }
                ]
            },
            {
                "sectionType": "BANNER_SECTION",
                "viewType": "BANNER_ONE_COLUMN",
                "id": "10001",
                "title": null,
                "message": null,
                "imageUri": "http://smparkworld.com/img/blog_images/03874774406742364719443500337657533898844656306735.png",
                "linkUri": "parkui://webview?redirect_to=http://smparkworld.com/promotion/10001",
                "refresh": {
                    "type": "RETURN",
                    "id": "CURATE_PROMOTION_BANNER"
                },
                "logs": [
                    {
                        "type": "CLICK",
                        "data": {
                            "id": "10001"
                        }
                    },
                    {
                        "type": "IMPRESSION",
                        "data": {
                            "id": "10001"
                        }
                    }
                ]
            },
            {
                "sectionType": "PRODUCT_SECTION",
                "viewType": "PRODUCT_ONE_COLUMN",
                "id": "999",
                "imageUri": "http://smparkworld.com/img/blog_images/16983926760026723958646828432249051461689215943998.png",
                "title": "[제주시] 제주 에코랜드 테마파크 입장권",
                "category": "입장권 • 제주",
                "reviewScore": "4.7 (396)",
                "price": "7,347원",
                "isWished": "false",
                "linkUri": "parkui://product/detail?product_id=999",
                "refresh": null,
                "logs": [
                    {
                        "type": "CLICK",
                        "data": {
                            "id": "999"
                        }
                    },
                    {
                        "type": "IMPRESSION",
                        "data": {
                            "id": "999"
                        }
                    }
                ]
            },
            {
                "sectionType": "PRODUCT_SECTION",
                "viewType": "PRODUCT_ONE_COLUMN",
                "id": "121",
                "imageUri": "http://smparkworld.com/img/blog_images/16291211130868351452949271973491813169204333982771.png",
                "title": "[전남 여수] 아쿠아플라넷 여수 + 뮤지엄오브컬러",
                "category": "입장권 • 전라도",
                "reviewScore": "4.9 (145)",
                "price": "19,950원",
                "isWished": "false",
                "linkUri": "parkui://product/detail?product_id=121",
                "refresh": null,
                "logs": [
                    {
                        "type": "CLICK",
                        "data": {
                            "id": "121"
                        }
                    },
                    {
                        "type": "IMPRESSION",
                        "data": {
                            "id": "121"
                        }
                    }
                ]
            },
            {
                "sectionType": "PRODUCT_SECTION",
                "viewType": "PRODUCT_ONE_COLUMN",
                "id": "131",
                "imageUri": "http://smparkworld.com/img/blog_images/87008134278537678068690502608342159353376206743060.png",
                "title": "[대구] X-MAS 얼리버드 이월드 자유이용권",
                "category": "입장권 • 대구",
                "reviewScore": "4.3 (384)",
                "price": "15,423원",
                "isWished": "false",
                "linkUri": "parkui://product/detail?product_id=131",
                "refresh": null,
                "logs": [
                    {
                        "type": "CLICK",
                        "data": {
                            "id": "131"
                        }
                    },
                    {
                        "type": "IMPRESSION",
                        "data": {
                            "id": "131"
                        }
                    }
                ]
            }
        ]
    }
    """.trimIndent()

    private fun getRawDataForPage2(): String = """
        {
        "requestUri": {
            "nextPageUri": null,
            "nextPageTriggerPosition": null
        },
        "sections": [
            {
                "sectionType": "BANNER_SECTION",
                "viewType": "BANNER_ONE_COLUMN",
                "id": "10002",
                "title": null,
                "message": null,
                "imageUri": "http://smparkworld.com/img/blog_images/49716605770233819048279682845697778898975715953485.png",
                "linkUri": "parkui://webview?redirect_to=http://smparkworld.com/promotion/10002",
                "refresh": {
                    "type": "RETURN",
                    "id": "CURATE_PROMOTION_BANNER"
                },
                "logs": [
                    {
                        "type": "CLICK",
                        "data": {
                            "id": "10001"
                        }
                    },
                    {
                        "type": "IMPRESSION",
                        "data": {
                            "id": "10001"
                        }
                    }
                ]
            },
            {
                "sectionType": "PRODUCT_SECTION",
                "viewType": "PRODUCT_ONE_COLUMN",
                "id": "141",
                "imageUri": "http://smparkworld.com/img/blog_images/70986350307720596737671692581374188341053778385665.png",
                "title": "[경기 고양] 일산 원마운트 워터파크",
                "category": "입장권 • 경기도",
                "reviewScore": "3.7 (47)",
                "price": "24,864원",
                "isWished": "false",
                "linkUri": "parkui://product/detail?product_id=141",
                "refresh": null,
                "logs": [
                    {
                        "type": "CLICK",
                        "data": {
                            "id": "141"
                        }
                    },
                    {
                        "type": "IMPRESSION",
                        "data": {
                            "id": "141"
                        }
                    }
                ]
            },
            {
                "sectionType": "PRODUCT_SECTION",
                "viewType": "PRODUCT_ONE_COLUMN",
                "id": "151",
                "imageUri": "http://smparkworld.com/img/blog_images/34909948107269078026609846524479087845551772700263.png",
                "title": "[QR바로입장] 롯데월드 어드벤처 종합이용권",
                "category": "입장권 • 서울",
                "reviewScore": "4.5 (2,008)",
                "price": "32,010원",
                "isWished": "false",
                "linkUri": "parkui://product/detail?product_id=151",
                "refresh": null,
                "logs": [
                    {
                        "type": "CLICK",
                        "data": {
                            "id": "151"
                        }
                    },
                    {
                        "type": "IMPRESSION",
                        "data": {
                            "id": "151"
                        }
                    }
                ]
            },
            {
                "sectionType": "PRODUCT_SECTION",
                "viewType": "PRODUCT_ONE_COLUMN",
                "id": "121",
                "imageUri": "http://smparkworld.com/img/blog_images/16291211130868351452949271973491813169204333982771.png",
                "title": "[전남 여수] 아쿠아플라넷 여수 + 뮤지엄오브컬러",
                "category": "입장권 • 전라도",
                "reviewScore": "4.9 (145)",
                "price": "19,950원",
                "isWished": "false",
                "linkUri": "parkui://product/detail?product_id=121",
                "refresh": null,
                "logs": [
                    {
                        "type": "CLICK",
                        "data": {
                            "id": "121"
                        }
                    },
                    {
                        "type": "IMPRESSION",
                        "data": {
                            "id": "121"
                        }
                    }
                ]
            }
        ]
    }
    """.trimIndent()

    private fun getBanner1(): BannerSectionVO {
        return BannerSectionVO(
            sectionType = "BANNER_SECTION",
            viewType = "BANNER_ONE_COLUMN",
            refresh = ParkRefreshVO(
                type = "RETURN",
                id = "CURATE_PROMOTION_BANNER"
            ),
            title = null,
            message = null,
            imageUri = "http://smparkworld.com/img/blog_images/03874774406742364719443500337657533898844656306735.png",
            linkUri = "parkui://webview?redirect_to=http://smparkworld.com/promotion/10001"
        )
    }

    private fun getBanner2(): BannerSectionVO {
        return BannerSectionVO(
            sectionType = "BANNER_SECTION",
            viewType = "BANNER_ONE_COLUMN",
            refresh = ParkRefreshVO(
                type = "RETURN",
                id = "CURATE_PROMOTION_BANNER"
            ),
            title = null,
            message = null,
            imageUri = "http://smparkworld.com/img/blog_images/49716605770233819048279682845697778898975715953485.png",
            linkUri = "parkui://webview?redirect_to=http://smparkworld.com/promotion/10002"
        )
    }

    private fun getBanner3(): BannerSectionVO {
        return BannerSectionVO(
            sectionType = "BANNER_SECTION",
            viewType = "BANNER_ONE_COLUMN",
            refresh = ParkRefreshVO(
                type = "RETURN",
                id = "CURATE_PROMOTION_BANNER"
            ),
            title = null,
            message = null,
            imageUri = "http://smparkworld.com/img/blog_images/89016852741508166456526987980110807560743733400692.png",
            linkUri = "parkui://webview?redirect_to=http://smparkworld.com/promotion/10003"
        )
    }

    private fun getBanner4(): BannerSectionVO {
        return BannerSectionVO(
            sectionType = "BANNER_SECTION",
            viewType = "BANNER_ONE_COLUMN",
            refresh = ParkRefreshVO(
                type = "RETURN",
                id = "CURATE_PROMOTION_BANNER"
            ),
            title = null,
            message = null,
            imageUri = "http://smparkworld.com/img/blog_images/46297208347177014695376165264360176784891590873802.png",
            linkUri = "parkui://webview?redirect_to=http://smparkworld.com/promotion/10004"
        )
    }

    private fun getBanner5(): BannerSectionVO {
        return BannerSectionVO(
            sectionType = "BANNER_SECTION",
            viewType = "BANNER_ONE_COLUMN",
            refresh = ParkRefreshVO(
                type = "RETURN",
                id = "CURATE_PROMOTION_BANNER"
            ),
            title = null,
            message = null,
            imageUri = "http://smparkworld.com/img/blog_images/73355316462068393161959187232924191200784580485494.png",
            linkUri = "parkui://webview?redirect_to=http://smparkworld.com/promotion/10005"
        )
    }
}