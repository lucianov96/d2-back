package com.d2back.repository

import com.d2back.model.Set
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SetRepository : JpaRepository<Set, Int>, JpaSpecificationExecutor<Set> {
    @Query(value = "SELECT coalesce(max(id), 0) FROM Set")
    fun getMaxNumber(): Int
}
