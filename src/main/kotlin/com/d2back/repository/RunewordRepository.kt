package com.d2back.repository

import com.d2back.model.Runeword
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RunewordRepository : JpaRepository<Runeword, Int>, JpaSpecificationExecutor<Runeword> {
    @Query(value = "SELECT coalesce(max(id), 0) FROM Runeword")
    fun getMaxNumber(): Int
}
