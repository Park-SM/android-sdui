package com.smparkworld.park.model.action

interface WishClickableDTO {

    fun getWishTargetId(): Long?

    fun getWishState(): Boolean?

    fun setWishState(isWished: Boolean)
}