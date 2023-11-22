package com.jthl.depotsecurity.ext

/**
 * @author wanglei
 * @date 2023/11/10 17:12
 * @Description:
 */
object DataRequest {

    data class LoginRequest(
        val account: String,
        val password: String
    )
}