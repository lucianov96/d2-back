package com.d2back.repository

import com.d2back.model.ModifierBonus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ModifierBonusRepository : JpaRepository<ModifierBonus, Int>
