package com.d2back.repository

import com.d2back.model.SetItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SetItemRepository : JpaRepository<SetItem, Int>, JpaSpecificationExecutor<SetItem> {
    @Query(value = "SELECT coalesce(max(id), 0) FROM SetItem")
    fun getMaxNumber(): Int
}
