package com.huni.distributedlock.application

import com.huni.distributedlock.domain.Coupon
import com.huni.distributedlock.domain.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository
) {

    fun addNewCoupon(userId: Long) {
        log.info("user 조회")
        val user = userRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException()

        val serial = UUID.randomUUID().toString().substring(0, 8)
        val coupon = Coupon(serial)

        user.addCoupon(coupon)
    }

    companion object {
        private val log = LoggerFactory.getLogger(UserService::class.java)
    }
}
