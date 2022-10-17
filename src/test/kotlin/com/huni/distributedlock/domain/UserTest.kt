package com.huni.distributedlock.domain

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

internal class UserTest {

    @Test
    fun `개수를 초과하면 에러가 발생한다`() {
        val user = User("name", id = 1L)
        user.addCoupon(Coupon("serial1", id = 1L))
        user.addCoupon(Coupon("serial2", id = 2L))

        shouldThrow<IllegalArgumentException> {
            user.addCoupon(Coupon("serial3", id = 3L))
        }
    }
}
