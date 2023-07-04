package com.fudcom.d3calc.implementation

import com.fudcom.d3calc.core.Calculator
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*

class RESTAdapter(calculator: Calculator) {

    val server = embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)

    // -- This is a side effect of how KTOR applications are designed, I wanted to encapsulate KTOR in an adapter.
    init {
        server.application.calculator = calculator
    }

    fun start() {
        server.start(wait = true)
    }

}

val CALCULATOR_ATTRIBUTE_KEY = AttributeKey<Calculator>("calculator")

var Application.calculator: Calculator
    get() {
        return attributes[CALCULATOR_ATTRIBUTE_KEY]
    }
    set(value) {
        attributes.put(CALCULATOR_ATTRIBUTE_KEY, value)
    }

fun Application.module() {
    configureRouting()
}

fun Application.configureRouting() {
    routing {
        get {
            call.respondText(this@configureRouting.calculator.result().toString(), ContentType.Text.Html)
        }
        post("/add/{number?}") {
            call.computeAndRespond("number") { number -> application.calculator.add(number) }
        }
        post("/sub/{number?}") {
            call.computeAndRespond("number") { number -> application.calculator.sub(number) }
        }
        post("/mul/{number?}") {
            call.computeAndRespond("number") { number -> application.calculator.mul(number) }
        }
        post("/div/{number?}") {
            call.computeAndRespond("number") { number -> application.calculator.div(number) }
        }
        post("/ac") {
            val result = application.calculator.allClear()
            call.respondText(result.toString(), ContentType.Text.Html)
        }
        post("/ms") {
            val result = application.calculator.memoryStore()
            call.respondText(result.toString(), ContentType.Text.Html)
        }
        post("/mr") {
            val result = application.calculator.memoryRecall()
            call.respondText(result.toString(), ContentType.Text.Html)
        }
    }
}

suspend fun ApplicationCall.computeAndRespond(callParameterName: String, operation: (Double) -> Double) {
    val number = parameters[callParameterName] ?: return respondText("Missing number", status = HttpStatusCode.BadRequest )
    try {
        val result = operation(number.toDouble())
        respondText(result.toString(), ContentType.Text.Html)
    } catch( e: NumberFormatException ) {
        return respondText("Not a number", status = HttpStatusCode.BadRequest )
    }
}


