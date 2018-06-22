package com.zhukovme.flickrclient.api.interceptors

import android.content.Context
import android.support.annotation.NonNull
import com.zhukovme.flickrclient.R
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
class RequestInterceptor(context: Context) : Interceptor {

    private val apiKey: CharSequence

    init {
        apiKey = context.getString(R.string.api_key)
    }

    @Throws(IOException::class)
    override fun intercept(@NonNull chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url()
        val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", apiKey.toString())
                .addQueryParameter("format", "json")
                .addQueryParameter("nojsoncallback", "1")
                .build()
        val request = originalRequest.newBuilder().url(url).build()
        return chain.proceed(request)
    }

}
