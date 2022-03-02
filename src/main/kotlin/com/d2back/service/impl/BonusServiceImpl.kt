package com.d2back.service.impl

import com.d2back.repository.BonusRepository
import com.d2back.service.BonusService
import org.springframework.stereotype.Service

@Service
class BonusServiceImpl(
    val bonusRepository: BonusRepository
) : BonusService {
    override fun getMaxNumber(): Int {
        return bonusRepository.getMaxNumber()
    }
}
