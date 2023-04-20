package xyz.danielstefani.patbackend.dto

/**
 * Cartoon Data Transfer Object, to be used as an intermediate package.
 */
data class CartoonDto(
    val name: String,         // Name of the cartoon character
    val age: Int,             // Age of the cartoon character
    val gender: String,       // Gender of the cartoon character
    val show: String,         // Name of the cartoon show
    val description: String,  // Description of the character
    val color: String,        // Color of the character
    val likes: Long           // Number of likes the character has received
)