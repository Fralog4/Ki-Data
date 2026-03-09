package com.app.Ki_Data.model

import jakarta.persistence.DiscriminatorColumn
import jakarta.persistence.DiscriminatorType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
@DiscriminatorColumn(name = "character_type", discriminatorType = DiscriminatorType.STRING)
abstract class EntityBase(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Int =0,
    open var name : String? = null,
    open var race : String ? = null,
    open var gender : String? = null,
    open var birthDate : Int =0,
    open var description: String? = null
)