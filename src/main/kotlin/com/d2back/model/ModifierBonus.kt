package com.d2back.model

import com.d2back.model.enums.ModifierType
import com.d2back.model.enums.ModifierType.absolute
import com.d2back.model.enums.values.MagicItemValue
import com.d2back.model.enums.values.NormalItemValue
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GenerationType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Table(
    name = "modifier_bonus",
)
@Entity
class ModifierBonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modifier_bonus", updatable = false, nullable = false)
    var id: Int = 0

    @Column(name = "modifier_type", updatable = true)
    @Enumerated(EnumType.STRING)
    var modifierType: ModifierType = absolute

    @Column(name = "between_value_1", updatable = true)
    var betweenValue1: Int? = null

    @Column(name = "between_value_2", updatable = true)
    var betweenValue2: Int? = null

    @Column(name = "absolute_int_value", updatable = true)
    var absoluteIntValue: Int? = null

    // This value is used only for skills
    @Column(name = "absolute_string_value", updatable = true)
    var absoluteStringValue: String? = null

    @Column(name = "normal_item_value", updatable = true)
    @Enumerated(EnumType.STRING)
    var normalItemValue: NormalItemValue? = null

    @Column(name = "magic_item_value", updatable = true)
    @Enumerated(EnumType.STRING)
    var magicItemValue: MagicItemValue? = null

    @ManyToOne
    @JoinColumn(name = "id_bonus", nullable = true)
    var bonus: Bonus? = null

    @ManyToOne
    @JoinColumn(name = "id_item", nullable = true)
    var normalItem: NormalItem? = null
}
