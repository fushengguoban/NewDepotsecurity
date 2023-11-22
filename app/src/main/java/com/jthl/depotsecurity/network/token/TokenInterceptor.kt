package com.jthl.depotsecurity.network.token

import com.jthl.depotsecurity.network.now.NetworkApi.Companion.headerName
import com.jthl.depotsecurity.network.now.NetworkApi.Companion.headerValue
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author wanglei
 * @date 2023/11/20 22:44
 * @Description:
 */
class TokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        return if (headerName.isEmpty()) {
            val requestBuilder = originalRequest.newBuilder()
                .method(originalRequest.method, originalRequest.body)
            val request = requestBuilder.build()
            chain.proceed(request)
        } else {
            val requestBuilder = originalRequest.newBuilder()
                .method(originalRequest.method, originalRequest.body)
                .header(headerName, headerValue)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

}