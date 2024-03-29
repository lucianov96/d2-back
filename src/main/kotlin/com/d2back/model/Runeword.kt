package com.d2back.model

import com.d2back.model.enums.ItemType
import com.d2back.model.enums.ItemType.amazon_bow
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.OneToMany
import javax.persistence.Table

@Table(
    name = "runeword",
)
@Entity
class Runeword : MagicItem() {

    @Column(name = "item_type", updatable = true)
    @Enumerated(EnumType.STRING)
    var type: ItemType = amazon_bow

    @Column(name = "runes", updatable = true)
    var runes: String = ""

    @OneToMany(mappedBy = "runeword", cascade = [CascadeType.ALL])
    var bonuses: List<Bonus> = mutableListOf()
}
