package xyz.danielstefani.patmobile.client.cartoon

enum class CartoonHttpEndpoint(
    val location: String,
    val method: String = ""
) {
    CARTOON_BASE_PATH("cartoons"),

    GET_ALL_CARTOONS("", "GET"),
    GET_CARTOON_BY_ID("uuid", "GET"),
    CREATE_CARTOON("", "POST"),
    UPDATE_CARTOON("", "PATCH"),
    DELETE_CARTOON_BY_ID("uuid", "DELETE")
}