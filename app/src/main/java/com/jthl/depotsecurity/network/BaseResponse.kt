package com.jthl.depotsecurity.network

/**
 * @author wanglei
 * @date 2023/11/20 17:48
 * @Description:
 */
abstract class BaseResponse<T> {
    abstract fun isSuccess(): Boolean

    abstract fun getResponseData(): T

    abstract fun getResponseCode(): Int

    abstract fun getResponseMsg(): String
}