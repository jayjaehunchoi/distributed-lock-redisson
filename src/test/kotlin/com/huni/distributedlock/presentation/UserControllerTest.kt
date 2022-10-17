package com.huni.distributedlock.presentation

import com.huni.distributedlock.domain.UserRepository
import com.huni.distributedlock.support.RequestUtils.Companion.concurrentPost
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserControllerTest(
    private val userRepository: UserRepository
) {

    @Test
    fun `락을 잡지 않을 경우 예상보다 많이 생긴다`() {
        concurrentPost(20, "http://localhost:8080/api/users/1/create-coupon")
        Thread.sleep(2000)
    }

    @Test
    fun `락으로 동시성을 해결한다`() {
        concurrentPost(20, "http://localhost:8080/api/users/2/create-coupon-locking")
        Thread.sleep(2000)
    }
}
