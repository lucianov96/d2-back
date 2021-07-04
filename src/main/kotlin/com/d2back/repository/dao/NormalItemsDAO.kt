package com.d2back.repository.dao

import com.d2back.repository.model.NormalItem
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface NormalItemsDAO : MongoRepository<NormalItem, Int> {

    override fun findById(id: Int): Optional<NormalItem>
    fun findByName(name: String): NormalItem?
}
