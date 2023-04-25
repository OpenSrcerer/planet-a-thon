package xyz.danielstefani.patmobile.client.cartoon

/**
 * An enumeration of Cartoon API endpoints.
 *
 * @property location The location of the endpoint on the Cartoon API.
 * @property method The HTTP method used to make a call to this endpoint.
 */
enum class CartoonHttpEndpoint(
    val location: String,
    val method: String = ""
) {

    // The base path for all cartoon requests.
    CARTOON_BASE_PATH("cartoons"),

    // *******************

    // Get all the cartoons (paginated).
    GET_ALL_CARTOONS("", "GET"),

    // Get a specific cartoon by ID.
    GET_CARTOON_BY_ID("uuid", "GET"),

    // Create a new cartoon.
    CREATE_CARTOON("", "POST"),

    // Update an existing cartoon.
    UPDATE_CARTOON("", "PATCH"),

    // Delete a cartoon by ID.
    DELETE_CARTOON_BY_ID("uuid", "DELETE")
}