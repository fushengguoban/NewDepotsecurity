package com.jthl.depotsecurity.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * @author wanglei
 * @date 2023/11/20 16:00
 * @Description: 网络请求构建器基类
 */
abstract class BaseNetWorkApi {
    fun <T> getApi(serviceClass: Class<T>, baseUrl: String): T {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
        return setRetrofitBuilder(retrofitBuilder).build().create(serviceClass)
    }

    /**
     * 实现重写父类的setHttpClientBuilder方法。
     * 在这里可以添加拦截器，可以对 OKHttpClient.Builder 做任意操作
     * @param builder Builder
     * @return OkHttpClient.Builder
     */
    abstract fun setHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder


    abstract fun setRetrofitBuilder(builder: Retrofit.Builder): Retrofit.Builder


    private val okHttpClient: OkHttpClient
        get() {
            var builder = OkHttpClient.Builder()
            builder=setHttpClientBuilder(builder)
            return builder.build()
        }


}