package com.d2back.repository

import com.d2back.model.UniqueItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UniqueItemRepository : JpaRepository<UniqueItem, Int>, JpaSpecificationExecutor<UniqueItem> {
    @Query(value = "SELECT coalesce(max(id), 0) FROM UniqueItem")
    fun getMaxNumber(): Int
}
