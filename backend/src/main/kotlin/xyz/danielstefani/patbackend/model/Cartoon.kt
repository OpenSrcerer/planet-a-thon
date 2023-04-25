package xyz.danielstefani.patbackend.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import xyz.danielstefani.patbackend.dto.CartoonDto
import java.util.*


/**
 * This is an example of how to create a "model".
 * A model is a Java class which shows the structure of the table in the database.
 *
 * You can create instances of these objects, and each one represents one row in the database.
 */
@Entity
@Table(
    name = "cartoons",
    indexes = [Index(name = "index_id_cartoon_name", columnList = "cartoon_id, cartoon_name", unique = true)]
)
class Cartoon(cartoonDto: CartoonDto?) {

    constructor() : this(null)

    @Id
    @Column(name = "cartoon_id", nullable = false)
    var id = UUID.randomUUID()

    @Column(name = "cartoon_name", nullable = false)
    var name: String? = ""

    @Column(name = "cartoon_age", nullable = false)
    var age: Int = 0

    @Column(name = "cartoon_gender", nullable = false)
    var gender: String = ""

    @Column(name = "cartoon_show", nullable = false)
    var show: String = ""

    @Column(name = "cartoon_description", nullable = false)
    var description: String = ""

    @Column(name = "cartoon_color", nullable = false)
    var color: String = ""

    @Column(name = "cartoon_likes", nullable = false)
    var likes: Long = 0L

    init {
        if (cartoonDto != null) {
            this.name = cartoonDto.name
            this.age = cartoonDto.age
            this.gender = cartoonDto.gender
            this.show = cartoonDto.show
            this.description = cartoonDto.description
            this.color = cartoonDto.color
            this.likes = cartoonDto.likes
        }
    }
}