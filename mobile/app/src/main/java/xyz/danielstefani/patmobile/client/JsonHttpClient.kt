package xyz.danielstefani.patmobile.client

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object JsonHttpClient {

    private val client: OkHttpClient

    init {
        client = OkHttpClient().newBuilder()
            .callTimeout(5000, TimeUnit.MILLISECONDS)
            .build()
    }

    fun getAllCartoons() {

    }

}