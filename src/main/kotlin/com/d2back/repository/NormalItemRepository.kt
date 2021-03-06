package com.d2back.repository

import com.d2back.model.NormalItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface NormalItemRepository : JpaRepository<NormalItem, Int>, JpaSpecificationExecutor<NormalItem>
