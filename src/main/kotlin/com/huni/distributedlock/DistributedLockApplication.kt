package com.huni.distributedlock

import com.huni.distributedlock.domain.User
import com.huni.distributedlock.domain.UserRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@SpringBootApplication
class DistributedLockApplication

fun main(args: Array<String>) {
    runApplication<DistributedLockApplication>(*args)
}

@Component
class Initializer(
    private val userRepository: UserRepository
) {
    @PostConstruct
    fun initialize() {
        userRepository.save(User("huni"))
        userRepository.save(User("hoho"))
    }
}
