package com.huni.distributedlock.lock

import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class LockManager(
    private val redissonClient: RedissonClient
) {

    fun <T> executeWithLock(userKey: String, dataType: String, executor: () -> T) : T{
        val lock = getLock(userKey, dataType)
        try {
            return executor.invoke()
        } finally {
            releaseLock(lock)
        }
    }

    private fun getLock(userKey: String, dataType: String) : RLock {
        val lock = redissonClient.getLock("$dataType:$userKey")
        val isLock = lock.tryLock(5, 7, TimeUnit.SECONDS)
        validateLock(isLock)
        return lock
    }

    private fun validateLock(isLock: Boolean) {
        require(isLock) {"LOCK 획득 실패"}
    }

    private fun releaseLock(lock: RLock) {
        if (lock.isLocked || lock.isHeldByCurrentThread) {
            lock.unlock()
        }
    }
}
