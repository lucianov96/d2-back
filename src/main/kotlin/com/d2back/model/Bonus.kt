package com.d2back.model

import com.d2back.model.enum.BonusType
import com.d2back.model.enum.Key
import com.d2back.model.enum.Key.cold_absorb
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.envers.Audited
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Table(
    name = "bonus",
)
@Entity
@Audited
@DynamicUpdate
class Bonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bonus", updatable = false, nullable = false)
    var id: Int = 0

    @Column(name = "key", updatable = true)
    @Enumerated(EnumType.STRING)
    var key: Key = cold_absorb

    @Column(name = "objects", updatable = true)
    var objects: Int? = null

    @Column(name = "bonus_type", updatable = true)
    @Enumerated(EnumType.STRING)
    var bonusType: BonusType = BonusType.unique

    @ManyToOne
    @JoinColumn(name = "id_set_item", referencedColumnName = "id_item", nullable = true, updatable = false)
    var setItem: SetItem? = null

    @ManyToOne
    @JoinColumn(name = "id_unique_item", referencedColumnName = "id_item", nullable = true)
    var uniqueItem: UniqueItem? = null

    @ManyToOne
    @JoinColumn(name = "runeword", referencedColumnName = "id_item", nullable = true)
    var runeword: Runeword? = null

    @OneToMany(mappedBy = "bonus", cascade = [CascadeType.ALL])
    var modifierBonuses: List<ModifierBonus> = mutableListOf()

    var number: Long? = null
}
