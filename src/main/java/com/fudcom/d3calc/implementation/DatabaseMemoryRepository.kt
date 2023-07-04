package com.fudcom.d3calc.implementation

import com.fudcom.d3calc.core.MemoryRepository
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils

class DatabaseMemoryRepository: MemoryRepository {

    // -- For demo purposes, this should come from a configuration file.
    private val connection = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

    init {
        SchemaUtils.create (Memories)
    }

    override fun store(number: Double) {
        Memory.new {
            value = number
        }
    }

    override fun recall(): Double {
        return Memory.all().lastOrNull()?.let {
            val value = it.value
            it.delete()
            value
        } ?: 0.0
    }
}

object Memories: IntIdTable() {
    val value = double("value" )
}

class Memory(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Memory>(Memories)
    var value by Memories.value
}