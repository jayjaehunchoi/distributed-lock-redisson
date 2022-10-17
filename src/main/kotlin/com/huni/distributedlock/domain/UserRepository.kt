package com.huni.distributedlock.domain

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository : JpaRepository<User, Long> {

    @Query(
        """
            SELECT DISTINCT u FROM User u 
             LEFT JOIN FETCH u.coupons 
             WHERE u.id = :userId 
        """
    )
    fun findByIdWithCoupons(@Param("userId")id: Long?) : User?
}
