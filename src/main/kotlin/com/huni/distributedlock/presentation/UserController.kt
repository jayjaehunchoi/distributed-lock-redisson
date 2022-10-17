package com.huni.distributedlock.presentation

import com.huni.distributedlock.application.LockingUserService
import com.huni.distributedlock.application.UserResponse
import com.huni.distributedlock.application.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    @GetMapping("/api/users")
    fun findAll(): ResponseEntity<List<UserResponse>> {
        return ResponseEntity.ok(userService.getUsers())
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handler(e: IllegalArgumentException): ResponseEntity<String> {
        log.error("error !! {}", e.message)
        return ResponseEntity.badRequest().body(e.message)
    }

    companion object {
        private val log = LoggerFactory.getLogger(UserController::class.java)
    }
}
