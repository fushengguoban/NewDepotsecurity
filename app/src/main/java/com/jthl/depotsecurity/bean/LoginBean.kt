package com.jthl.depotsecurity.bean

/**
 * @author wanglei
 * @date 2023/11/10 15:20
 * @Description:
 */
data class LoginBean(
    val jobNumber: String,
    val name: String,
    val retcode: String,
    val retmsg: String,
    val token: String,
    val siteId: Int?
)
