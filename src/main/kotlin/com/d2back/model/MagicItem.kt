package com.d2back.model

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@EntityListeners(
    value = [
        AuditingEntityListener::class
    ]
)
@MappedSuperclass
abstract class MagicItem {

    @Id
    @Column(name = "id_item", updatable = false, nullable = false)
    var id: Int = 0

    @Column(name = "item_name", updatable = true, nullable = false)
    var name: String = ""

    @Column(name = "level", updatable = true, nullable = false)
    var level: Int = 0
}
