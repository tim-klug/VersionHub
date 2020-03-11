package versionHub.routes

import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

fun Route.patch() {
    route("patch/{id}") {
        get {
            val id = call.parameters["id"].toString()
            call.respondText("for $id I found version: 1")
        }
    }

}