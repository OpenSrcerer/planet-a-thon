package xyz.danielstefani.patmobile.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class CartoonPage {
    @JsonProperty("content")
    var cartoons: List<Cartoon>? = null

    var totalPages: Int? = null

    var totalElements: Int? = null

    var number: Int? = null

    var first: Boolean? = null

    var last: Boolean? = null

    var empty: Boolean? = null

    companion object {
        fun empty(): CartoonPage {
            return CartoonPage().also {
                it.cartoons = listOf()
            }
        }
    }
}