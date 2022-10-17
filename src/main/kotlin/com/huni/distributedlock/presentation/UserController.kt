package com.huni.distributedlock.presentation

import com.huni.distributedlock.application.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {

    @PostMapping("/api/users/{userId}/create-coupon")
    fun createNewCoupon(@PathVariable userId: Long): ResponseEntity<Void> {
        userService.addNewCoupon(userId)
        return ResponseEntity.ok().build()
    }
}
