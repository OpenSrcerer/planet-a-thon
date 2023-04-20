package xyz.danielstefani.patbackend.dto

import java.util.*

/**
 * Similar to the Cartoon DTO, but with optional properties. We use this for the
 * update operation.
 */
class PartialCartoonDto {
    var id: UUID? = null                 // Unique ID of the character
    var name: String? = null             // Name of the cartoon character
    var age: Int? = null                 // Age of the cartoon character
    var gender: String? = null           // Gender of the cartoon character
    var show: String? = null             // Name of the cartoon show
    var description: String? = null      // Description of the character
    var color: String? = null            // Color of the character
    var likes: Long? = null              // Number of likes the character has received
}