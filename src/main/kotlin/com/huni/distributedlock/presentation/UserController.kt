package com.huni.distributedlock.presentation

import com.huni.distributedlock.application.LockingUserService
import com.huni.distributedlock.application.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
    private val lockingUserService: LockingUserService
) {

    @PostMapping("/api/users/{userId}/create-coupon")
    fun createNewCoupon(@PathVariable userId: Long): ResponseEntity<Void> {
        userService.addNewCoupon(userId)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/api/users/{userId}/create-coupon-locking")
    fun createNewCouponWithLocking(@PathVariable userId: Long): ResponseEntity<Void> {
        lockingUserService.addNewCoupon(userId)
        return ResponseEntity.ok().build()
    }
}