package com.d2back.model

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Table(
    name = "set",
)
@Entity
class Set {

    @Id
    @Column(name = "id_set", updatable = false, nullable = false)
    var id: Int = 0

    @Column(name = "set_name", updatable = true)
    var name: String = ""

    @OneToMany(mappedBy = "set", cascade = [CascadeType.ALL])
    var setItems: List<SetItem>? = null

    @OneToMany(mappedBy = "set", cascade = [CascadeType.ALL])
    var bonuses: List<Bonus> = mutableListOf()
}
