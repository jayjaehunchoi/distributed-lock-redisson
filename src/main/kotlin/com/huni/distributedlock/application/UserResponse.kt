package com.huni.distributedlock.application

import com.huni.distributedlock.domain.Coupon
import com.huni.distributedlock.domain.User

data class UserResponse(
    val id: Long,
    val name: String,
    val coupons: CouponResponses
) {

    companion object {
        fun from(user: User): UserResponse {
            return UserResponse(user.id!!, user.name, CouponResponses.from(user.coupons))
        }
    }
}

data class CouponResponses(
    val size: Int,
    val coupons: List<CouponResponse>
) {

    companion object {
        fun from(coupons: Set<Coupon>): CouponResponses {
            val size = coupons.size
            val couponResponses = coupons.map { CouponResponse(it.id!!, it.serial) }
                .toCollection(mutableListOf())
            return CouponResponses(size, couponResponses)
        }
    }
}

data class CouponResponse(
    val couponId: Long,
    val serial: String
)
