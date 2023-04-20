package xyz.danielstefani.patbackend.controller

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.danielstefani.patbackend.dto.CartoonDto
import xyz.danielstefani.patbackend.dto.PartialCartoonDto
import xyz.danielstefani.patbackend.exception.CartoonNotFoundException
import xyz.danielstefani.patbackend.exception.GenericHttpException
import xyz.danielstefani.patbackend.model.Cartoon
import xyz.danielstefani.patbackend.service.CartoonService
import java.util.*

/**
 * This code defines a Spring REST controller for handling requests
 * related to cartoon CRUD operations.
 */
@RestController // The @RestController annotation marks the class as a controller with built-in JSON response handling.
@RequestMapping("/cartoons") // The @RequestMapping("/cartoons") specifies the base URL for the controller.
class CartoonController(
    private val cartoonService: CartoonService // Dependency injected cartoon service
) {
    /**
     * getCartoons() handles a GET request for retrieving all cartoons.
     * @param pageable A Pageable parameter, which is a Spring Data interface for pagination and sorting.
     * @return A Page of Cartoon objects.
     */
    @GetMapping
    fun getCartoons(pageable: Pageable): Page<Cartoon> {
        return cartoonService.getCartoons(pageable)
    }

    /**
     * getCartoon() handles a GET request for retrieving a single cartoon by its UUID.
     * @param uuid It takes a UUID path variable.
     * @return A single cartoon if found, or an HTTP 404 status otherwise.
     */
    @GetMapping("/{uuid}")
    fun getCartoon(
        @PathVariable uuid: UUID
    ): Cartoon {
        return cartoonService.getCartoonById(uuid)
            .orElseThrow { CartoonNotFoundException() }
    }

    /**
     * createCartoon() handles a POST request for creating a new cartoon.
     * @param cartoonDto It takes a CartoonDto request body
     * @returns A ResponseEntity with the created Cartoon object and a Created (201) status code.
     */
    @PostMapping
    fun createCartoon(
        @RequestBody cartoonDto: CartoonDto
    ): ResponseEntity<Cartoon> {
        val createdCartoon = cartoonService.createCartoon(cartoonDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCartoon)
    }

    /**
     * updateCartoon() handles a PATCH request for partially updating an existing cartoon.
     * @param partialCartoonDto It takes a PartialCartoonDto request body.
     * @returns The updated Cartoon object. If the id field is null, it throws a custom GenericHttpException with a BAD_REQUEST status code.
     */
    @PatchMapping
    fun updateCartoon(
        @RequestBody partialCartoonDto: PartialCartoonDto
    ): Cartoon {
        if (partialCartoonDto.id == null) {
            throw GenericHttpException(HttpStatus.BAD_REQUEST, "You have provided an empty or invalid 'id' field.")
        }
        return cartoonService.patchCartoon(partialCartoonDto)
    }

    /**
     * deleteCartoon() handles a DELETE request for deleting a single cartoon by its UUID.
     * @param uuid It takes a UUID path variable.
     * @returns A ResponseEntity with a NO_CONTENT status code.
     */
    @DeleteMapping("/{uuid}")
    fun deleteCartoon(
        @PathVariable uuid: UUID
    ): ResponseEntity<Void> {
        cartoonService.deleteCartoonById(uuid)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}