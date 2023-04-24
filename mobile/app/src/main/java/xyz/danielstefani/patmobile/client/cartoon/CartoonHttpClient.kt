package xyz.danielstefani.patmobile.client.cartoon

import androidx.annotation.CheckResult
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import reactor.core.publisher.Mono
import xyz.danielstefani.patmobile.client.HttpCallback
import xyz.danielstefani.patmobile.client.HttpEndpoint
import xyz.danielstefani.patmobile.dto.CartoonPage
import java.util.concurrent.TimeUnit

object CartoonHttpClient {

    private val client: OkHttpClient =
        OkHttpClient().newBuilder()
            .callTimeout(5000, TimeUnit.MILLISECONDS)
            .build()
    private val objectMapper: ObjectMapper = ObjectMapper()

    @CheckResult
    fun getAllCartoons(): Mono<CartoonPage> {
        return makeHttpRequestAsync(CartoonHttpEndpoint.GET_ALL_CARTOONS)
            .map { objectMapper.readValue(it, CartoonPage::class.java) }
    }

    private fun makeHttpRequestAsync(
        cartoonHttpEndpoint: CartoonHttpEndpoint
    ): Mono<String> {
        return Mono.create { sink ->
            client.newCall(
                Request.Builder()
                    .url(getCartoonsHttpUrl(cartoonHttpEndpoint))
                    .header("PlanetAThonKey", "rememberme")
                    .build()
            ).enqueue(HttpCallback(sink))
        }
    }

    private fun getCartoonsHttpUrl(
        cartoonHttpEndpoint: CartoonHttpEndpoint
    ): HttpUrl {
        return HttpUrl.Builder()
            .scheme("http")
            .host("10.0.2.2")
            .port(8080)
            .addPathSegments(HttpEndpoint.HTTP_BASE_PATH.location)
            .addPathSegment(CartoonHttpEndpoint.CARTOON_BASE_PATH.location)
            .addPathSegment(cartoonHttpEndpoint.location)
            .build()
    }
}