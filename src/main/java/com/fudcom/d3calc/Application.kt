package com.fudcom.d3calc

import com.fudcom.d3calc.core.Calculator
import com.fudcom.d3calc.implementation.RESTAdapter
import com.fudcom.d3calc.implementation.SingleValueInRAMMemoryRepository

class Application {

    private val calculator = Calculator(SingleValueInRAMMemoryRepository())

    private val restAdapter = RESTAdapter(calculator)

    init {
        // -- Start REST Adapter and wait forever.
        restAdapter.start()
    }

}

fun main() {
    Application()
}
