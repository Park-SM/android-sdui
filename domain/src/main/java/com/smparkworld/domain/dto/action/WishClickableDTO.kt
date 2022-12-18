package com.smparkworld.domain.dto.action

interface WishClickableDTO {

    fun getWishTargetId(): Long?

    fun getWishState(): Boolean?

    fun setWishState(isWished: Boolean)
}