package com.huni.distributedlock.application

import com.huni.distributedlock.lock.LockManager
import org.springframework.stereotype.Component
import java.util.*

@Component
class LockingUserService(
    private val userService: UserService,
    private val lockManager: LockManager
) {
    fun addNewCoupon(userId: Long) {
        val key = UUID.randomUUID().toString().substring(0, 5)
        lockManager.executeWithLock(key, "card") { userService.addNewCoupon(userId) }
    }
}
