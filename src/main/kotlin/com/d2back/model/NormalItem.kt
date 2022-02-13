package com.d2back.model

import com.d2back.model.enum.CharacterClass
import com.d2back.model.enum.Difficulty
import com.d2back.model.enum.Difficulty.normal
import com.d2back.model.enum.ItemType
import com.d2back.model.enum.ItemType.crossbow
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.envers.Audited
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Table(
    name = "normal_item",
)
@Entity
@Audited
@DynamicUpdate
class NormalItem {

    @Id
    @Column(name = "id_item", updatable = false, nullable = false)
    var id: Int = 0

    @Column(name = "normal_item_name", updatable = true)
    var name: String = ""

    @Column(name = "item_type", updatable = true)
    @Enumerated(EnumType.STRING)
    var type: ItemType = crossbow

    @Column(name = "level", updatable = true)
    var level: Int = 0

    @Column(name = "durability", updatable = true)
    var durability: Int = 0

    @Column(name = "character_class", updatable = true)
    @Enumerated(EnumType.STRING)
    var characterClass: CharacterClass? = null

    @Column(name = "difficulty", updatable = true)
    @Enumerated(EnumType.STRING)
    var difficulty: Difficulty = normal

    @OneToOne(mappedBy = "normalItem")
    var setItem: SetItem? = null

    @OneToOne(mappedBy = "normalItem")
    var uniqueItem: UniqueItem? = null

    @OneToMany(mappedBy = "normalItem", cascade = [CascadeType.ALL])
    var modifierBonuses: List<ModifierBonus> = mutableListOf()

}
