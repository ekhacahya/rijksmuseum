package dev.ecbn.rijksmuseum.data.remote

import dev.ecbn.rijksmuseum.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

/**
 * Created by Eka Cahya Nugraha on 02/11/21.
 * ecbn95@gmail.com
 */
class RequestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl: HttpUrl = originalRequest.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("key", BuildConfig.API_KEY)
            .build()
        val request = originalRequest.newBuilder().url(url)
            .build()
        Timber.d(request.toString())
        return chain.proceed(request)
    }
}