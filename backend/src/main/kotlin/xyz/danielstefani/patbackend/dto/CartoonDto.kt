package xyz.danielstefani.patbackend.dto

data class CartoonDto(
    val name: String,
    val age: Int,
    val gender: String,
    val show: String,
    val description: String,
    val color: String,
    val likes: Long
)