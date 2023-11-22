package com.jthl.depotsecurity.network.now

import com.google.gson.GsonBuilder
import com.jthl.depotsecurity.network.ApiService
import com.jthl.depotsecurity.network.BaseNetWorkApi
import com.jthl.depotsecurity.network.logging.LogInterceptor
import com.jthl.depotsecurity.network.token.TokenInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author wanglei
 * @date 2022/3/3 17:23
 * @Description: 网络请求构建器，继承BaseNetworkApi 并实现setHttpClientBuilder/setRetrofitBuilder方法，
 * 在这里可以添加拦截器，设置构造拦截器可以对Builder做任意操作
 */


val apiService: ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkApi.INSTANCE.getApi(ApiService::class.java, ApiService.SERVER_URL)
}

class NetworkApi : BaseNetWorkApi() {
    companion object {
        val INSTANCE: NetworkApi by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkApi()
        }
        var headerName = ""  //请求头 token
        var headerValue = ""  //token的值
    }

    /**
     * 重写父类的setHttpClientBuilder方法
     * 在这里可以添加拦截器，可以对OKHttpClient.Builder 做任意操作
     * @param builder Builder
     * @return OkHttpClient.Builder
     */
    override fun setHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        return builder.apply {
            addInterceptor(TokenInterceptor())
            addInterceptor(LogInterceptor())
            connectTimeout(60L, TimeUnit.SECONDS)
            readTimeout(60L, TimeUnit.SECONDS)
            writeTimeout(60L, TimeUnit.SECONDS)
        }
    }

    /**
     * 实现重写父类的setRetrofitBuilder方法
     * 在这里可以对Retrofit.Builder做任意操作，比如添加GSON解析器，protobuf等
     * @param builder Builder
     * @return Retrofit.Builder
     */
    override fun setRetrofitBuilder(builder: Retrofit.Builder): Retrofit.Builder {
        return builder.apply {
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        }
    }

}