package com.muazekici.relaxingsounds.repositories.server_api

import android.content.Context
import com.muazekici.relaxingsounds.R
import com.muazekici.relaxingsounds.di.qualifiers.ApplicationContext
import com.muazekici.relaxingsounds.di.scopes.AppScope
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import java.lang.Exception
import javax.inject.Inject

@AppScope
class MockAPI @Inject constructor(@ApplicationContext private val context: Context) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url

        return URLtoFileMap[url.encodedPath]?.let {
            try {
                val responseVal = readFile(it)
                return Response.Builder()
                    .code(200)
                    .addHeader("content-type", "application/json")
                    .message(responseVal).request(chain.request()).protocol(Protocol.HTTP_1_1)
                    .body(
                        responseVal.toByteArray()
                            .toResponseBody(MEDIA_TYPE.toMediaTypeOrNull())
                    ).build()

            } catch (e: Exception) {
                createError(500, e.message.toString(), chain.request())
            }

        } ?: createError(404, "", chain.request())
    }

    private fun readFile(fileId: Int): String {
        val istream = context.resources.openRawResource(fileId)
        return istream.bufferedReader().use { it.readText() }
    }

    private fun createError(code: Int, message: String, request: Request) = Response.Builder()
        .request(request)
        .protocol(Protocol.HTTP_1_1)
        .message(message)
        .body(byteArrayOf().toResponseBody(MEDIA_TYPE.toMediaTypeOrNull()))
        .code(code)
        .build()

    companion object {
        private val URLtoFileMap = hashMapOf(
            "/favorites" to R.raw.favorites,
            "/categories" to R.raw.categories,
            "/category/1" to R.raw.category1,
            "/category/2" to R.raw.category2,
            "/category/3" to R.raw.category3
        )

        private const val MEDIA_TYPE: String = "application/json"
    }
}