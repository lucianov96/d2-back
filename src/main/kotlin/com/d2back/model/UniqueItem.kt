package com.d2back.model

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Table(
    name = "unique_item",
)
@Entity
class UniqueItem : MagicItem() {

    @OneToOne
    @JoinColumn(name = "normal_item_id", referencedColumnName = "id_item")
    var normalItem: NormalItem = NormalItem()

    @OneToMany(mappedBy = "uniqueItem", cascade = [CascadeType.ALL])
    var bonuses: List<Bonus> = mutableListOf()
}
