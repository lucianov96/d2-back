package com.d2back.service

import com.d2back.repository.NormalItemsRepository
import org.springframework.stereotype.Service

@Service
class ItemService(
    val normalItemsRepository: NormalItemsRepository
) {

}
