package com.d2back.repository

import com.d2back.model.Runeword
import com.d2back.model.enums.Key
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RunewordRepository : JpaRepository<Runeword, Int>, JpaSpecificationExecutor<Runeword> {
    @Query(value = "SELECT coalesce(max(id), 0) FROM Runeword")
    fun getMaxNumber(): Int
    fun findAllByNameLikeAndBonuses_KeyIn(name: String, keys: List<Key>, pageable: Pageable): Page<Runeword>
    fun findAllByBonuses_KeyIn(keys: List<Key>, pageable: Pageable): Page<Runeword>
    fun findAllByNameLike(name: String, pageable: Pageable): Page<Runeword>
}
