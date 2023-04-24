package xyz.danielstefani.patmobile.client

import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import reactor.core.publisher.MonoSink
import java.io.IOException

class HttpCallback(
    private val sink: MonoSink<String>
) : Callback {
    override fun onResponse(call: Call, response: Response) {
        val body = response.body?.string()

        if (body.isNullOrEmpty()) {
            onFailure(call, IOException("Response body sent by the server was null."))
        }

        sink.success(body)
    }

    override fun onFailure(call: Call, e: IOException) {
        sink.error(e)
    }
}