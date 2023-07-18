package com.d2back.repository

import com.d2back.model.Runeword
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
interface RunewordRepository : JpaRepository<Runeword, Int>, JpaSpecificationExecutor<Runeword> {
    @Query(value = "SELECT coalesce(max(id), 0) FROM Runeword")
    fun getMaxNumber(): Int
    @Query(
        "SELECT r FROM Runeword r WHERE " +
            "(:name = null or r.name LIKE %:name%) AND " +
            "(:type = null or r.type = :type) AND " +
            "(:rune1 = null or r.runes LIKE %:rune1%) AND " +
            "(:rune2 = null or r.runes LIKE %:rune2%) AND " +
            "(:rune3 = null or r.runes LIKE %:rune3%) AND " +
            "(:rune4 = null or r.runes LIKE %:rune4%) AND " +
            "(:rune5 = null or r.runes LIKE %:rune5%) AND " +
            "(:rune6 = null or r.runes LIKE %:rune6%) AND " +
            "(COALESCE(:keys, null) = null or " +
            "    (SELECT count(b) FROM r.bonuses b WHERE b.key IN (:keys)) = :keysCount)"

    )
    fun findByFilter(
        @Param("name") name: String?,
        @Param("keys") keys: List<Key>?,
        @Param("keysCount") keysCount: Long = keys?.size?.toLong() ?: 0L,
        @Param("type") type: ItemType?,
        @Param("rune1") rune1: String?,
        @Param("rune2") rune2: String?,
        @Param("rune3") rune3: String?,
        @Param("rune4") rune4: String?,
        @Param("rune5") rune5: String?,
        @Param("rune6") rune6: String?,
        pageable: Pageable
    ): Page<Runeword>
}
