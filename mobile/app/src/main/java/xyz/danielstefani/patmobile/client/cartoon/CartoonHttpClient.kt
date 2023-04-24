package xyz.danielstefani.patmobile.client.cartoon

import android.util.Log
import androidx.annotation.CheckResult
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import reactor.core.publisher.Mono
import xyz.danielstefani.patmobile.client.HttpCallback
import xyz.danielstefani.patmobile.client.HttpEndpoint
import xyz.danielstefani.patmobile.dto.PaginatedCartoon
import java.time.Duration
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit

object CartoonHttpClient {

    private val client: OkHttpClient =
        OkHttpClient().newBuilder()
            .callTimeout(5000, TimeUnit.MILLISECONDS)
            .build()
    private val objectMapper: ObjectMapper = ObjectMapper()

    @CheckResult
    fun getAllCartoons(): Mono<PaginatedCartoon> {
        return makeHttpRequestAsync(CartoonHttpEndpoint.GET_ALL_CARTOONS)
            .timeout(Duration.of(3, ChronoUnit.SECONDS))
            .map { objectMapper.readValue(it, PaginatedCartoon::class.java) }
            .doOnNext { println(it.content) }
    }

    private fun makeHttpRequestAsync(
        cartoonHttpEndpoint: CartoonHttpEndpoint
    ): Mono<String> {
        println(getCartoonsHttpUrl(cartoonHttpEndpoint).toString())
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