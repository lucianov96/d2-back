package com.d2back.model

import com.d2back.model.enum.ItemType
import com.d2back.model.enum.ItemType.amazon_bow
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.envers.Audited
import javax.persistence.*

@Table(
    name = "runeword",
)
@Entity
@Audited
@DynamicUpdate
class Runeword: MagicItem() {

    @Column(name = "item_type", updatable = true)
    @Enumerated(EnumType.STRING)
    var type: ItemType = amazon_bow

    @Column(name = "runes", updatable = true)
    var runes: String = ""

    @OneToMany(mappedBy = "runeword", cascade = [CascadeType.ALL])
    var bonuses: List<Bonus> = mutableListOf()

}
