package com.d2back.repository

import com.d2back.model.NormalItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface NormalItemsRepository : JpaRepository<NormalItem, Int> {

    override fun findById(id: Int): Optional<NormalItem>
    fun findByName(name: String): NormalItem?
}
