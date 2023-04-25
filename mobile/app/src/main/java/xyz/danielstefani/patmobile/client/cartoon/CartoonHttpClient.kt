package xyz.danielstefani.patmobile.client.cartoon

import androidx.annotation.CheckResult
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import reactor.core.publisher.Mono
import xyz.danielstefani.patmobile.client.HttpCallback
import xyz.danielstefani.patmobile.dto.Cartoon
import xyz.danielstefani.patmobile.dto.CartoonPage
import java.util.concurrent.TimeUnit

/**
 * A singleton HTTP Client to make web requests.
 */
object CartoonHttpClient {

    // Create an instance of OkHttpClient with specific configurations and an instance of ObjectMapper.
    private val client: OkHttpClient =
        OkHttpClient().newBuilder()
            .callTimeout(5000, TimeUnit.MILLISECONDS)
            .build()
    private val objectMapper: ObjectMapper = ObjectMapper()

    /**
     * @return A Mono that emits a CartoonPage object containing all the cartoons.
     */
    @CheckResult
    fun getAllCartoons(): Mono<CartoonPage> {
        // Make an HTTP request asynchronously with the GET_ALL_CARTOONS endpoint of the Cartoon API.
        // The body parameter is null by default since this is a GET request.
        return makeHttpRequestAsync<Cartoon>(CartoonHttpEndpoint.GET_ALL_CARTOONS)
            // Map the String response to a CartoonPage object using ObjectMapper.
            .map { objectMapper.readValue(it, CartoonPage::class.java) }
    }

    /**
     * Makes a HTTP request asynchronously.
     *
     * @param T The type of object to send in the HTTP request body.
     * @param cartoonHttpEndpoint The endpoint of the HTTP request.
     * @param body The object to send in the HTTP request body.
     * @return A Mono that emits the String response of the HTTP request.
     */
    private fun <T : java.io.Serializable> makeHttpRequestAsync(
        cartoonHttpEndpoint: CartoonHttpEndpoint,
        body: T? = null
    ): Mono<String> {

        // Create a new Mono that is the source of the asynchronous computation.
        return Mono.create { sink ->

            // Call a new instance method on OkHttpClient to create a new HTTP call.
            client.newCall(

                // Construct a new request using Request.Builder.
                with(
                    Request.Builder()
                        .url(getCartoonsHttpUrl(cartoonHttpEndpoint))
                        .header("PlanetAThonKey", "rememberme")
                ) {

                    // Set the HTTP method on the request as appropriate for the HTTP endpoint.
                    if (body != null) {
                        method(
                            cartoonHttpEndpoint.method,
                            objectMapper.writeValueAsString(body)
                                .toRequestBody("application/json".toMediaType())
                        )
                    } else {
                        method(cartoonHttpEndpoint.method, null)
                    }
                }.build()

                // Enqueue the HTTP request with an HttpCallback instance that will emit the response via the sink.
            ).enqueue(HttpCallback(sink))
        }
    }

    /**
     * @param cartoonHttpEndpoint The CartoonHttpEndpoint to construct an HttpUrl for.
     * @return An HttpUrl object for the specified CartoonHttpEndpoint.
     */
    private fun getCartoonsHttpUrl(cartoonHttpEndpoint: CartoonHttpEndpoint): HttpUrl {

        // Build an HttpUrl object using HttpUrl.Builder to construct the URL for the Cartoon API.
        return HttpUrl.Builder()
            .scheme("http") // Set the URL scheme to HTTP.
            .host("10.0.2.2") // Set the host to the server's address.
            .port(8080) // Set the port for the server.
            .addPathSegments("api/v1") // Add the API base path to the URL.
            .addPathSegment(CartoonHttpEndpoint.CARTOON_BASE_PATH.location) // Add the cartoon base path to the URL.
            .addPathSegment(cartoonHttpEndpoint.location) // Add the endpoint location to the URL.
            .build() // Build the HttpUrl object.
    }
}