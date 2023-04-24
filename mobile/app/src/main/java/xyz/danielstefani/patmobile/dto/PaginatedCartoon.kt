package xyz.danielstefani.patmobile.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class PaginatedCartoon {
    @JsonProperty("content")
    var content: List<Cartoon>? = null
}