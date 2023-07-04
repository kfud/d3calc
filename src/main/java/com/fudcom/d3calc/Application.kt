package com.fudcom.d3calc

import com.fudcom.d3calc.core.Calculator
import com.fudcom.d3calc.implementation.DatabaseMemoryRepository
import com.fudcom.d3calc.implementation.RESTAdapter
import com.fudcom.d3calc.implementation.SingleValueInRAMMemoryRepository

class Application {

    // -- Choose calculator memory implementation:
//    private val calculator = Calculator(SingleValueInRAMMemoryRepository())
    private val calculator = Calculator(DatabaseMemoryRepository())

    // -- Other adapters can be created as additional integrations are needed.
    private val restAdapter = RESTAdapter(calculator)

    init {
        // -- Start REST Adapter and wait forever.
        restAdapter.start()
    }

}

fun main() {
    Application()
}
