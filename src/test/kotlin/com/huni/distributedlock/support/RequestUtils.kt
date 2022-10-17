package com.huni.distributedlock.support

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.web.client.RestTemplate
import java.util.concurrent.CyclicBarrier

class RequestUtils {

    companion object {

        private val REST_TEMPLATE = create()

        private fun create(): RestTemplate {
            return RestTemplateBuilder().build()
        }

        fun post(
            uri: String,
            vararg uriVariables: Any
        ) {
            REST_TEMPLATE.postForObject(uri, null, Void::class.java, *uriVariables)
        }

        fun concurrentPost(
            threadCount: Int,
            uri: String,
            vararg uriVariables: Any?
        ) {
            val barrier = CyclicBarrier(threadCount)
            for (i in 0 until threadCount) {
                Thread {
                    try {
                        barrier.await()
                        post(uri, uriVariables)
                    } catch (e: Exception) {
                        throw RuntimeException(e)
                    }
                }.start()
            }
        }
    }
}
