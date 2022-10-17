package com.huni.distributedlock.application

import com.huni.distributedlock.lock.LockManager
import org.springframework.stereotype.Component

@Component
class LockingUserService(
    private val userService: UserService,
    private val lockManager: LockManager
) {
    fun addNewCoupon(userId: Long) {
        lockManager.executeWithLock(userId.toString(), "coupon") { userService.addNewCoupon(userId) }
    }
}
