package com.fudcom.d3calc.implementation

import com.fudcom.d3calc.core.MemoryRepository

class SingleValueInRAMMemoryRepository: MemoryRepository {

    private var value: Double = 0.0

    override fun store(number: Double) {
        value = number
    }

    override fun recall(): Double = value

}