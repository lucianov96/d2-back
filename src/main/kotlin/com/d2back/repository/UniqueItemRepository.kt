package com.d2back.repository

import com.d2back.model.UniqueItem
import com.d2back.model.enums.ItemType
import com.d2back.model.enums.Key
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UniqueItemRepository : JpaRepository<UniqueItem, Int>, JpaSpecificationExecutor<UniqueItem> {
    @Query(value = "SELECT coalesce(max(id), 0) FROM UniqueItem")
    fun getMaxNumber(): Int

    @Query(
        "SELECT u FROM UniqueItem u WHERE " +
        "(:name = null or u.name LIKE %:name%) AND " +
        "(:type = null or u.normalItem.type = :type) AND " +
        "(COALESCE(:keys, null) = null or " +
        "    (SELECT count(b) FROM u.bonuses b WHERE b.key IN (:keys)) = :keysCount)"
    )
    fun findByFilter(
        @Param("name") name: String?,
        @Param("keys") keys: List<Key>?,
        @Param("keysCount") keysCount: Long = keys?.size?.toLong() ?: 0L,
        @Param("type") type: ItemType?,
        pageable: Pageable
    ): Page<UniqueItem>
}
