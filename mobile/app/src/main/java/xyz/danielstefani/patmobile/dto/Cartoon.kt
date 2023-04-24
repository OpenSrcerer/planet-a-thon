package xyz.danielstefani.patmobile.dto

import java.util.*

class Cartoon {
    var id: UUID? = null                 // Unique ID of the character
    var name: String? = null             // Name of the cartoon character
    var age: Int? = null                 // Age of the cartoon character
    var gender: String? = null           // Gender of the cartoon character
    var show: String? = null             // Name of the cartoon show
    var description: String? = null      // Description of the character
    var color: String? = null            // Color of the character
    var likes: Long? = null              // Number of likes the character has received
}