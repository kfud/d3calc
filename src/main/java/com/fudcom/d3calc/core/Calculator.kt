package com.fudcom.d3calc.core

class Calculator(private val memory: MemoryRepository) {

    private var result: Double = 0.0

    fun add(number: Double): Double {
        result += number
        return result
    }

    fun sub(number: Double): Double {
        result -= number
        return result
    }

    fun mul(number: Double): Double {
        result *= number
        return result
    }

    fun div(number: Double): Double {
        result /= number
        return result
    }

    fun allClear(): Double {
        result = 0.0
        return result
    }

    fun memoryStore(): Double {
        memory.store(result)
        return result
    }

    fun memoryRecall(): Double {
        result = memory.recall()
        return result
    }

    fun result() = result

}