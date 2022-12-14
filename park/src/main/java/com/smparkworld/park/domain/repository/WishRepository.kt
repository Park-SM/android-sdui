package com.smparkworld.park.domain.repository

interface WishRepository {

    suspend fun createWish(id: Long): Boolean

    suspend fun deleteWish(id: Long): Boolean
}