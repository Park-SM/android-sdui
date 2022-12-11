package com.smparkworld.park.model.action

interface WishClickableDTO {

    fun getWishRequestUrl(isWished: Boolean): String?
}