package versionHub.model

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Table
import java.util.*

object Applications : UUIDTable() {
    val name = varchar("name", 265)
}

class Application(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Application>(Applications)

    val name by Applications.name
    val versions by Version referrersOn Versions.application
}