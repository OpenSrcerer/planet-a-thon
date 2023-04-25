package xyz.danielstefani.patmobile.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * A class that represents a page of cartoons.
 *
 * @property cartoons The cartoons on the page.
 * @property totalPages The total number of pages of cartoons.
 * @property totalElements The total number of cartoons across all pages.
 * @property number The page number.
 * @property first Whether or not this is the first page.
 * @property last Whether or not this is the last page.
 * @property empty Whether or not the page is empty.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class CartoonPage {

    // The cartoons on the page.
    @JsonProperty("content")
    var cartoons: List<Cartoon>? = null

    // The total number of pages of cartoons.
    var totalPages: Int? = null

    // The total number of cartoons across all pages.
    var totalElements: Int? = null

    // The page number.
    var number: Int? = null

    // Whether or not this is the first page.
    var first: Boolean? = null

    // Whether or not this is the last page.
    var last: Boolean? = null

    // Whether or not the page is empty.
    var empty: Boolean? = null

    companion object {

        /**
         * @return An empty CartoonPage object.
         */
        fun empty(): CartoonPage {
            return CartoonPage().also {
                it.cartoons = listOf()
            }
        }
    }
}