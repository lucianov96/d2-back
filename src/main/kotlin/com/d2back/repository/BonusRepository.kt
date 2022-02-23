package com.d2back.repository

import com.d2back.model.Bonus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BonusRepository : JpaRepository<Bonus, Int> {

    @Query(value = "SELECT coalesce(max(number), 0) FROM Bonus")
    fun getMaxNumber(): Long
}
