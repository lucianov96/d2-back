package com.d2back.repository

import com.d2back.model.SetItem
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
interface SetItemRepository : JpaRepository<SetItem, Int>, JpaSpecificationExecutor<SetItem> {
    @Query(value = "SELECT coalesce(max(id), 0) FROM SetItem")
    fun getMaxNumber(): Int

    @Query(
        "SELECT s FROM SetItem s WHERE " +
        "(:name = null or s.name LIKE %:name%) AND " +
        "(:type = null or s.normalItem.type = :type) AND " +
        "(:set = null or s.set.name = :set) AND " +
        "(COALESCE(:keys, null) = null or " +
        "    (SELECT count(b) FROM s.bonuses b WHERE b.key IN (:keys)) = :keysCount)"
    )
    fun findByFilter(
        @Param("name") name: String?,
        @Param("keys") keys: List<Key>?,
        @Param("keysCount") keysCount: Long = keys?.size?.toLong() ?: 0L,
        @Param("type") type: ItemType?,
        @Param("set") set: String?,
        pageable: Pageable
    ): Page<SetItem>
}
