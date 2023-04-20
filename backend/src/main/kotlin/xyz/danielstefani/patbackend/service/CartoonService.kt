package xyz.danielstefani.patbackend.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import xyz.danielstefani.patbackend.dto.CartoonDto
import xyz.danielstefani.patbackend.dto.PartialCartoonDto
import xyz.danielstefani.patbackend.exception.CartoonNotFoundException
import xyz.danielstefani.patbackend.exception.GenericHttpException
import xyz.danielstefani.patbackend.model.Cartoon
import xyz.danielstefani.patbackend.repository.CartoonRepository
import java.util.*

/**
 * This class is the service layer for the Cartoon resource.
 */
@Service
class CartoonService(
    // The class requires an instance of the CartoonRepository to interact with the database.
    private val cartoonRepository: CartoonRepository
) {
    // Retrieves all cartoons in the database with pagination.
    fun getCartoons(pageable: Pageable): Page<Cartoon> {
        return cartoonRepository.findAll(pageable)
    }

    // Retrieves a single cartoon with the provided ID.
    fun getCartoonById(uuid: UUID): Optional<Cartoon> {
        return cartoonRepository.findById(uuid)
    }

    // Creates a new cartoon in the database with the provided data.
    fun createCartoon(cartoonDto: CartoonDto): Cartoon {
        return cartoonRepository.save(Cartoon(cartoonDto))
    }

    // This function updates an existing Cartoon object with a subset of its original properties.
    fun patchCartoon(cartoonDto: PartialCartoonDto): Cartoon {
        // Retrieve the original cartoon object from the database.
        val oldCartoon = cartoonRepository.findById(cartoonDto.id!!)

        // If the original cartoon doesn't exist, throw an exception.
        if (oldCartoon.isEmpty) {
            throw CartoonNotFoundException()
        }

        // Update the relevant properties of the original cartoon and save it to the database.
        val updatedCartoon = oldCartoon.get().also {
            cartoonDto.name.apply { if (this != null) it.name = this }
            cartoonDto.age.apply { if (this != null) it.age = this }
            cartoonDto.gender.apply { if (this != null) it.gender = this }
            cartoonDto.show.apply { if (this != null) it.show = this }
            cartoonDto.description.apply { if (this != null) it.description = this }
            cartoonDto.color.apply { if (this != null) it.color = this }
            cartoonDto.likes.apply { if (this != null) it.likes = this }
        }

        return cartoonRepository.save(updatedCartoon)
    }

    // Deletes the cartoon with the provided ID from the database.
    fun deleteCartoonById(uuid: UUID) {
        // Check if the cartoon with the provided ID exists in the database.
        val oldCartoon = cartoonRepository.findById(uuid)
        if (oldCartoon.isEmpty) {
            // Throw an exception if the cartoon does not exist.
            throw GenericHttpException(HttpStatus.NOT_FOUND, "The Cartoon with the provided ID does not exist.")
        }
        // Otherwise, delete the cartoon from the database.
        cartoonRepository.deleteById(uuid)
    }
}