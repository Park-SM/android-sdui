package com.smparkworld.data.source.remote

import com.smparkworld.data.vo.ProductDetailVO
import javax.inject.Inject

interface ProductRemoteDataSource {

    suspend fun getProductById(id: Long): ProductDetailVO
}

class FakeProductRemoteDataSourceImpl @Inject constructor(

) : ProductRemoteDataSource {

    override suspend fun getProductById(id: Long): ProductDetailVO {
        return when (id) {
            333L -> {
                getProduct333()
            }
            555L -> {
                getProduct555()
            }
            777L -> {
                getProduct777()
            }
            999L -> {
                getProduct999()
            }
            121L -> {
                getProduct121()
            }
            131L -> {
                getProduct131()
            }
            141L -> {
                getProduct141()
            }
            151L -> {
                getProduct151()
            }
            else -> {
                throw Exception("Not found product by id:$id")
            }
        }
    }

    private fun getProduct333(): ProductDetailVO {
        return ProductDetailVO(
            id = 333L,
            imageUri = "http://smparkworld.com/img/blog_images/98866035372614732852192638679394314086458144735809.png",
            title = "[서울] 아쿠아플라넷 63 & 맥스달튼 얼리버드 입장권",
            category = "입장권 • 서울",
            reviewScore = "4.6 (358)",
            price = "10,080원",
            isWished = true,
        )
    }

    private fun getProduct555(): ProductDetailVO {
        return ProductDetailVO(
            id = 555L,
            imageUri = "http://smparkworld.com/img/blog_images/28700093715932212986310862007881110398175148893334.png",
            title = "[대구] 이월드 연간회원권",
            category = "입장권 • 대구",
            reviewScore = "4.6 (382)",
            price = "119,000원",
            isWished = true,
        )
    }

    private fun getProduct777(): ProductDetailVO {
        return ProductDetailVO(
            id = 777L,
            imageUri = "http://smparkworld.com/img/blog_images/06457547313343780518062733748263636158307658431761.png",
            title = "[제주] 제주투어패스 48시간 프리패스권",
            category = "입장권 • 제주",
            reviewScore = "4.8 (247)",
            price = "18,905원",
            isWished = false,
        )
    }

    private fun getProduct999(): ProductDetailVO {
        return ProductDetailVO(
            id = 999L,
            imageUri = "http://smparkworld.com/img/blog_images/16983926760026723958646828432249051461689215943998.png",
            title = "[제주시] 제주 에코랜드 테마파크 입장권",
            category = "입장권 • 제주",
            reviewScore = "4.7 (396)",
            price = "7,347원",
            isWished = false,
        )
    }

    private fun getProduct121(): ProductDetailVO {
        return ProductDetailVO(
            id = 121L,
            imageUri = "http://smparkworld.com/img/blog_images/16291211130868351452949271973491813169204333982771.png",
            title = "[전남 여수] 아쿠아플라넷 여수 + 뮤지엄오브컬러",
            category = "입장권 • 전라도",
            reviewScore = "4.9 (145)",
            price = "19,950원",
            isWished = false,
        )
    }

    private fun getProduct131(): ProductDetailVO {
        return ProductDetailVO(
            id = 131L,
            imageUri = "http://smparkworld.com/img/blog_images/87008134278537678068690502608342159353376206743060.png",
            title = "[대구] X-MAS 얼리버드 이월드 자유이용권",
            category = "입장권 • 대구",
            reviewScore = "4.3 (384)",
            price = "15,423원",
            isWished = false,
        )
    }

    private fun getProduct141(): ProductDetailVO {
        return ProductDetailVO(
            id = 141L,
            imageUri = "http://smparkworld.com/img/blog_images/70986350307720596737671692581374188341053778385665.png",
            title = "[경기 고양] 일산 원마운트 워터파크",
            category = "입장권 • 경기도",
            reviewScore = "3.7 (47)",
            price = "24,864원",
            isWished = false,
        )
    }

    private fun getProduct151(): ProductDetailVO {
        return ProductDetailVO(
            id = 151L,
            imageUri = "http://smparkworld.com/img/blog_images/34909948107269078026609846524479087845551772700263.png",
            title = "[QR바로입장] 롯데월드 어드벤처 종합이용권",
            category = "입장권 • 서울",
            reviewScore = "4.5 (2,008)",
            price = "32,010원",
            isWished = false,
        )
    }
}