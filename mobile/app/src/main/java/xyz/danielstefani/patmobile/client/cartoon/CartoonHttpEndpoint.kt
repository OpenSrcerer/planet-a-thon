package xyz.danielstefani.patmobile.client.cartoon

enum class CartoonHttpEndpoint(
    val location: String
) {
    CARTOON_BASE_PATH("cartoons"),

    GET_ALL_CARTOONS(""),
    GET_CARTOON_BY_ID("uuid"),
    CREATE_CARTOON(""),
    UPDATE_CARTOON(""),
    DELETE_CARTOON_BY_ID("uuid")
}