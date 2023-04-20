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

@Service
class CartoonService(
    private val cartoonRepository: CartoonRepository
) {
    fun getCartoons(pageable: Pageable): Page<Cartoon> {
        return cartoonRepository.findAll(pageable)
    }

    fun getCartoonById(uuid: UUID): Optional<Cartoon> {
        return cartoonRepository.findById(uuid)
    }

    fun createCartoon(cartoonDto: CartoonDto): Cartoon {
        return cartoonRepository.save(Cartoon(cartoonDto))
    }

    fun patchCartoon(cartoonDto: PartialCartoonDto): Cartoon {
        val oldCartoon = cartoonRepository.findById(cartoonDto.id!!)

        if (oldCartoon.isEmpty) {
            throw CartoonNotFoundException()
        }

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

    fun deleteCartoonById(uuid: UUID) {
        val oldCartoon = cartoonRepository.findById(uuid)
        if (oldCartoon.isEmpty) {
            throw GenericHttpException(HttpStatus.NOT_FOUND, "The Cartoon with the provided ID does not exist.")
        }
        cartoonRepository.deleteById(uuid)
    }
}