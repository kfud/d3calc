package com.fudcom.d3calc.core

interface MemoryRepository {

    fun store(number: Double)

    fun recall(): Double

}