package com.jthl.depotsecurity.network

import android.net.ParseException
import android.util.MalformedJsonException
import com.google.gson.JsonParseException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @author wanglei
 * @date 2022/3/3 15:14
 * @Description: 根据异常返回相关错误信息工具类
 */
object ExceptionHandle {
    fun handleException(e: Throwable?): AppException {
        val ex: AppException
        e?.let {
            when (it) {
                is HttpException -> {
                    return AppException(Error.NETWORK_ERROR, e)
                }
                is JsonParseException, is JSONException, is ParseException, is MalformedJsonException -> {
                    return AppException(Error.PARSE_ERROR, e)
                }
                is ConnectException -> {
                    return AppException(Error.NETWORK_ERROR, e)
                }
                is javax.net.ssl.SSLException -> {
                    return AppException(Error.SSL_ERROR, e)
                }
                is ConnectTimeoutException -> {
                    return AppException(Error.TIMEOUT_ERROR, e)
                }
                is SocketTimeoutException -> {
                    return AppException(Error.TIMEOUT_ERROR, e)
                }
                is UnknownHostException -> {
                    return AppException(Error.TIMEOUT_ERROR, e)
                }
                is AppException -> return it
                else -> {
                    return AppException(Error.UNKNOWN, e)
                }
            }
        }
        return AppException(Error.UNKNOWN, e)
    }
}