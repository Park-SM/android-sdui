package com.smparkworld.sduisample.ui.product.detail.delegator

import com.smparkworld.core.model.Result
import com.smparkworld.sduisample.domain.dto.ProductDTO
import javax.inject.Inject

class ProductFakeDelegator @Inject constructor(

) : ProductDelegator {

    override suspend fun getProductById(id: Long): Result<ProductDTO> {
        return when (id) {
            333L -> {
                Result.Success(getProduct333())
            }
            555L -> {
                Result.Success(getProduct555())
            }
            777L -> {
                Result.Success(getProduct777())
            }
            999L -> {
                Result.Success(getProduct999())
            }
            121L -> {
                Result.Success(getProduct121())
            }
            131L -> {
                Result.Success(getProduct131())
            }
            141L -> {
                Result.Success(getProduct141())
            }
            151L -> {
                Result.Success(getProduct151())
            }
            else -> {
                Result.Error(Exception("Not found product by id:$id"))
            }
        }
    }

    private fun getProduct333(): ProductDTO {
        return ProductDTO(
            id = 333L,
            imageUrl = "http://smparkworld.com/img/blog_images/98866035372614732852192638679394314086458144735809.png",
            title = "[서울] 아쿠아플라넷 63 & 맥스달튼 얼리버드 입장권",
            category = "입장권 • 서울",
            reviewScore = "4.6 (358)",
            price = "10,080원",
            isWished = true,
        )
    }

    private fun getProduct555(): ProductDTO {
        return ProductDTO(
            id = 555L,
            imageUrl = "http://smparkworld.com/img/blog_images/28700093715932212986310862007881110398175148893334.png",
            title = "[대구] 이월드 연간회원권",
            category = "입장권 • 대구",
            reviewScore = "4.6 (382)",
            price = "119,000원",
            isWished = false,
        )
    }

    private fun getProduct777(): ProductDTO {
        return ProductDTO(
            id = 777L,
            imageUrl = "http://smparkworld.com/img/blog_images/06457547313343780518062733748263636158307658431761.png",
            title = "[제주] 제주투어패스 48시간 프리패스권",
            category = "입장권 • 제주",
            reviewScore = "4.8 (247)",
            price = "18,905원",
            isWished = false,
        )
    }

    private fun getProduct999(): ProductDTO {
        return ProductDTO(
            id = 999L,
            imageUrl = "http://smparkworld.com/img/blog_images/16983926760026723958646828432249051461689215943998.png",
            title = "[제주시] 제주 에코랜드 테마파크 입장권",
            category = "입장권 • 제주",
            reviewScore = "4.7 (396)",
            price = "7,347원",
            isWished = false,
        )
    }

    private fun getProduct121(): ProductDTO {
        return ProductDTO(
            id = 121L,
            imageUrl = "http://smparkworld.com/img/blog_images/16291211130868351452949271973491813169204333982771.png",
            title = "[전남 여수] 아쿠아플라넷 여수 + 뮤지엄오브컬러",
            category = "입장권 • 전라도",
            reviewScore = "4.9 (145)",
            price = "19,950원",
            isWished = false,
        )
    }

    private fun getProduct131(): ProductDTO {
        return ProductDTO(
            id = 131L,
            imageUrl = "http://smparkworld.com/img/blog_images/87008134278537678068690502608342159353376206743060.png",
            title = "[대구] X-MAS 얼리버드 이월드 자유이용권",
            category = "입장권 • 대구",
            reviewScore = "4.3 (384)",
            price = "15,423원",
            isWished = false,
        )
    }

    private fun getProduct141(): ProductDTO {
        return ProductDTO(
            id = 141L,
            imageUrl = "http://smparkworld.com/img/blog_images/70986350307720596737671692581374188341053778385665.png",
            title = "[경기 고양] 일산 원마운트 워터파크",
            category = "입장권 • 경기도",
            reviewScore = "3.7 (47)",
            price = "24,864원",
            isWished = false,
        )
    }

    private fun getProduct151(): ProductDTO {
        return ProductDTO(
            id = 151L,
            imageUrl = "http://smparkworld.com/img/blog_images/34909948107269078026609846524479087845551772700263.png",
            title = "[QR바로입장] 롯데월드 어드벤처 종합이용권",
            category = "입장권 • 서울",
            reviewScore = "4.5 (2,008)",
            price = "32,010원",
            isWished = false,
        )
    }
}