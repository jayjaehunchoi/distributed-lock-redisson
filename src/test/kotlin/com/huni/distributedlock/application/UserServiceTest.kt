package com.huni.distributedlock.application

import com.huni.distributedlock.domain.User
import com.huni.distributedlock.domain.UserRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserServiceTest(
    private val userService: UserService,
    private val userRepository: UserRepository
) {

    @Test
    fun `새로운 쿠폰을 추가한다`() {
        val user = userRepository.save(User("huni"))
        userService.addNewCoupon(user.id ?: throw IllegalArgumentException())
        val foundUser = userRepository.findByIdWithCoupons(user.id) ?: throw IllegalArgumentException()

        foundUser.coupons shouldHaveSize 1
    }

    @Test
    fun `쿠폰 제한 숫자를 초과하면 에러가 발생한다`() {
        val user = userRepository.save(User("huni"))
        val id = user.id ?: throw IllegalArgumentException()
        userService.addNewCoupon(id)
        userService.addNewCoupon(id)

        shouldThrow<IllegalArgumentException> {
            userService.addNewCoupon(id)
        }.shouldHaveMessage("최대 쿠폰 수를 초과했습니다.")
    }
}
