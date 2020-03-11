package versionHub.model

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Table
import java.util.*

object Versions : UUIDTable() {
    val major = varchar("major", 20)
    val minor = varchar("minor", 20)
    val patch = varchar("patch", 20)
    val application = reference("application", Applications)
}

class Version(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Version>(Versions)

    var major by Versions.major
    var minor by Versions.minor
    var patch by Versions.patch
    var application by Application referencedOn Versions.application

}