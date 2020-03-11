package versionHub

import versionHub.routes.patch
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import versionHub.model.Applications
import versionHub.model.Versions

fun main() {
    Database.connect("jdbc:h2:mem:veresionHub", driver = "org.h2.Driver")

    transaction {
        SchemaUtils.create(Applications, Versions)
    }

    embeddedServer(Netty, port = 8080, module = Application::mainModule).start(wait = true)
}

fun Application.mainModule() {
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    routing {
        patch()
    }
}
