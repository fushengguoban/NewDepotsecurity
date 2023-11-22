package com.jthl.depotsecurity.network.logging

import okhttp3.MediaType
import okhttp3.Request

/**
 * @author wanglei
 * @date 2022/3/3 15:46
 * @Description:
 */
interface FormatPrinter {

    /**
     * 打印网络请求信息，当网络请求时{[OkHttp3.RequestBody]}可以解析的情况
     * @param request Request
     * @param bodyString String 发送给服务器的请求体中的数据（以解析）
     */
    fun printJsonRequest(request: Request, bodyString: String)

    /**
     * 打印网络请求信息，当网络请求时{[okhttp3.RequestBody]}为null 或不可解析的情况
     * @param request Request
     */
    fun printFileRequest(request: Request)

    /**
     * 打印网络响应信息，当网络响应时可以解析的情况
     * @param chainMsg Long            服务器响应耗时
     * @param isSuccessful Boolean     请求是否成功
     * @param code Int                 响应码
     * @param headers String           请求头
     * @param contentType MediaType?   服务器返回数据的数据类型
     * @param bodyString String?       服务器返回的数据（以解析）
     * @param segments List<String?>   域名后面的资源地址
     * @param message String           响应信息
     * @param responseUrl String       请求地址
     */
    fun printJsonResponse(
        chainMsg: Long,
        isSuccessful: Boolean,
        code: Int,
        headers: String,
        contentType: MediaType?,
        bodyString: String?,
        segments: List<String?>,
        message: String,
        responseUrl: String
    )

    /**
     * 打印网络响应信息, 当网络响应时 {[okhttp3.ResponseBody]} 为 `null` 或不可解析的情况
     *
     * @param chainMs      服务器响应耗时(单位毫秒)
     * @param isSuccessful 请求是否成功
     * @param code         响应码
     * @param headers      请求头
     * @param segments     域名后面的资源地址
     * @param message      响应信息
     * @param responseUrl  请求地址
     */
    fun printFileResponse(
        chainMs: Long,
        isSuccessful: Boolean,
        code: Int,
        headers: String,
        segments: List<String?>,
        message: String,
        responseUrl: String
    )
}