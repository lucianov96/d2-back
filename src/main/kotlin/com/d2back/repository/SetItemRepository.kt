package com.d2back.repository

import com.d2back.model.SetItem
import com.d2back.model.enums.Key
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SetItemRepository : JpaRepository<SetItem, Int>, JpaSpecificationExecutor<SetItem> {
    @Query(value = "SELECT coalesce(max(id), 0) FROM SetItem")
    fun getMaxNumber(): Int
    fun findAllByNameLikeAndBonuses_KeyIn(name: String, keys: List<Key>, pageable: Pageable): Page<SetItem>
    fun findAllByBonuses_KeyIn(keys: List<Key>, pageable: Pageable): Page<SetItem>
    fun findAllByNameLike(name: String, pageable: Pageable): Page<SetItem>
}
