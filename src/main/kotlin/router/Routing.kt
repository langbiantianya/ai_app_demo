package com.example.router

import io.ktor.server.application.*
import io.ktor.server.http.content.staticResources
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        staticResources("/", "static"){

        }
    }
}
