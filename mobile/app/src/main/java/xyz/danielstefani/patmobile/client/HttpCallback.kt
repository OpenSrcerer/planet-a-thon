package xyz.danielstefani.patmobile.client

import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import reactor.core.publisher.MonoSink
import java.io.IOException

/**
 * A Callback implementation that emits either the response body or an error through a MonoSink.
 *
 * @property sink The MonoSink to emit the response body or an error through.
 */
class HttpCallback(
    private val sink: MonoSink<String>
) : Callback {

    /**
     * Called when the HTTP request completes with an HTTP response.
     *
     * @param call The HTTP call.
     * @param response The HTTP response.
     */
    override fun onResponse(call: Call, response: Response) {
        // Extract the response body as a string.
        val body = response.body?.string()

        // If the response body is empty or null, emit a failure through the sink.
        if (body.isNullOrEmpty()) {
            onFailure(call, IOException("Response body sent by the server was null."))
        }

        // Otherwise, emit the response body as a success through the sink.
        sink.success(body)
    }

    /**
     * Called when the HTTP request fails.
     *
     * @param call The HTTP call.
     * @param e The exception that was thrown.
     */
    override fun onFailure(call: Call, e: IOException) {
        sink.error(e)
    }
}