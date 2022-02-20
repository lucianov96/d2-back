package com.d2back.model

import org.hibernate.annotations.DynamicUpdate
import org.hibernate.envers.Audited
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Table(
    name = "set_item",
)
@Entity
@Audited
@DynamicUpdate
class SetItem : MagicItem() {

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "normal_item_id", referencedColumnName = "id_item")
    var normalItem: NormalItem = NormalItem()

    @ManyToOne
    @JoinColumn(name = "id_set")
    var set: Set = Set()

    @OneToMany(mappedBy = "setItem", cascade = [CascadeType.ALL])
    var bonuses: List<Bonus> = mutableListOf()
}
