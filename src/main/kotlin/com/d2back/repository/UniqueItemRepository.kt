package com.d2back.repository

import com.d2back.model.UniqueItem
import com.d2back.model.enums.Key
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UniqueItemRepository : JpaRepository<UniqueItem, Int>, JpaSpecificationExecutor<UniqueItem> {
    @Query(value = "SELECT coalesce(max(id), 0) FROM UniqueItem")
    fun getMaxNumber(): Int
    fun findAllByNameLikeAndBonuses_KeyIn(name: String, keys: List<Key>, pageable: Pageable): Page<UniqueItem>
    fun findAllByBonuses_KeyIn(keys: List<Key>, pageable: Pageable): Page<UniqueItem>
    fun findAllByNameLike(name: String, pageable: Pageable): Page<UniqueItem>
}
