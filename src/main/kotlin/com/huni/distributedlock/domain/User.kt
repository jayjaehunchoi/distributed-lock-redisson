package com.huni.distributedlock.domain

import javax.persistence.*

@Entity
@Table(name = "users")
class User(

    @Column(nullable = false)
    val name: String,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val coupons: MutableSet<Coupon> = mutableSetOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {

    fun addCoupon(coupon: Coupon) {
        require(coupons.size < MAX_COUPON_SIZE) {"최대 쿠폰 수를 초과했습니다."}
        coupon.user = this
        this.coupons.add(coupon)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    companion object {
        private val MAX_COUPON_SIZE = 2
    }
}
